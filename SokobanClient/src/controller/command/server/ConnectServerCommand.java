package controller.command.server;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class ConnectServerCommand extends SokobanCommand {

	public ConnectServerCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.CONNECTSERVER);
	}

	@Override
	public void execute() {
		client.start();
	}

}
