package mongoDriver;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.types.ObjectId;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* This class connect with the localhost mongo and with
* the database 'myDatabase'. Use the GridFS to store a
* file on mongo.
*
*
* Created by parthenis on 17/08/2017.
*/

public class insertFile {

	public static void main(String args[]){
		MongoClient mongoClient = new MongoClient();
		MongoDatabase myDatabase = mongoClient.getDatabase("myDatabase");
		GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);

		try {
			// Get the input stream
			InputStream streamToUploadFrom = new FileInputStream(new File("/home/parthenis/Pictures/linux_command_cheat_sheet.jpg"));

			// Create some custom options
			GridFSUploadOptions options = new GridFSUploadOptions();//.chunkSizeBytes(819200);


			ObjectId fileId = gridFSBucket.uploadFromStream("newFileName", streamToUploadFrom, options);
		} catch (FileNotFoundException e){
			System.out.println("no existing File");
		}

	}
}
