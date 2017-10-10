/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package searcher;

import java.util.HashMap;

import run.Position;
import searchable.Searchable;
import solution.Action;
import state.State;

public class MySearchable implements Searchable<SokobanState> {
	AllPossiblesMoves neighborGet;
	GoalStateBuilder goalStateBuilder;
	private char[][] lvl;
	private Position sPlace;
	private Position gPlace;

	public MySearchable(char[][] lvl, Position s, Position g) {
		this.lvl = lvl;
		this.sPlace = s;
		this.gPlace = g;
		neighborGet = new AllPossiblesMoves(new SearchPolicy());
		goalStateBuilder = new GoalStateBuilder();

	}

	@Override
	public State<SokobanState> getInitialState() {

		return new State<SokobanState>(new SokobanState(lvl, sPlace));

	}

	@Override
	public State<SokobanState> getGoalState() {
		if (lvl[gPlace.getY()][gPlace.getX()] == 'o' || lvl[gPlace.getY()][gPlace.getX()] == ' ')
			return new State<SokobanState>(
					new SokobanState(goalStateBuilder.buildGoalState(copyBoard(lvl), sPlace, gPlace), gPlace));

		System.out.println("searchable:: no legal des");
		return null;

	}

	@Override
	public HashMap<Action, State<SokobanState>> getAllPossiblesMove(State<SokobanState> state) {
		return neighborGet.getPossiblesMap(state);
	}

	public static Position getPlayerPosition(char board[][]) {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[0].length; j++)
				if (board[i][j] == 'A' || board[i][j] == 'a')
					return new Position(j, i);
		return null;
	}

	private char[][] copyBoard(char[][] board) {

		char[][] copy = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board[i].length; j++)
				copy[i][j] = board[i][j];

		return copy;
	}

	@Override
	public int getCostOfStateAndAction(State<SokobanState> currState, Action action) {
		return currState.getCost() + 1;
	}

	@Override
	public boolean isSatisfiedSolution(State<SokobanState> currState) {
		return currState.getState().getPointer().equals(gPlace);

	}

}
