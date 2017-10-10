package services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import connection.DBConnection;
import db.Solution;

@Path("cache")
public class SolutionCache {

	@GET
	@Path("/load/{levelHash}")
	@Produces(MediaType.APPLICATION_XML)
	public Solution askForSolution(@PathParam("levelHash") String levelHash) {

		String query = "from Solution where SolutionHash='" + levelHash + "'";
		List<Solution> result = DBConnection.getInstance().queryForDatabase(query);
		return result.isEmpty()?null:result.get(0);

	}

	@POST
	public void saveSolution(Solution solution) {
		DBConnection.getInstance().insertToDataBase(solution);
	}

}
