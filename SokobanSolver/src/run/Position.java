/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package run;

public class Position {

	private int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {

		return Integer.toString(x) + "," + Integer.toString(y);

	}

	@Override
	public boolean equals(Object obj) {
		return x == ((Position) obj).x && (y == ((Position) obj).y);
	}

}
