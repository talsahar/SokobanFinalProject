package model.taskmanager.task;

import java.util.Observer;

import app.xmlclasses.Record;

public class SaveRecordsTask extends WSTask {
	
	public SaveRecordsTask(String id, String _params, Observer o) {
		super(id, "SaveRecord", _params, o, null);
	}

	@Override
	public void doTask() {
		String args[] = param.split(",");
		Record record = new Record(args[0], args[1], args[2], Integer.parseInt(args[3]), Integer.parseInt(args[4]));
		doPost("http://localhost:8080/SokobanWebServer/records/", record);
		setChanged();
		notifyObservers();
	}

}
