package controller.command.records;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import model.records.Record;
import others.CommandNames;
import server.MyClient;
import view.View;

public class RecordSaveCommand extends SokobanCommand {

	public RecordSaveCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.ServerSaveScore);
	}

	@Override
	public void execute() {
		if (!client.isConnected())
			view.setMessage("error: disconnected from server");
		else {
			if (!params.isEmpty()) {

				Record record = model.buildRecord(params.remove(0));
				client.send(CommandNames.ServerSaveScore + record.toString());
			}
		}

	}

}
