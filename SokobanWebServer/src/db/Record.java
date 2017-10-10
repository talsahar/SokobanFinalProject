package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity(name = "Record")
@XmlRootElement(name = "Record")
public class Record {

	@Id
	@Column(name = "Date")
	private String date;

	@Column(name = "Player")
	private String name;

	@Column(name = "Level_Name")
	private String lvlName;

	@Column(name = "Time")
	private int time;

	@Column(name = "Steps")
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
