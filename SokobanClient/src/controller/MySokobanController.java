package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import controller.command.Command;
import controller.command.SokobanCommand;
import model.Model;
import server.MyClient;
import view.View;

public class MySokobanController implements Observer, Controller {

	private ExecutorService executor;
	private Map<String, Command> commandMap;

	private MySokobanController() {
		executor = Executors.newSingleThreadExecutor();
		commandMap = new HashMap<>();
	}

	public void intCommandMap(View view, Model model, Controller controller, MyClient cli) {
		new CommandClassLoader().initMap(commandMap, view, model, controller, cli);
	}

	@Override
	public void update(Observable o, Object arg) {

		List<String> params = msgToLinkString((String) arg);
		SokobanCommand command = (SokobanCommand) commandMap.get(params.remove(0));

		command.setParams(params);

		executor.execute(() -> command.execute());
	}

	public List<String> msgToLinkString(String msg) {

		String[] arr = msg.split(" ");
		List<String> params = new LinkedList<String>();

		for (String s : arr)
			params.add(s);

		return params;

	}

	public static class Holder {
		public static final MySokobanController instance = new MySokobanController();
	}

	public static MySokobanController getInstance() {
		return Holder.instance;
	}

	@Override
	public void stop() {
		executor.shutdownNow();
	}

}
