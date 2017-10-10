package view;

import java.io.FileNotFoundException;
import java.util.List;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import model.records.Record;
import others.CONF;
import others.MyMapper;

public class FrontDisplayer extends Canvas {
	private MyMapper<String, Image> imageMapper;

	private GraphicsContext gc;

	public FrontDisplayer() {
		gc = getGraphicsContext2D();

		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
	}

	public void clearAndDraw(Image image) {
		gc.clearRect(0, 0, getWidth(), getHeight());
		if (image != null)
			gc.drawImage(image, 0, 0, getWidth(), getHeight());

	}

	public void aboutPop() throws FileNotFoundException {
		clearAndDraw(imageMapper.getByKey(CONF.aboutFileName));
	}

	public void displayPhoto() {
		clearAndDraw(imageMapper.getByKey(CONF.welcomeFileName));
	}

	public void loadingDatabase() {
		clearAndDraw(imageMapper.getByKey(CONF.loadingDatabaseFileName));
	}

	public void showRecords(List<Record> list) {
		clearAndDraw(imageMapper.getByKey(CONF.championFileName));
		gc.setFont(Font.font("Droid Sans", FontWeight.BOLD, 15));
		gc.setFill(Color.GREENYELLOW);
		int a = 60;
		int b = 170;
		int c = 270;
		int d = 370;
		int j = 140;
		for (Record rec : list) {
			gc.fillText(rec.getLvlName(), a, j);
			gc.fillText(rec.getName(), b, j);
			gc.fillText("" + rec.getSteps(), c, j);
			gc.fillText("" + rec.getTime(), d, j);
			j += 30;

		}

	}

	public void winPop(String time, String steps) {
		clearAndDraw(imageMapper.getByKey(CONF.winnerFileName));

		// text
		gc.setFont(Font.font("Droid Sans", FontWeight.BOLD, 25));
		gc.setFill(Color.ORANGERED);
		gc.fillText(time, 330, 210);
		gc.fillText(steps, 330, 240);

	}

	public void setImageMap(MyMapper<String, Image> map) {
		this.imageMapper = map;
	}
}
