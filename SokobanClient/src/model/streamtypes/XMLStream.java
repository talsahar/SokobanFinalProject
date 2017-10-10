package model.streamtypes;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.data.Level;

public class XMLStream implements LevelStream {

	@Override
	public void loadLevel(Level lvl, InputStream in) throws IOException, ClassNotFoundException {

		XMLDecoder d = new XMLDecoder(in);

		lvl.copy((Level) d.readObject());

		d.close();

	}

	@Override
	public void saveLevel(Level saveMe, OutputStream out) throws IOException {

		XMLEncoder e = new XMLEncoder(out);
		e.writeObject(saveMe);
		e.close();
	}

}
