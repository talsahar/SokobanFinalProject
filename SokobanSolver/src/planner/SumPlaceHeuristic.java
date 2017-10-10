/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package planner;

import action.Clause;
import run.Position;

public class SumPlaceHeuristic implements Heuristic {

	@Override // finds closest hole
	public Clause decomposeGoal(Clause kb, Clause goal) {
		Position playerPlace = ((MyClause) kb).getPlayerPosition();
		int playerSum = playerPlace.getX() + playerPlace.getY();

		goal.getPredicates().sort((o1, o2) -> {
			Position p1 = ((MyPredicate) o1).getPosition();
			Position p2 = ((MyPredicate) o2).getPosition();
			return (Math.abs(playerSum - (p2.getX() + p2.getY()))) - (Math.abs((playerSum - (p1.getX() + p1.getY()))));
		});
		return goal;
	}

}
