package view.graphicsBuilder;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import view.FrontDisplayer;
import view.LevelDisplayer;
import view.MainWindowController;

public class LevelDisplayerManager {
	private BorderPane borderPane;
	private Canvas displayer;
	private Canvas frontDisplayer;

	private ImageView play;
	private ImageView load;
	private ImageView save;
	private ImageView exit;

	public LevelDisplayerManager(MainWindowController displayer) {
		this.displayer = displayer.getDisplayer();
		this.frontDisplayer = displayer.getFrontDisplayer();
		this.play = displayer.getPlayButton();
		this.load = displayer.getLoadButton();
		this.save = displayer.getSaveButton();
		this.exit = displayer.getExitButton();
		this.borderPane = displayer.getBorderPane();
	}

	public LevelDisplayerManager buildFrontGraphics(GraphicsBuilder builder) {
		builder.buildFront((FrontDisplayer) frontDisplayer);
		return this;
	}

	public LevelDisplayerManager buildCharacter(GraphicsBuilder builder) {
		((LevelDisplayer) displayer).setPlayerMap(builder.buildCharacter());
		return this;
	}

	public LevelDisplayerManager buildLevelGraphics(GraphicsBuilder builder) {
		((LevelDisplayer) displayer).setLevelMap(builder.buildLevel());
		return this;

	}

	public LevelDisplayerManager buildMenuGraphics(GraphicsBuilder builder) {
		builder.buildButtons(play, load, save, exit);
		return this;

	}

	public LevelDisplayerManager buildBorderBackground(GraphicsBuilder builder) {
		builder.buildBackground(borderPane);
		return this;

	}

	public LevelDisplayerManager buildMainButtonsShadow(GraphicsBuilder builder) {
		builder.buttonsShadowEffect(play, load, save, exit);
		return this;

	}

	public void setAll(CommonGraphicsBuilder builder) {
		buildFrontGraphics(builder).buildCharacter(builder).buildLevelGraphics(builder).buildMenuGraphics(builder)
				.buildBorderBackground(builder).buildMainButtonsShadow(builder);
	}

}
