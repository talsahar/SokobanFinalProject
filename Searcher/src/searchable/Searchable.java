/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package searchable;

import java.util.HashMap;

import solution.Action;
import state.State;

public interface Searchable<T> {

	HashMap<Action, State<T>> getAllPossiblesMove(State<T> state);

	State<T> getGoalState();

	State<T> getInitialState();

	int getCostOfStateAndAction(State<T> currState, Action action);

	boolean isSatisfiedSolution(State<T> currState);

}
