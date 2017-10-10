package controller.command.background;

import controller.Controller;
import controller.command.SokobanCommand;
import model.Model;
import others.CommandNames;
import server.MyClient;
import view.View;

public class MsgCommand extends SokobanCommand {

	public MsgCommand(View v, Model m, Controller c, MyClient cli) {
		super(v, m, c, cli, CommandNames.MESSAGE);
	}

	@Override
	public void execute() {

		String str = "";
		for (String s : params)
			str += (s + " ");
		view.setMessage(str);

	}
}
