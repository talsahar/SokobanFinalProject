package controller.command.game;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class DisplayLevelCommand extends SokobanCommand {

	public DisplayLevelCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.DISPLAY);
	}

	@Override
	public void execute() {

		view.displayLevel(model.getLevel().getMyLevel(), model.getLevel().getPlayer().getPlace().getX(),
				model.getLevel().getPlayer().getPlace().getY());

	}

}
