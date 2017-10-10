package model.taskmanager.task;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public interface WebServerTask {
	default Invocation.Builder getInvocationBuilder(String url) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		return webTarget.request();

	}

	default boolean checkResponse(Response response) {
		if (response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());

		} else
			return true;
	}

	Response doGet(String Url);

	Response doPost(String Url, Object obj);

	void doTask();

	String getCliId();

	String getTaskName();

	Response getResponse();
}
