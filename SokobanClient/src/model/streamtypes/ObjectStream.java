package model.streamtypes;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import model.data.Level;

public class ObjectStream implements LevelStream {

	@Override
	public void loadLevel(Level lvl, InputStream in) throws IOException, ClassNotFoundException {

		ObjectInputStream ois = new ObjectInputStream(in);

		lvl.copy((Level) ois.readObject());

		ois.close();
	}

	@Override
	public void saveLevel(Level saveMe, OutputStream out) throws IOException {

		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(saveMe);
		out.close();

	}

}
