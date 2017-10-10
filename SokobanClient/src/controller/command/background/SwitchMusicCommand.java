package controller.command.background;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class SwitchMusicCommand extends SokobanCommand {

	public SwitchMusicCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.SwitchMusic);
	}

	@Override
	public void execute() {
		switch (params.remove(0)) {
		case "on":
			model.getMusic().on();
			break;
		case "off":
			model.getMusic().off();
			break;
		}
	}

}
