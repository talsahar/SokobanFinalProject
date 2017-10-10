package controller.command.background;

import controller.Controller;
import controller.command.SokobanCommand;
import javafx.application.Platform;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class ExitCommand extends SokobanCommand {

	public ExitCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.EXIT);
	}

	@Override
	public void execute() {
		model.getMusic().off();
		client.disconnect();
		Platform.exit();

	}

}
