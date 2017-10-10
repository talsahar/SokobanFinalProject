package model.taskmanager.task;

import java.util.Observable;
import java.util.Observer;
import java.util.function.Consumer;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public abstract class WSTask extends Observable implements WebServerTask {

	protected String cliID;
	protected String taskName;
	protected String param;
	protected Response response;
	protected Consumer<String> onTaskOver;

	public WSTask(String clidID, String taskName, String param, Observer o, Consumer<String> onTaskOver) {
		this.cliID = clidID;
		this.taskName = taskName;
		this.param = param;
		this.onTaskOver = onTaskOver;
		addObserver(o);
	}

	public abstract void doTask();

	@Override
	public Response doGet(String url) {

		response = getInvocationBuilder(url).accept(MediaType.APPLICATION_XML).get();
		return checkResponse(response) ? response : null;

	}

	public Response doPost(String url, Object obj) {
		return response = getInvocationBuilder(url).post(Entity.entity(obj, MediaType.APPLICATION_XML));
	}

	@Override
	public String getCliId() {
		return cliID;
	}

	@Override
	public String getTaskName() {
		return taskName;

	}

	@Override
	public Response getResponse() {
		return response;
	}

}
