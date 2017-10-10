/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package sokobanActions;

import accessories.PredCreator;
import planner.MyClause;
import planner.MyPredicate;

public class MovePlayer extends MyAction {

	public MovePlayer(MyClause knowledgeBase, String p2, PredCreator creator) {
		super("movePlayer", "", "", creator);
		MyPredicate player = (MyPredicate) knowledgeBase.getPlayerPredicate();
		MyPredicate des = (MyPredicate) knowledgeBase.getPredicateByValue(p2);

		value = player.getValue() + "," + des.getValue();

		precondition.addFirst(player);
		precondition.addFirst(des);
		precondition.addFirst(new MyPredicate("clearPath", "", value));

		if (player.isPlayer())
			effects.addLast(creator.createClear(player.getValue()));
		else
			effects.addLast(creator.createHole(player.getID(), player.getValue()));
		if (des.isHole())
			effects.addLast(creator.createPlayerHole(des.getID(), des.getValue()));

		else if (des.isClear())
			effects.addLast(creator.createPlayer(des.getValue()));

		updateActionPlan(knowledgeBase);

	}

}
