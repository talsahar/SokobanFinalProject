/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package model.taskmanager;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.conf.Conf;
import model.taskmanager.task.WebServerTask;

/**
 * 
 * responsible for handling tasks from client to server using thread pool.
 * notifies when done.
 *
 */

public class TaskManager extends Observable implements Observer {

	private HashMap<String, ExecutorService> handlerExecutor;

	public TaskManager(int maxConnections) {
		handlerExecutor = new HashMap<>(maxConnections);

	}

	public void addTask(WebServerTask t) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(() -> t.doTask());
		handlerExecutor.put(t.getCliId(), executor);
		setChanged();
		notifyObservers(Conf.newProcessNotify + t.getCliId() + " " + t.getTaskName());

	}

	public void endTask(String taskId) {
		String notifyStr = "";
		if (!handlerExecutor.containsKey(taskId))
			notifyStr = Conf.noSuchAnIDExsistNotify + " on task manager " + taskId;

		else {
			if (!handlerExecutor.get(taskId).isTerminated())
				handlerExecutor.remove(taskId).shutdown();
			notifyStr = Conf.overProcessNotify + taskId;
		}
		setChanged();
		notifyObservers(notifyStr);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		endTask(((WebServerTask) arg0).getCliId());
	}

}
