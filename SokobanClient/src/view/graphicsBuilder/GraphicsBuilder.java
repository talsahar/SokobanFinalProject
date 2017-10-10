package view.graphicsBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import others.MyMapper;
import view.FrontDisplayer;

public interface GraphicsBuilder {
	default Image loadImage(String path) {
		try {
			return new Image(new FileInputStream(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return null;

	}

	void buildButtons(ImageView play, ImageView load, ImageView save, ImageView exit);

	void buildFront(FrontDisplayer menu);

	public MyMapper<Class, Image> buildLevel();

	MyMapper<Integer, Image> buildCharacter();

	void buildBackground(BorderPane borderPane);

	void buttonsShadowEffect(ImageView play, ImageView load, ImageView save, ImageView exit);

}
