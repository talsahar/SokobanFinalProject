package controller.command.files;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class LoadLevelCommand extends SokobanCommand {

	public LoadLevelCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.LOAD);
	}

	@Override
	public void execute() {
		if (!params.isEmpty())
			model.load(params.remove(0));

	}

}
