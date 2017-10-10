package services;

import java.io.IOException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import planner.SokobanPlannable;
import planner.Strips;
import run.SokobanSolver;
import searcher.BestFS;
import xml.State;

@Path("solver")
public class Solver {

	@POST
	@Produces(MediaType.APPLICATION_XML)
	public db.Solution askForSolution(State state) {

		try {
			db.Solution solution = new db.Solution();
			SokobanSolver solver = new SokobanSolver(new Strips(), new BestFS<>());
			solver.solve(new SokobanPlannable(state.getState()));
			solution.setMySolution(solver.solve(new SokobanPlannable(state.getState())).toString());
			solution.setSolutionHash("" + state.getState().hashCode());
			return solution;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
