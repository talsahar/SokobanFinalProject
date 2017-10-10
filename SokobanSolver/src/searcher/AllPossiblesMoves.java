/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package searcher;

import java.util.HashMap;
import java.util.Map.Entry;

import run.Position;
import solution.Action;
import state.State;

public class AllPossiblesMoves {

	private SearchPolicy policy;
	private NextStateBuilder nextStateBuilder;

	public AllPossiblesMoves(SearchPolicy searchPolicy) {
		policy = searchPolicy;
		this.nextStateBuilder = new NextStateBuilder();
	}

	public char[][] copyBoard(char[][] board) {
		char[][] copy = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				copy[i][j] = board[i][j];
			}
		}
		return copy;
	}

	private HashMap<Action, Integer[]> getMap() {
		HashMap<Action, Integer[]> myMap = new HashMap<>();

		Integer[] left = { -1, 0 };
		Integer[] right = { 1, 0 };
		Integer[] down = { 0, 1 };
		Integer[] up = { 0, -1 };
		myMap.put(new Action("move up"), up);
		myMap.put(new Action("move down"), down);
		myMap.put(new Action("move left"), left);
		myMap.put(new Action("move right"), right);
		return myMap;

	}

	public HashMap<Action, State<SokobanState>> getPossiblesMap(State<SokobanState> state) {

		HashMap<Action, State<SokobanState>> possiblesStates = new HashMap<>();

		Position p = state.getState().getPointer();
		char[][] board = state.getState().getBoard();

		HashMap<Action, Integer[]> rMap = getMap();

		for (Entry<Action, Integer[]> entry : rMap.entrySet()) {
			Action action = entry.getKey();
			Integer[] iArr = entry.getValue();

			Position p1 = new Position(p.getX(), p.getY());
			Position p2 = new Position(p1.getX() + iArr[0], p1.getY() + iArr[1]);
			Position p3 = new Position(p2.getX() + iArr[0], p2.getY() + iArr[1]);

			if (policy.isLegalMove(board, p1, p2, p3)) {
				char[][] nextState = nextStateBuilder.getNextState(copyBoard(board), p1, p2, p3);
				State<SokobanState> possible = new State<SokobanState>(new SokobanState(nextState, p2));
				possiblesStates.put(action, possible);
			}
		}

		return possiblesStates;

	}

}
