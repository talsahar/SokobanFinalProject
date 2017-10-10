package model.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Level implements Serializable {

	private String path;

	private ArrayList<ArrayList<XObj>> myLevel;
	private int sizeX, sizeY;

	private XObj player;

	private boolean completed;

	private int goal;
	private int winCounter;

	private int steps;

	public Level() {
		myLevel = new ArrayList<ArrayList<XObj>>();
		sizeX = 0;
		sizeY = 0;

		player = null;

		completed = false;
		winCounter = 0;
		path = null;
		steps = 0;
		goal = 0;
	}

	public void copy(Level c) {
		path = c.path;
		myLevel = c.myLevel;
		sizeX = c.sizeX;
		sizeY = c.sizeY;
		player = c.player;
		completed = c.completed;
		goal = c.goal;
		winCounter = c.winCounter;
		steps = c.steps;

	}

	public void downWinCounter() {
		winCounter--;
	}

	public void fixMap() {

		int x = this.sizeX;
		int y = this.sizeY;
		for (int i = 0; i < y; i++) {
			if (myLevel.get(i).size() < sizeX)
				for (int j = 0; j <= sizeX - myLevel.get(i).size(); j++) {
					myLevel.get(i).add(new Outside(j, i));
				}

		}

		if (x > y) {
			for (int i = y; i < x; i++) {
				myLevel.add(new ArrayList<XObj>());
				for (int j = 0; j < x; j++) {
					myLevel.get(i).add(new Outside(j, i));

				}

			}

		}
		if (x < y) {
			for (int i = 0; i < y; i++) {
				for (int j = myLevel.get(i).size(); j < y; j++) {
					myLevel.get(i).add(new Outside(j, i));

				}

			}

		}

	}

	public XObj getFrom(int x, int y) {

		return myLevel.get(y).get(x);

	}

	public XObj getFrom(Place p) {

		return (p.getX() >= 0 && p.getX() < sizeX && p.getY() >= 0 && p.getY() < sizeY)
				? myLevel.get(p.getY()).get(p.getX())
				: null;

	}

	public ArrayList<ArrayList<XObj>> getMyLevel() {
		return myLevel;
	}

	public String getPath() {
		return path;

	}

	public XObj getPlayer() {
		return player;
	}

	/*
	 * public int getPlayerMode() { if(player instanceof Player) return ((Player)
	 * player).getMode(); return -1; }
	 */

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

	public int getSteps() {
		return steps;
	}

	public boolean isBox(Place o) {
		return (getFrom(o).getClass() == Box.class);
	}

	/////////////// **************Getters+Setters*********************///////////////

	public boolean isBoxHole(Place o) {
		return (getFrom(o).getClass() == BoxHole.class);
	}

	public boolean isCompleted() {
		return completed;
	}

	public boolean isExist() {
		return !(player == null);

	}

	public boolean isHole(Place o) {
		return (getFrom(o).getClass() == Hole.class);
	}

	public boolean isPlayer(Place o) {
		return (getFrom(o).getClass() == Player.class);
	}

	public boolean isSpace(Place o) {

		return (getFrom(o).getClass() == Space.class);
	}

	public boolean isPlayerHole(Place o) {

		return (getFrom(o).getClass() == PlayerHole.class);
	}

	public boolean isWall(Place o) {
		return (getFrom(o).getClass() == Wall.class);
	}

	public void setGoal(int goalCounter) {
		this.goal = goalCounter;

	}

	public void setMyLevel(ArrayList<ArrayList<XObj>> myLevel) {
		this.myLevel = myLevel;
	}

	public void setNumVBox(int currectCounter) {

	}

	public void setPath(String p) {
		path = p;
	}

	public void setPlayer(XObj player) {
		this.player = player;
	}

	public void setPlayerMode(int m) {
		if (player instanceof Player)
			((Player) player).setMode(m);
	}

	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}

	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public void setTo(Place src, Place des) {
		setTo(getFrom(src), des);

	}

	public void setTo(XObj o, int x, int y) {

		myLevel.get(y).set(x, o);
		getFrom(x, y).setPlace(x, y);

	}

	public void setTo(XObj o, Place place) {

		int x = place.getX();
		int y = place.getY();
		myLevel.get(y).set(x, o);
		getFrom(x, y).setPlace(place);

	}

	public void setWinCounter(int currectCounter) {
		this.winCounter = currectCounter;

	}

	public char[][] toCharArrays() {
		char[][] map = new char[sizeY][sizeX];
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				map[i][j] = myLevel.get(i).get(j).getValue();

			}
		}

		return map;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Path: (" + path + ")\n";
		s += "Size: " + "(" + sizeX + "X" + sizeY + ")\n";

		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {
				s += getFrom(j, i).toString() + "," + getFrom(j, i).getPlace().toString() + "|";

			}
			s += "\n";
		}
		return s;

	}

	public String toURL() {
		String s = "";
		for (int i = 0; i < sizeY; i++) {
			for (int j = 0; j < sizeX; j++) {

				s += getFrom(j, i).toString();

			}
			s += "n";
		}

		return s;

	}

	public void addStep() {
		steps++;
	}

	public void upWinCounter() {
		winCounter++;
		if (winCounter == goal) {
			completed = true;

		}

	}

}
