import sign.signlink;
import java.util.zip.*;
import java.io.*;

public final class DataBase
{

	

	private static String getDir() {
		return signlink.findcachedir();
	}
	
	public static byte[][] allFrames = new byte[7000][];
	public static byte[][] allSkinlist = new byte[7000][];
	public static void loadAnimations() {
		System.out.println("Loading animations");
		int i = 0;
		try {
           GZIPInputStream gzipDataFile = new GZIPInputStream(new FileInputStream(getDir() + "frames.dat"));
           DataInputStream dataFile = new DataInputStream(gzipDataFile);
           GZIPInputStream gzipIndexFile = new GZIPInputStream(new FileInputStream(getDir() + "frames.idx"));
           DataInputStream indexFile = new DataInputStream(gzipIndexFile);
			int length = indexFile.readInt();
			for(i = 0; i < length; i++) {
				int id = indexFile.readInt();
				int invlength = indexFile.readInt();
				byte[] data = new byte[invlength];
				dataFile.readFully(data);
				allFrames[id]=data;
			}
			indexFile.close();
			dataFile.close();
		} catch (Exception e) {
			System.out.println("Error: "+i);
			e.printStackTrace();
		}
	
		try {
           GZIPInputStream gzipDataFile = new GZIPInputStream(new FileInputStream(getDir() + "skinlist.dat"));
           DataInputStream dataFile = new DataInputStream(gzipDataFile);
           GZIPInputStream gzipIndexFile = new GZIPInputStream(new FileInputStream(getDir() + "skinlist.idx"));
           DataInputStream indexFile = new DataInputStream(gzipIndexFile);
			int length = indexFile.readInt();
			for(i = 0; i < length; i++) {
				int id = indexFile.readInt();
				int invlength = indexFile.readInt();
				byte[] data = new byte[invlength];
				dataFile.readFully(data);
				allSkinlist[id]=data;
			}
			indexFile.close();
			dataFile.close();
		} catch (Exception e) {
			System.out.println("Error: "+i);
			e.printStackTrace();
		}
	}
	
	/*
	 * Models
	 * V1
	 */
	public static byte[][] allModels = new byte[70000][];
	public static int modelList[];
	/**public static void loadModels() {
	int i = 0;
		try {
           GZIPInputStream gzipDataFile = new GZIPInputStream(new FileInputStream(getDir() + "models.dat"));
           DataInputStream dataFile = new DataInputStream(gzipDataFile);
           GZIPInputStream gzipIndexFile = new GZIPInputStream(new FileInputStream(getDir() + "models.idx"));
           DataInputStream indexFile = new DataInputStream(gzipIndexFile);
			int length = indexFile.readInt();
			modelList = new int[length];
			for(i = 0; i < length; i++) {
				int id = indexFile.readInt();
				modelList[i] = id;
				int invlength = indexFile.readInt();
				byte[] data = new byte[invlength];
				dataFile.readFully(data);
				allModels[id]=data;
			}
			indexFile.close();
			dataFile.close();
		} catch (Exception e) {
			System.out.println("Error: "+i+" / "+modelList[i]);
			e.printStackTrace();
		}
		
	}*/
}
