/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package accessories;

import action.Predicate;
import planner.MyClause;
import planner.MyPredicate;
import run.Position;
import searcher.SokobanState;

public class PredicateToState {
	public SokobanState clauseToState(MyClause c) {
		Position maxPlace = getMaxPlace(c);
		char[][] board = new char[maxPlace.getY() + 1][maxPlace.getX() + 1];
		for (Predicate p : c.getPredicates()) {
			MyPredicate castedP = (MyPredicate) p;
			if (p.getType().endsWith("At")) {
				char ch = 0;
				if (castedP.isWall())
					ch = '#';
				if (castedP.isClear())
					ch = ' ';
				if (castedP.isBox())
					ch = '@';
				if (castedP.isPlayer())
					ch = 'A';
				if (castedP.isHole())
					ch = 'o';
				if (castedP.isBoxHole())
					ch = 'X';
				if (castedP.isPlayerHole())
					ch = 'a';
				Position place = castedP.getPosition();
				board[place.getY()][place.getX()] = ch;
			}
		}

		return new SokobanState(board, null);

	}

	private Position getMaxPlace(MyClause c) {
		int maxX = 0;
		int maxY = 0;

		for (Predicate p : c.getPredicates()) {
			if (p.getType().endsWith("At")) {
				Position place = ((MyPredicate) p).getPosition();
				int v1 = place.getX();
				int v2 = place.getY();
				if (v1 > maxX)
					maxX = v1;
				if (v2 > maxY)
					maxY = v2;
			}
		}

		return new Position(maxX, maxY);

	}
}
