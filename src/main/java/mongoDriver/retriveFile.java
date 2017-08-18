package mongoDriver;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.types.ObjectId;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


/* This class connect with the localhost mongo and with
* the database 'myDatabase'. Use the GridFS to retrieve file
* store all files's chucks on stream.
*
* (For example reason load image-file on Jframe)
*
*
* Created by parthenis on 17/08/2017.
*/
public class retriveFile {

	public static void main (String args[]){
		MongoClient mongoClient = new MongoClient();
		MongoDatabase myDatabase = mongoClient.getDatabase("myDatabase");
		GridFSBucket gridFSBucket = GridFSBuckets.create(myDatabase);

		ObjectId fileID =new ObjectId("fileID");
		InputStream fileStream = gridFSBucket.openDownloadStream(fileID); //store in stream all file's chucks

		try {
			BufferedImage image = ImageIO.read(fileStream);
			JFrame editorFrame = new JFrame("Image Demo");
			editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			ImageIcon imageIcon = new ImageIcon(image);
			JLabel jLabel = new JLabel();
			jLabel.setIcon(imageIcon);
			editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);
			editorFrame.pack();
			editorFrame.setLocationRelativeTo(null);
			editorFrame.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
