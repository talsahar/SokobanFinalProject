package app.xmlclasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Record")
public class Record {

	private String date;

	private String name;

	private String lvlName;

	private int time;

	private int steps;

	public Record() {

	}

	public Record(String date, String playerName, String lvlName, int time, int steps) {
		this.lvlName = lvlName;
		this.time = time;
		this.steps = steps;
		this.name = playerName;
		this.date = date;

	}

	@XmlElement
	public String getDate() {
		return date;
	}

	@XmlElement
	public String getLvlName() {
		return lvlName;
	}

	@XmlElement
	public String getName() {
		return name;
	}

	@XmlElement
	public int getSteps() {
		return steps;
	}

	@XmlElement
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
		s += name + "_" + lvlName + "_" + time + "_" + steps;

		return s;

	}

}
