package model.records;

public class Record implements Comparable<Record> {

	private String date;

	private String name;

	private String lvlName;

	private int time;

	private int steps;

	public Record(String date, String playerName, String lvlName, int time, int steps) {
		this.lvlName = lvlName;
		this.time = time;
		this.steps = steps;
		this.name = playerName;
		this.date = date;

	}

	@Override
	public int compareTo(Record arg0) {
		return time - arg0.time;
	}

	public String getDate() {
		return date;
	}

	public String getLvlName() {
		return lvlName;
	}

	public String getName() {
		return name;
	}

	public int getSteps() {
		return steps;
	}

	public int getTime() {
		return time;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setLvlName(String lvlName) {
		this.lvlName = lvlName;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		String s = "";
		s += date + "," + name + "," + lvlName + "," + time + "," + steps;

		return s;

	}

}
