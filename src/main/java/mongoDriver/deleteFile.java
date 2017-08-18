package mongoDriver;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.types.ObjectId;

/**
 * This class connect with the localhost mongo and with
 * the database myDatabase. Use the GridFS to delete a existing
 * file using file id.
 *
 *
 * Created by parthenis on 17/08/2017.
 */
public class deleteFile {

	public static void main(String args[]) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase myDatabase = mongoClient.getDatabase("myDatabase");
		GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);

		ObjectId fileId =new ObjectId("fileID");
		try {
			gridFSBucket.delete(fileId);
		}catch (Exception e) {
			System.out.println("no existing ID");
		}

	}
}
