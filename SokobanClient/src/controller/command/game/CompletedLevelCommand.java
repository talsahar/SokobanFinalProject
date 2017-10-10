package controller.command.game;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class CompletedLevelCommand extends SokobanCommand {

	public CompletedLevelCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.COMPLETED);
	}

	@Override
	public void execute() {

		view.levelCompleted(model.getSteps().get(), model.getTimeCounter().getProperty().get());
	}

}
