package model.streamtypes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import model.data.Box;
import model.data.BoxHole;
import model.data.Hole;
import model.data.Level;
import model.data.Outside;
import model.data.Player;
import model.data.PlayerHole;
import model.data.Space;
import model.data.Wall;
import model.data.XObj;
import others.CONF;

public class TextStream implements LevelStream {

	@Override
	public void loadLevel(Level myLevel, InputStream in) throws IOException {
		int goalCounter = 0;
		int currectCounter = 0;

		int maxX = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		String newLine;
		int j = 0;
		while ((newLine = br.readLine()) != null) {
			myLevel.getMyLevel().add(new ArrayList<XObj>()); // <--Must
																// initialize
																// first!!!
			for (int i = 0; i < newLine.length(); i++) {
				switch (newLine.charAt(i)) {
				case CONF.Wall:
					myLevel.getMyLevel().get(j).add(new Wall(i, j));
					break;
				case CONF.PlayerOnHole:
					myLevel.getMyLevel().get(j).add(new PlayerHole(i, j));
					myLevel.setPlayer(myLevel.getFrom(i, j));
					break;
				case CONF.Outside:
					myLevel.getMyLevel().get(j).add(new Outside(i, j));
					break;
				case CONF.BoxOnHole:
					myLevel.getMyLevel().get(j).add(new BoxHole(i, j));
					goalCounter++;
					currectCounter++;
					break;
				case CONF.Space:
					myLevel.getMyLevel().get(j).add(new Space(i, j));
					break;
				case CONF.Box:
					myLevel.getMyLevel().get(j).add(new Box(i, j));
					goalCounter++;
					break;
				case CONF.Player:
					myLevel.getMyLevel().get(j).add(new Player(i, j));
					myLevel.setPlayer(myLevel.getFrom(i, j));
					break;
				case CONF.Hole:
					myLevel.getMyLevel().get(j).add(new Hole(i, j));
					break;
				}
			} // end loop
			j++;

			/// Save the maximum size X
			if (maxX < newLine.length())
				maxX = newLine.length();

		} // end while

		myLevel.setSizeX(maxX);
		myLevel.setSizeY(j);
		myLevel.setGoal(goalCounter);
		myLevel.setWinCounter(currectCounter);

		myLevel.fixMap();

		in.close();

	}

	@Override
	public void saveLevel(Level saveMe, OutputStream out) throws IOException {

		PrintWriter writer = new PrintWriter(new OutputStreamWriter(out));
		for (int i = 0; i < saveMe.getSizeY(); i++) {
			for (int j = 0; j < saveMe.getSizeX(); j++) {

				writer.write(saveMe.getMyLevel().get(i).get(j).getValue());

			}
			writer.println();
		}
		writer.close();
	}

}
