package model.streamtypes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import model.data.Level;

public class StreamManager {

	private static StreamManager instance = new StreamManager();

	public static StreamManager getInstance() {
		return instance;

	}

	LevelStream stream; // type of ObjectStream,TextStream,XMLStream

	private StreamManager() {
		if (instance != null) {
			throw new RuntimeException("cannot constract use getInstance()");
		}
	}

	public void LevelLoader(String fileName, Level loadMe) throws ClassNotFoundException, IOException {

		loadMe.setPath(fileName);
		getStream(fileName).loadLevel(loadMe, new FileInputStream(fileName));

	}

	public void LevelSaver(String fileName, Level saveMe) {

		try {
			getStream(fileName).saveLevel(saveMe, new FileOutputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private LevelStream getStream(String fileName) {

		switch (fileName.substring(fileName.length() - 4)) {
		case ".xml":
			return (new XMLStream());
		case ".obj":
			return (new ObjectStream());
		case ".txt":
			return (new TextStream());
		}

		return null;

	}

}
