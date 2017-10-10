package app.xmlclasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Solution")
public class Solution {

	private String SolutionHash;
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

	public void setSolutionHash(String solutionHash) {
		this.SolutionHash = solutionHash;
	}

	@Override
	public String toString() {
		String s = "";
		s += "ID: " + SolutionHash + "\n" + mySolution;

		return s;
	}

}
