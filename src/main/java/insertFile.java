import com.mongodb.MongoClient;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

/**
 * Created by parthenis on 17/08/2017.
 */

public class insertFile {

	public static void main(String args[]){

		MongoClient mongoClient = new MongoClient();
		MongoDatabase myDatabase = mongoClient.getDatabase("mydb");

		GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);

		// Get the input stream

		try {
			InputStream streamToUploadFrom = new FileInputStream(new File("/home/parthenis/Pictures/printable-letter-cloisterblack-p.jpg"));
			// Create some custom options
			GridFSUploadOptions options = new GridFSUploadOptions();
			//chunkSizeBytes(819200);
			ObjectId fileId = gridFSBucket.uploadFromStream("image-oneall", streamToUploadFrom, options);
		} catch (FileNotFoundException e){
			// handle exception
		}

	}
}
