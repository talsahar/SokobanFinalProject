package model.data;

import others.CONF;

public class Player extends XObj {

	private int mode = 0;

	public Player() {
		super();

	}

	public Player(int x, int y) {
		super(x, y, CONF.Player);

	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}
