package controller.command.server;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class DisconnectServerCommand extends SokobanCommand {

	public DisconnectServerCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.DISCONNECTSERVER);
	}

	@Override
	public void execute() {
		client.disconnect();

	}

}
