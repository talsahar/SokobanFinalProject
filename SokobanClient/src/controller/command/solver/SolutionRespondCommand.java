package controller.command.solver;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import others.MyMapper;
import server.MyClient;
import view.View;

public class SolutionRespondCommand extends SokobanCommand {
	public SolutionRespondCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.ServerSolutionRespond);
	}

	@Override
	public void execute() {

		if (params.isEmpty()) {
			view.setMessage("No solution");
			return;
		}
		switch (view.hintOrSolution()) {
		case 2:// animation
			model.showSolutionAnimation(params.remove(0));

			break;

		case 1:
			String hint = new MyMapper<Character, String>().add('u', "Hint: Move UP").add('d', "Hint: Move DOWN")
					.add('l', "Hint: Move LEFT").add('r', "Hint: Move Right").getByKey(params.remove(0).charAt(0));
			view.setMessage(hint);
			break;
		case 0:
			view.setMessage("Solution Display Canceled");
		}

	}

}
