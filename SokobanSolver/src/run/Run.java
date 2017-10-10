/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package run;

import java.io.IOException;

import planner.SokobanPlannable;
import planner.Strips;
import searcher.BestFS;
import solution.Solution;

/**
 * Sokoban Solver library is a separate library which responsible for solving
 * Sokoban levels. When a user fails to resolve a level, he may request a
 * solution from the server. The Sokoban Solver library represented as jar used
 * by web server's Solver service.
 * 
 * In the Sokoban Solver we decoupled the algorithm from the problem it solves
 * using the "dependency injection" technique. It's because we don’t want to
 * duplicate the code we want the algorithms to be static and will use it on
 * other domains. 
 * First, we were written the generic part, the independent algorithms: 
 * 
 * Searcher 
 * The searcher interface provides a search (Searchable s) statement. 
 * Best-first-search and Dijkstra classes which are responsible for
 * searching the quickest path between source and destination will implement
 * this interface and will generically will take as argument any type of
 * Searchable which represents the policy and the following states: source and
 * destination. in our case Sokoban Searchable which the source and des are
 * two-dimensional arrays, with the Sokoban's policy. 
 * 
 * Planner 
 * The planner interface provides a solve (Plannable p, Heuristic h) statement. 
 * The planner algorithm called Strips maintains a stack, getting the domain's knowledgebase
 * and goal-state, both represented by predicates, will insert the goal-state
 * predicates to the stack will try to provide the unsatisfied predicates and
 * update the knowledgebase by the policy of our current domain and will use the
 * searching algorithm for searching purposes.
 *
 * 
 * 
 * 
 */
public class Run {
	public static void main(String[] args) {

		SokobanPlannable plannable = null;
		try {
			plannable = new SokobanPlannable("example.txt");

		} catch (IOException e) {
			e.printStackTrace();
		}
		SokobanSolver solver = new SokobanSolver(new Strips(), new BestFS<>());

		Solution solution = solver.solve(plannable);

		System.out.println("---------\nsolution\n----------\n" + solution);
		SolutionWriter writer = new SolutionWriter();
		writer.writeSolution("solution.txt", solution);

	}

}
