/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package run;

import plannable.Plannable;
import planner.Planner;
import planner.SumPlaceHeuristic;
import searcher.Searcher;
import solution.Solution;

public class SokobanSolver {

	Planner planner;
	Searcher searcher;

	public SokobanSolver(Planner planner, Searcher searcher) {
		this.planner = planner;
		this.searcher = searcher;
	}

	public Solution solve(Plannable plannable) {
		return planner.plan(plannable, new SumPlaceHeuristic());

	}
}
