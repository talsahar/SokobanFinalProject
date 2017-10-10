package connection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GenericREST {

	public Response genericPost(String url, Object obj) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request();
		Response response = invocationBuilder.post(Entity.entity(obj, MediaType.APPLICATION_XML));

		return response;

	}

	public Response genericGet(String url) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		return invocationBuilder.get();

	}

	public static GenericREST getInstance() {
		return new GenericREST();
	}

	public Response genericPost() {

		return null;

	}

}
