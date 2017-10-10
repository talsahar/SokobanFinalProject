/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package sokobanActions;

import accessories.PredCreator;
import planner.MyClause;
import planner.MyPredicate;
import run.Position;
import solution.Action;

public class CoverHole extends MyAction {

	public CoverHole(MyClause knowledgeBase, String holeValue, PredCreator creator) {
		super("coverHole", "", "", creator);

		MyPredicate player = (MyPredicate) knowledgeBase.getPlayerPredicate();
		MyPredicate box = (MyPredicate) knowledgeBase.getClosestBoxPredicate();
		MyPredicate hole = (MyPredicate) knowledgeBase.getPredicateByValue(holeValue);
		value = box.getValue() + "," + hole.getValue();

		updateActionPlan(knowledgeBase);
		if (getActionPlan().isFailed()) {
			isFailed = true;
			return;
		}

		precondition.addFirst(player);
		precondition.addFirst(box);
		precondition.addFirst(hole);
		precondition.addFirst(new MyPredicate("clearPath", "", value));

		MyClause newKb = knowledgeBase.getCopy();
		clearCopyFromPlayer(newKb);

		Position boxPos = box.getPosition();
		for (Action stepName : actionPlan.getActions()) {
			PushBox action = new PushBox(newKb, stepName.getName(), boxPos, creator);
			boxPos = action.getNewBoxPos();
			precondition.addFirst(action.getPreconditions());
			precondition.addFirst(action);
			newKb.update(action.getEffects());
		}

		effects.addLast(creator.createBoxHole(hole.getID(), holeValue));
		actionPlan.getActions().clear();// must be clear
	}

	private void clearCopyFromPlayer(MyClause newKb) {
		if (((MyPredicate) newKb.getPlayerPredicate()).isPlayerHole())
			newKb.update(creator.createHole(newKb.getPlayerPredicate().getID(), newKb.getPlayerPosition().toString()));
		else
			newKb.update(creator.createClear(newKb.getPlayerPosition().toString()));
	}
}
