package controller.command.background;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class BindCommand extends SokobanCommand {

	public BindCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.BIND);
	}

	@Override
	public void execute() {
		view.getStepsField().textProperty().bind(model.getSteps());
		view.getTimeField().textProperty().bind(model.getTimeCounter().getProperty());
	}

}
