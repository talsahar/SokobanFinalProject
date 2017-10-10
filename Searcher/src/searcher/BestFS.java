/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package searcher;

import java.util.HashMap;
import java.util.PriorityQueue;

import searchable.Searchable;
import solution.Action;
import solution.Solution;
import state.State;

public class BestFS<T> implements Searcher<T> {

	public State<T> getFromClosed(State<T> state, PriorityQueue<State<T>> closed) {
		for (State<T> s : closed)
			if (s.equals(state)) {
				return s;
			}

		return null;

	}

	public void updateOpenIfNeed(State<T> state, PriorityQueue<State<T>> open) {
		for (State<T> old : open)
			if (old.equals(state) && (old.getCost() > state.getCost())) {
				old = state;
				break;
			}

	}

	@Override
	public Solution search(Searchable<T> searchable) {
		PriorityQueue<State<T>> open = new PriorityQueue<>();
		PriorityQueue<State<T>> closed = new PriorityQueue<>();
		State<T> initState = searchable.getInitialState();
		initState.setCost(0);
		open.add(initState);

		while (!open.isEmpty()) {

			State<T> currState = open.poll();
			closed.add(currState);
			if (searchable.isSatisfiedSolution(currState))
				return buildSolution(currState);
			HashMap<Action, State<T>> successors = searchable.getAllPossiblesMove(currState);
			successors.forEach((action, newState) -> {
				newState.setProperties(currState, searchable.getCostOfStateAndAction(currState, action), action);

				if (!open.contains(newState) && !closed.contains(newState))
					open.add(newState);

				else if (closed.contains(newState)) {
					if (isBetterPath(getFromClosed(newState, closed), newState)) {
						closed.remove(newState);
						open.add(newState);
					}
				}

				else if (open.contains(newState))
					updateOpenIfNeed(newState, open);
			});
		}
		Solution solution = new Solution();
		solution.setfailed();
		return solution;
	}

}
