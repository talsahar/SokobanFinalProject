package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Solution")
@Entity(name = "Solution")
public class Solution {

	@Id
	@Column(name = "SolutionHash")
	private String SolutionHash;
	@Column(name = "TheSolution")
	private String mySolution;

	public Solution() {

	}

	public Solution(String hash, String sol) {
		this.SolutionHash = hash;
		this.mySolution = sol;

	}

	@XmlElement
	public String getMySolution() {
		return mySolution;
	}

	@XmlElement
	public String getSolutionHash() {
		return SolutionHash;
	}

	public void setMySolution(String mySolution) {
		this.mySolution = mySolution;
	}

	public void setSolutionHash(String hash) {

		this.SolutionHash = hash;
	}

	@Override
	public String toString() {
		String s = "";
		s += "ID: " + SolutionHash + "\n" + mySolution;

		return s;
	}

}
