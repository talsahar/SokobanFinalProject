/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package sokobanActions;

import accessories.PredCreator;
import action.Predicate;
import planner.MyClause;
import planner.MyPredicate;
import run.Position;
import solution.Action;
import solution.Solution;

public class PushBox extends MyAction {
	private Position newBoxPos;

	public PushBox(MyClause newKb, String way, Position boxPos, PredCreator creator) {
		super("pushBox", "", way, creator);

		String[] values = getPlaceValues(way, boxPos);
		MyPredicate behindBox = (MyPredicate) newKb.getPredicateByValue(values[0]);
		MyPredicate box = (MyPredicate) newKb.getPredicateByValue(values[1]);
		MyPredicate des = (MyPredicate) newKb.getPredicateByValue(values[2]);

		Predicate player = null;
		if (behindBox.isHole() || behindBox.isPlayerHole()) {
			player = new MyPredicate("playerHoleAt", behindBox.getID(), behindBox.getValue());
			effects.addLast(new MyPredicate("holeAt", player.getID(), player.getValue()));
		}

		else if (behindBox.isClear() || behindBox.isPlayer()) {
			player = new MyPredicate("playerAt", behindBox.getID(), behindBox.getValue());
			effects.addLast(new MyPredicate("clearAt", player.getID(), player.getValue()));

		}

		precondition.addLast(player);
		precondition.addLast(box);
		precondition.addLast(des);

		if (box.isBoxHole())
			effects.addLast(new MyPredicate("playerHoleAt", box.getID(), box.getValue()));
		else if (box.isBox())
			effects.addLast(new MyPredicate("playerAt", box.getID(), box.getValue()));

		if (des.isHole())
			effects.addLast(new MyPredicate("boxHoleAt", des.getID(), des.getValue()));
		else if (des.isClear())
			effects.addLast(new MyPredicate("boxAt", des.getID(), des.getValue()));

		updateActionPlan(newKb);
		newBoxPos = des.getPosition();
	}

	public String[] getPlaceValues(String way, Position boxPos) {
		int px, py, dx, dy;
		px = dx = boxPos.getX();
		py = dy = boxPos.getY();
		switch (way) {
		case "move up":
			py++;
			dy--;
			break;
		case "move down":
			py--;
			dy++;
			break;
		case "move left":
			px++;
			dx--;
			break;
		case "move right":
			px--;
			dx++;
			break;
		}

		String pDest = "" + dx + "," + dy;
		String pPlayer = "" + px + "," + py;

		String[] array = { pPlayer, boxPos.toString(), pDest };
		return array;

	}

	@Override
	public void updateActionPlan(MyClause clause) {
		// plan of 1 step of player
		actionPlan = new Solution();
		actionPlan.add(new Action(value));
	}

	public Position getNewBoxPos() {
		return newBoxPos;
	}

}
