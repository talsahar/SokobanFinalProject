package model.data;

import others.CONF;

public class PlayerHole extends XObj {

	public PlayerHole() {
		super();
	}

	public PlayerHole(int x, int y) {
		super(x, y, CONF.PlayerOnHole);

	}

}
