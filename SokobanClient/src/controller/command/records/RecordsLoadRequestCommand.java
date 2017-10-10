package controller.command.records;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class RecordsLoadRequestCommand extends SokobanCommand {

	public RecordsLoadRequestCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.ServerLoadRecordsRequest);
	}

	@Override
	public void execute() {
		if (!client.isConnected()) {
			view.setMessage("error: disconnected from server");
			view.mainMenuShow();
		} else {
			String request = CommandNames.ServerLoadRecordsRequest;
			while (!params.isEmpty()) {
				String tmp = params.remove(0);
				if (tmp.equals("getLevel"))
					request += "level=" + model.getLevelName() + '&';
				else
					request += tmp + '&';
			}
			if (request.endsWith("&"))
				request = request.substring(0, request.length() - 1);
			client.send(request);

		}

	}

}
