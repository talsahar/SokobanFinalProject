package view;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import model.data.Player;
import model.data.PlayerHole;
import model.data.XObj;
import others.MyMapper;

public class LevelDisplayer extends Canvas {

	private MyMapper<Class, Image> levelMap;

	private MyMapper<Integer, Image> playerMap;
	private GraphicsContext gc = getGraphicsContext2D();

	public void redraw(ArrayList<ArrayList<XObj>> lvl, int px, int py) {

		if (lvl == null) {
			return;
		}

		int y = lvl.size();
		int x = lvl.get(0).size();

		double w = getWidth() / x;
		double h = getHeight() / y;

		gc.clearRect(0, 0, getWidth(), getHeight());

		for (int i = 0; i < y; i++)
			for (int j = 0; j < x; j++) {

				Image img = levelMap.getByKey(lvl.get(i).get(j).getClass());
				gc.drawImage(img, j * w, i * h, w, h);

			}

		XObj player = lvl.get(py).get(px);
		if (!(player instanceof PlayerHole)) {
			Image img = playerMap.getByKey(((Player) player).getMode());
			gc.drawImage(img, px * w, py * h, w, h);

		}

	}

	public void setPlayerMap(MyMapper map) {

		playerMap = map;
	}

	public void setLevelMap(MyMapper<Class, Image> levelMap) {
		this.levelMap = levelMap;
	}

}