package controller.command.background;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class SetTimerCommand extends SokobanCommand {

	public SetTimerCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.SetTimer);
	}

	@Override
	public void execute() {
		if (!params.isEmpty())
			switch (params.remove(0)) {
			case "on":
				model.getTimeCounter().startCounting();
				break;
			case "off":
				model.getTimeCounter().stopCounting();
				break;
			}
	}
}
