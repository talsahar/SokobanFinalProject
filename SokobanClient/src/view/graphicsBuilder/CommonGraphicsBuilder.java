package view.graphicsBuilder;

import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.data.Box;
import model.data.BoxHole;
import model.data.Hole;
import model.data.Outside;
import model.data.PlayerHole;
import model.data.Space;
import model.data.Wall;
import others.CONF;
import others.MyMapper;
import view.FrontDisplayer;

public class CommonGraphicsBuilder implements GraphicsBuilder {

	private String gString;
	private String buttonsPath;

	public CommonGraphicsBuilder(String type) {
		gString = CONF.GRAPHICPATH + type;

	}

	@Override
	public void buildButtons(ImageView play, ImageView load, ImageView save, ImageView exit) {

		// p.setStyle("-fx-background-image: url('/testing/background.jpg')");
		play.setImage(loadImage(gString + CONF.playFileName));
		load.setImage(loadImage(gString + CONF.loadFileName));
		save.setImage(loadImage(gString + CONF.saveFileName));
		exit.setImage(loadImage(gString + CONF.exitFileName));
	}

	@Override
	public void buildFront(FrontDisplayer menu) {
		menu.setImageMap(
				new MyMapper<String, Image>().add(CONF.winnerFileName, loadImage(gString + CONF.winnerFileName))
						.add(CONF.aboutFileName, loadImage(gString + CONF.aboutFileName))
						.add(CONF.welcomeFileName, loadImage(gString + CONF.welcomeFileName))
						.add(CONF.championFileName, loadImage(gString + CONF.championFileName))
						.add(CONF.loadingDatabaseFileName, loadImage(gString + CONF.loadingDatabaseFileName)));

	}

	@Override
	public MyMapper<Integer, Image> buildCharacter() {
		return new MyMapper<Integer, Image>().add(0, loadImage(gString + CONF.playerDownFileName))
				.add(1, loadImage(gString + CONF.playerUpFileName)).add(2, loadImage(gString + CONF.playerLeftFileName))
				.add(3, loadImage(gString + CONF.playerRightFileName));

	}

	@Override
	public MyMapper<Class, Image> buildLevel() {

		return new MyMapper<Class, Image>().add(Box.class, loadImage(gString + CONF.boxFileName))
				.add(Wall.class, loadImage(gString + CONF.wallFileName))
				.add(Hole.class, loadImage(gString + CONF.holeFileName))
				.add(BoxHole.class, loadImage(gString + CONF.boxOnHoleFileName))
				.add(Outside.class, loadImage(gString + CONF.outsideFileName))
				.add(Space.class, loadImage(gString + CONF.spaceFileName))
				.add(PlayerHole.class, loadImage(gString + CONF.playerOnHoleFileName));

	}

	@Override
	public void buildBackground(BorderPane borderPane) {
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true);
		borderPane.setBackground(new Background(new BackgroundImage(loadImage(gString + CONF.welcomeFileName),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
	}

	@Override
	public void buttonsShadowEffect(ImageView play, ImageView load, ImageView save, ImageView exit) {
		buttonShadow(play);
		buttonShadow(load);
		buttonShadow(save);
		buttonShadow(exit);
	}

	private void buttonShadow(ImageView imageView) {
		// set a clip to apply rounded border to the original image.
		Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
		clip.setArcWidth(20);
		clip.setArcHeight(20);
		imageView.setClip(clip);

		// snapshot the rounded image.
		SnapshotParameters parameters = new SnapshotParameters();
		parameters.setFill(Color.TRANSPARENT);
		WritableImage image = imageView.snapshot(parameters, null);

		// remove the rounding clip so that our effect can show through.
		imageView.setClip(null);

		// apply a shadow effect.
		imageView.setEffect(new DropShadow(20, Color.BLACK));

		// store the rounded image in the imageView.
		imageView.setImage(image);
	}

}