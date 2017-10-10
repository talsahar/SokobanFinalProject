package model.data;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class BackgroundMusic {

	MediaPlayer player;
	boolean status;

	public BackgroundMusic(String path) {
		player = new MediaPlayer(new Media(new File(path).toURI().toString()));

	}

	public void off() {
		if (status) {
			player.pause();
			status = !status;
		}

	}

	public void on() {
		if (!status) {
			player.play();
			status = !status;
		}

	}

}
