package controller.command.solver;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class SolutionRequestCommand extends SokobanCommand {

	public SolutionRequestCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.ServerSolutionRequest);
	}

	@Override
	public void execute() {
		if (!model.getLevel().isExist())
			view.setMessage("level not exists");
		else if (!client.isConnected())
			view.setMessage("error: disconnected from server");

		else {

			String s = CommandNames.ServerSolutionRequest + model.getLevel().toURL();
			view.setMessage("Solve request sent to solver");
			client.send(s);

		}
	}

}
