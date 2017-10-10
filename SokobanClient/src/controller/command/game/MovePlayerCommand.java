package controller.command.game;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class MovePlayerCommand extends SokobanCommand {

	public MovePlayerCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.MOVE);
	}

	@Override
	public void execute() {
		if (!params.isEmpty())
			model.move(params.remove(0));

	}

}
 	