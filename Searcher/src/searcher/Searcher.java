/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package searcher;

import java.util.LinkedList;

import searchable.Searchable;
import solution.Action;
import solution.Solution;
import state.State;

public interface Searcher<T> {

	Solution search(Searchable<T> s);

	default Solution buildSolution(State<T> goalState) {

		LinkedList<Action> list = new LinkedList<>();
		while (goalState.getCameFrom() != null) {
			list.addFirst(goalState.getAction());
			goalState = goalState.getCameFrom();

		}
		return new Solution(list);

	}

	default boolean isBetterPath(State<T> oldState, State<T> newState) {
		return oldState.getCost() > newState.getCost();
	}

}
