import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;

import sign.signlink;
/**
 *Loads models from a data file
 *
 *@author Ben
 */

@SuppressWarnings("unused")
public class ModelDecompressor {
	/** Custom model cache loading, useless */

	public static void hdgfx() {
		try {
			DataInputStream indexFile = new DataInputStream(new FileInputStream(signlink.findcachedir() + "/Data/Animation/gfx.idx"));
			DataInputStream dataFile = new DataInputStream(new FileInputStream(signlink.findcachedir() + "/Data/Animation/gfx.dat"));
		int length = indexFile.readInt();
		for(int i = 0; i < length; i++) {
			int id = indexFile.readInt();
			int invlength = indexFile.readInt();
			byte[] data = new byte[invlength];
			dataFile.readFully(data);
			//System.out.println("ID: "+ id +" Length: "+ invlength +" Data: "+ data);
			Model.method460(data, id);
		}
		indexFile.close();
		dataFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
public static void models() {
try {
DataInputStream indexFile = new DataInputStream(new FileInputStream(signlink.findcachedir() + "/models.idx"));
DataInputStream dataFile = new DataInputStream(new FileInputStream(signlink.findcachedir() + "/models.dat"));
int length = indexFile.readInt();
for(int i = 0; i < length; i++) {
int id = indexFile.readInt();
int invlength = indexFile.readInt();
byte[] data = new byte[invlength];
dataFile.readFully(data);
//System.out.println("ID: "+ id +" Length: "+ invlength +" Data: "+ data);
Model.method460(data, id);
}
indexFile.close();
dataFile.close();
} catch (Exception e) {
e.printStackTrace();
}
}	
	public static void loadModelDataFile() {
		try {
			DataInputStream indexFile = new DataInputStream(new FileInputStream(signlink.findcachedir()+"null_cache.idx"));
			DataInputStream dataFile = new DataInputStream(new FileInputStream(signlink.findcachedir()+"null_cache.dat"));
			int length = indexFile.readInt();
			for(int i = 0; i < length; i++) {
				int id = indexFile.readInt();
				int invlength = indexFile.readInt();
				byte[] data = new byte[invlength];
				dataFile.readFully(data);
				Model.method460(data, id);
			}
			indexFile.close();
			dataFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}