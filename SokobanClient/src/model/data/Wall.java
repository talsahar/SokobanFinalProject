package model.data;

import others.CONF;

public class Wall extends XObj {

	public Wall() {
		super();
	}

	public Wall(int x, int y) {
		super(x, y, CONF.Wall);

	}

}
