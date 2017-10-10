package model.taskmanager.task;

import java.util.Observer;
import java.util.function.Consumer;

import javax.ws.rs.core.Response;

import app.xmlclasses.State;

public class SolveTask extends WSTask {

	public SolveTask(String id, String _state, Observer o, Consumer<String> onTaskOver) {
		super(id, "Solve", _state, o, onTaskOver);
	}

	@Override
	public void doTask() {

		Response response = doPost("http://localhost:8080/SokobanWebServer/solution/",
				new State(param.replaceAll("n", "\n")));
		if (checkResponse(response)) {
			String result = response.readEntity(String.class);
			result = translateToNumericalSolution(result);
			onTaskOver.accept(result);
		}
		setChanged();
		notifyObservers();
	}

	private String translateToNumericalSolution(String message) {
		String solution = "";
		for (String word : message.split("\\s+")) {
			switch (word) {
			case "up":
				solution += 'u';
				break;
			case "down":
				solution += 'd';
				break;
			case "left":
				solution += 'l';
				break;
			case "right":
				solution += 'r';
				break;
			}
		}
		return solution;
	}
}
