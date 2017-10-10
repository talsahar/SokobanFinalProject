/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package planner;

import java.util.Stack;

import accessories.SokobanSearchAPI;
import action.Clause;
import action.Predicate;
import run.Position;

public class MyClause extends Clause {
	private Stack<MyPredicate> boxes;

	public MyClause(Predicate... predicates) {
		super(predicates);

		boxes = new Stack<>();

	}

	@Override
	public void addLast(Predicate p) {
		super.addLast(p);
		if (((MyPredicate) p).isBox())
			boxes.add((MyPredicate) p);
	}

	public Stack<MyPredicate> getBoxes() {
		return boxes;
	}

	public String getBoxValue(String id) {
		for (MyPredicate box : boxes) {
			if (box.getID().equals(id))
				return box.getValue();
		}
		return null;
	}

	public MyClause getCopy() {

		MyClause copy = new MyClause();
		getPredicates().forEach(p -> copy.addLast(p));
		return copy;

	}

	/*
	 * public String getPlayerPlace() { return getPredicate("playerAt",
	 * null).getValue();
	 * 
	 * }
	 */
	public Position getPlayerPosition() {
		if (isPlayerOnHole())
			return ((MyPredicate) getPredicateByType("playerHoleAt")).getPosition();
		return ((MyPredicate) getPredicateByType("playerAt")).getPosition();
	}

	public boolean isPlayerOnHole() {
		return getPredicateByType("playerHoleAt") == null ? false : true;
	}

	public Predicate getPlayerPredicate() {
		Predicate p1 = getPredicateByType("playerHoleAt");
		Predicate p2 = getPredicateByType("playerAt");
		return p1 == null ? p2 : p1;

	}

	@Override
	public boolean isSatisfied(Predicate p) {

		if (p instanceof MyPredicate) {
			if (((MyPredicate) p).isClearPath()) {
				SokobanSearchAPI searcher = SokobanSearchAPI.getInstance();
				Position[] pos = ((MyPredicate) p).getDoublePosition();
				return searcher.isClearPath(this, pos[0], pos[1]) ? true : false;
			}
		}

		return super.isSatisfied(p);
	}

	@Override
	public void update(Predicate effect) {
		super.update(effect);

	}

	@Override
	public void update(Clause effect) {
		super.update(effect);

	}

	public Predicate getClosestBoxPredicate() {
		Position playerpos = getPlayerPosition();
		int playerSum = playerpos.getX() + playerpos.getY();

		MyPredicate closest = null;
		int closestSum = Integer.MAX_VALUE;
		for (MyPredicate box : boxes) {
			Position boxPos = box.getPosition();
			int boxSum = boxPos.getX() + boxPos.getY();
			int calculated = Math.abs(playerSum - boxSum);
			if (calculated < closestSum) {
				closestSum = calculated;
				closest = box;
			}
		}
		if (closest != null)
			boxes.remove(closest);
		return closest;
	}

}