/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package model;

import java.util.Observable;
import java.util.Observer;

import app.conf.Conf;
import model.server.SokobanServer;
import model.taskmanager.TaskManager;
import model.taskmanager.task.LoadRecordsTask;
import model.taskmanager.task.SaveRecordsTask;
import model.taskmanager.task.SolveTask;

public class MyModel extends Observable implements Model, Observer {

	private SokobanServer sokobanServer;
	private TaskManager taskManager;
	private int maxConnections = Conf.maxConnectionsNumber;
	private int port;

	@Override
	public void runServer(String id, String pass) {

		// boolean verifyStatus = new VerifyAdmin().verify(id, pass);
		boolean verifyStatus = true;// commit it
		if (verifyStatus) {
			taskManager = new TaskManager(maxConnections);
			taskManager.addObserver(this);
			sokobanServer = new SokobanServer(maxConnections);
			sokobanServer.addObserver(this);
			sokobanServer.startServer(port);
		} else {
			setChanged();
			notifyObservers("Incorrect Login Fields");
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		if (arg0 instanceof SokobanServer) {
			String s = (String) arg1;
			if (s.startsWith(Conf.recordQuery) || s.startsWith(Conf.solveCommand) || s.startsWith(Conf.saveRecord)) {
				String[] array = s.split("_");
				if (s.startsWith(Conf.recordQuery))
					taskManager.addTask(new LoadRecordsTask(array[2], array[1], taskManager,
							(res) -> sendMessage(array[2], Conf.recordRespondCommand + res)));
				else if (s.startsWith(Conf.solveCommand))
					taskManager.addTask(new SolveTask(array[2], array[1], taskManager,
							(res) -> sendMessage(array[2], Conf.solutionCommand + res)));
				else if (s.startsWith(Conf.saveRecord))
					taskManager.addTask(new SaveRecordsTask(array[2], array[1], taskManager));

				return;
			}

		}

		setChanged();
		notifyObservers(arg1);

	}

	@Override
	public void setMaxConnections(int max) {
		maxConnections = max;
		setChanged();
		notifyObservers("Your max connections is now " + max);
	}

	@Override
	public void sendMessage(String id, String message) {
		sokobanServer.sendMessage(id, message);
	}

	@Override
	public void stopServer() {
		sokobanServer.stopServer();
	}

	@Override
	public void kickClient(String id) {
		sokobanServer.removeClient(id);
	}

	@Override
	public void endProcess(String taskId) {
		taskManager.endTask(taskId);
	}

	@Override
	public void setPort(int port) {
		this.port = port;

	}

	private static class Holder {
		public static final Model instance = new MyModel();
	}

	public static Model getInstance() {
		return Holder.instance;
	}

}
