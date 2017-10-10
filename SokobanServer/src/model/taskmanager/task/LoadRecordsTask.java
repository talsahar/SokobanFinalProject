package model.taskmanager.task;

import java.util.Observer;
import java.util.function.Consumer;

public class LoadRecordsTask extends WSTask {

	public LoadRecordsTask(String id, String _params, Observer o, Consumer<String> onTaskOver) {
		super(id, "LoadRecords", _params, o, onTaskOver);

	}

	@Override
	public void doTask() {
		System.out.println(param);
		String records = doGet("http://localhost:8080/SokobanWebServer/records/load/?" + param)
				.readEntity(String.class);
		onTaskOver.accept(records);
		setChanged();
		notifyObservers();

	}

}
