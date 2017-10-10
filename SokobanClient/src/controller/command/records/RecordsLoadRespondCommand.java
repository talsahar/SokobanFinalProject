package controller.command.records;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class RecordsLoadRespondCommand extends SokobanCommand {

	public RecordsLoadRespondCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.ServerLoadRecordsRespond);
	}

	@Override
	public void execute() {
		String response = "";
		while (!params.isEmpty())
			response += params.remove(0) + " ";

		view.showChamionsList(model.analyzeServersScoreRespond(response));
	}

}
