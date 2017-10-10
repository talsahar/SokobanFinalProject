/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package sokobanActions;

import accessories.PredCreator;
import accessories.SokobanSearchAPI;
import action.AbstractAction;
import planner.MyClause;
import run.Position;

public abstract class MyAction extends AbstractAction {
	PredCreator creator;

	public MyAction(String type, String id, String value, PredCreator creator) {
		super(type, id, value);
		this.creator = creator;
		precondition = new MyClause();
		effects = new MyClause();
	}

	public void updateActionPlan(MyClause clause) {

		String[] arr = value.split(",");
		Position s = new Position(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
		Position d = new Position(Integer.parseInt(arr[2]), Integer.parseInt(arr[3]));
		actionPlan = SokobanSearchAPI.getInstance().getSearchSolution(clause, s, d);
	}

}
