package paintGUI.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class toXML {
	public static void serializeObjectToXML(String xmlFileLocation, ArrayList<RandShape> objectToSerialize) throws Exception {
		FileOutputStream os = new FileOutputStream(xmlFileLocation);
		XMLEncoder encoder = new XMLEncoder(os);
		encoder.writeObject(objectToSerialize);
		encoder.close();
	}

	/**
	 * Reads Java Bean Object From XML File
	 */
	public static ArrayList<RandShape> deserializeXMLToObject(String xmlFileLocation) throws Exception {
		FileInputStream os = new FileInputStream(xmlFileLocation);
		XMLDecoder decoder = new XMLDecoder(os);
		ArrayList<RandShape> deSerializedObject = (ArrayList<RandShape>) decoder.readObject();
		decoder.close();

		return deSerializedObject;
	}
}
