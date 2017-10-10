/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package state;

import solution.Action;

public class State<T> implements Comparable<State<T>> {

	private T state;
	private State<T> cameFrom;
	private Action action;
	private int cost;

	public State(T state) {
		this.state = state;
	}

	@Override
	public int compareTo(State<T> o) {
		return this.cost - o.cost;

	}

	@Override
	public boolean equals(Object obj) {
		State<T> s = (State<T>) obj;
		return s.state.equals(state);
	}

	public Action getAction() {
		return action;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public int getCost() {
		return cost;
	}

	// Getters & Setters
	public T getState() {
		return state;
	}

	@Override
	public int hashCode() {

		return state.hashCode();
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setState(T state) {
		this.state = state;
	}

	@Override
	public String toString() {

		return state.toString();
	}

	public void setProperties(State<T> cameFrom, int cost, Action action) {
		this.cameFrom = cameFrom;
		this.cost = cost;
		this.action = action;

	}

}
