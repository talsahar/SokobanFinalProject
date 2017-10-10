package model.streamtypes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import model.data.Level;

public interface LevelStream {
	public void loadLevel(Level loadMe, InputStream in) throws IOException, ClassNotFoundException;

	public void saveLevel(Level saveMe, OutputStream out) throws IOException;
}
