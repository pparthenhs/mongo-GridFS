import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.types.ObjectId;

/**
 * Created by parthenis on 17/08/2017.
 */
public class deleteFile {

	public static void main(String args[]) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase myDatabase = mongoClient.getDatabase("mydb");
		GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);

		ObjectId fileId =new ObjectId("59955f7053b56f4f603cc84e");
		try {
			gridFSBucket.delete(fileId);
		}catch (Exception e) {
			System.out.println("no id");
		}

	}
}
