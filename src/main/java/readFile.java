import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.Document;
import org.bson.conversions.Bson;


import java.util.ArrayList;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

/**
 * Created by parthenis on 17/08/2017.
 */
public class readFile {


	public static void main(String args[]) {

		MongoClient mongoClient = new MongoClient();
		MongoDatabase myDatabase = mongoClient.getDatabase("mydb");

		MongoCollection<Document> list = myDatabase.getCollection("fs.files");

		//Bson projection = fields(include("filename"), excludeId());
		//Bson filter = and(gt("chunkSize", 261120));

		ArrayList<Document> all = list.find().into(new ArrayList<Document>());


		for (Document cur : all) {
			System.out.println(cur);
		}
	}
}
