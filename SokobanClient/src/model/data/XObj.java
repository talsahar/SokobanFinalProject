package model.data;

import java.io.Serializable;

public abstract class XObj implements Serializable {

	private Place place;

	private char value;

	public XObj() {
		place = new Place();

	}

	public XObj(int x, int y, char w) {
		place = new Place(x, y);
		value = w;

	}

	public Place getPlace() {
		return place;
	}

	public char getValue() {
		return value;
	}

	public void setPlace(int x, int y) {
		this.place.setX(x);
		this.place.setY(y);
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	@Override
	public String toString() {

		return "" + value;
	}

}
