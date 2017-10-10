/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package solution;

import java.util.LinkedList;

public class Solution {

	private LinkedList<Action> actions;
	private boolean failed;

	public Solution(LinkedList<Action> newList) {
		actions = newList;
	}

	public void setfailed() {
		failed = true;
	}

	public boolean isFailed() {
		return failed;
	}

	public void add(Action action) {
		actions.add(action);
	}

	public Solution() {
		actions = new LinkedList<>();
	}

	public void appendSolution(Solution other) {
		LinkedList<Action> otherActions = other.getActions();
		while (!otherActions.isEmpty())
			actions.add(otherActions.removeFirst());

	}

	public LinkedList<Action> getActions() {
		return actions;
	}

	public void setActions(LinkedList<Action> actions) {
		this.actions = actions;
	}

	@Override
	public String toString() {
		String s = "";
		for (Action a : actions) {
			s += a.getName();
			s += '\n';
		}
		return s;
	}

}
