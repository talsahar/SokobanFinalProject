package app.xmlclasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "State")
public class State {

	private String state;

	public State() {
	}

	public State(String state) {
		this.state = state;
	}

	@XmlElement
	public String getState() {
		return state;
	}

	public int getHash() {
		return state.hashCode();
	}

	public void setState(String state) {
		this.state = state;
	}

}
