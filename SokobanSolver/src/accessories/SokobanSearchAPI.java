/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package accessories;

import planner.MyClause;
import run.Position;
import searcher.BestFS;
import searcher.MySearchable;
import searcher.Searcher;
import searcher.SokobanState;
import solution.Solution;

//here you can change the search alg
public class SokobanSearchAPI {

	private Searcher<SokobanState> alg;
	private PredicateToState stateTranslate;

	private SokobanSearchAPI() {
		alg = new BestFS<>();
		stateTranslate = new PredicateToState();

	}

	public void setAlg(Searcher<SokobanState> s) {
		alg = s;
	}

	public Solution getSearchSolution(MyClause c, Position p1, Position p2) {
		SokobanState state = stateTranslate.clauseToState(c);
		return alg.search(new MySearchable(state.getBoard(), p1, p2));
	}

	public boolean isClearPath(MyClause c, Position p1, Position p2) {

		return getSearchSolution(c, p1, p2) == null ? false : true;
	}

	private static class Holder {
		public static final SokobanSearchAPI instance = new SokobanSearchAPI();
	}

	public static SokobanSearchAPI getInstance() {
		return Holder.instance;
	}

}
