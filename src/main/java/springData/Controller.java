package springData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * This class connect is rest Controller
 * and contains end-points for CRUD operation
 * on files in mongo.
 *
 * Created by parthenis on 18/08/2017.
 */
@RestController
@RequestMapping("/fs")
public class Controller {

	// GridFsOperations is a interface and declare all file operation
	// the default implementation for this interface is GridFSTemplate
	@Autowired
	GridFsOperations gridFsOperations;

	//end-point to store a file on mongo. The input File must be at type InputStream
	// @path --> current name of file
	// @name --> new file name
	@RequestMapping(value="/store/{path}/{name}", method = RequestMethod.POST)
	public String store(@PathVariable("path") String filePath, @PathVariable("name") String fileName) {
		InputStream inputStream;
		try {
			inputStream= new FileInputStream("/home/parthenis/Pictures/"+filePath);
			gridFsOperations.store(inputStream,fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "I added this "+ fileName;
	}


	//end-point to delete a file from mongo. Using Criteria for match
	// @name --> file name
	@RequestMapping(value="/delete/{name}", method = RequestMethod.POST)
	public String delete(@PathVariable ("name") String fileName) {
		gridFsOperations.delete(new Query(Criteria.where("filename").is(fileName)));
		return "I deleted this :"+ fileName;
	}


	//end-point to find metadata info on file from mongo. Using Criteria for match
	// @name --> file name
	@RequestMapping(value="/find/{name}", method = RequestMethod.POST)
	public String find(@PathVariable ("name") String fileName) {
		return "I find this : "+ gridFsOperations.findOne(new Query(Criteria.where("filename").is(fileName)));
	}

	//end-point to retrieve file from mongo
	// @name --> file name
	@RequestMapping(value="/get/{name}", method = RequestMethod.GET)
	public ResponseEntity<GridFsResource> get(@PathVariable ("name") String fileName) {
		GridFsResource image = gridFsOperations.getResource(fileName);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}

}
