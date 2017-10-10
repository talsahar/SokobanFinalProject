package controller.command.files;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class RestartLevelCommand extends SokobanCommand {

	public RestartLevelCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.RESTART);
	}

	@Override
	public void execute() {
		model.restart();

	}

}
