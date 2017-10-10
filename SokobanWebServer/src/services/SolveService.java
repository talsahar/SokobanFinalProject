package services;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import connection.GenericREST;
import db.Solution;
import xml.State;

@Path("solution")
public class SolveService {

	private Solution fromCache(String hashCodde) {
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/SokobanWebServer/cache/load/" + hashCodde);
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_XML);
		return invocationBuilder.get(Solution.class);
	}

	private Solution fromSolver(State state) {
		Response response = GenericREST.getInstance().genericPost("http://localhost:8080/SokobanWebServer/solver/",
				state);
		return response.readEntity(Solution.class);
	}

	private void saveSolution(Solution solution) {
		GenericREST.getInstance().genericPost("http://localhost:8080/SokobanWebServer/cache/", solution);
	}

	@POST
	@Produces(MediaType.APPLICATION_XML)
	public Solution solve(State state) {

		Solution solution = fromCache("" + state.getState().hashCode());

		if (solution == null) {
			solution = fromSolver(state);
			if (solution == null)
				return null;
			saveSolution(solution);
		}

		return solution;

	}

}
