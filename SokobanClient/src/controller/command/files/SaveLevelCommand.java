package controller.command.files;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class SaveLevelCommand extends SokobanCommand {

	public SaveLevelCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.SAVE);
	}

	@Override
	public void execute() {
		if (!params.isEmpty())
			model.save(params.remove(0));
	}

}
