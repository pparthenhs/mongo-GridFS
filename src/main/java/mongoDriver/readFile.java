package mongoDriver;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.util.ArrayList;


/* This class connect with the localhost mongo and with
* the database 'myDatabase'. Use the GridFS to find metadata
* info about a file. (Classic find on fs.files Collection)
*
*
* Created by parthenis on 17/08/2017.
*/
public class readFile {


	public static void main(String args[]) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase myDatabase = mongoClient.getDatabase("myDatabase");
		MongoCollection<Document> list = myDatabase.getCollection("fs.files");

		//Bson projection = fields(include("filename"), excludeId());
		//Bson filter = and(gt("chunkSize", 261120));
		ArrayList<Document> all = list.find().into(new ArrayList<Document>());

		for (Document cur : all) {
			System.out.println(cur);
		}
	}
}
