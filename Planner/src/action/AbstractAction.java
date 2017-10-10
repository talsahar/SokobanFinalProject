/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package action;

import solution.Solution;

public abstract class AbstractAction extends Predicate {

	protected Clause precondition;
	protected Clause effects;
	protected boolean isFailed;
	protected Solution actionPlan;

	public AbstractAction(String type, String id, String value) {
		super(type, id, value);
	}

	public void setFailed() {
		isFailed = true;
	}

	public boolean isFailed() {
		return isFailed;
	}

	public Solution getActionPlan() {
		if (actionPlan == null) {
			System.out.println("No solution AbstractAction::getPlanSteps");
		}
		return actionPlan;
	}

	public Clause getEffects() {
		return effects;
	}

	public Clause getPreconditions() {
		return precondition;
	}

}
