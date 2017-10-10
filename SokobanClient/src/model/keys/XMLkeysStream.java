package model.keys;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class XMLkeysStream {

	public SokobanKeys loadKeys(String in) {

		try {
			XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(in)));

			SokobanKeys keys = ((SokobanKeys) d.readObject());
			d.close();
			return keys;
		}

		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public void saveKeys(SokobanKeys saveMe, String out) {

		try {
			XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(out)));
			e.writeObject(saveMe);
			e.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

}
