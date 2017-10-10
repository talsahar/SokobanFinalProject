/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package planner;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import accessories.PredCreator;
import accessories.PredicateToState;
import accessories.SokobanClauseBuilder;
import action.AbstractAction;
import action.Clause;
import action.Predicate;
import action.StopCondition;
import plannable.Plannable;
import sokobanActions.CoverHole;
import sokobanActions.MovePlayer;
import sokobanActions.MyAction;

public class SokobanPlannable implements Plannable {

	MyClause goalState;
	MyClause knowledgeBase;
	PredCreator creator;

	// handles files and states both
	public SokobanPlannable(String fileName) throws IOException {
		creator = new PredCreator();
		List<String> level = null;
		SokobanClauseBuilder builder = new SokobanClauseBuilder(new PredCreator());
		if (fileName.endsWith(".txt")) {

			level = builder.loadFile(fileName);
		} else {
			String[] tmp = fileName.split("\\r\\n|\\n|\\r");
			level = new LinkedList<>();
			for (String line : tmp)
				level.add(line);
		}

		knowledgeBase = builder.buildKnowledgeBase(level);
		goalState = builder.buildGoal(knowledgeBase);

	}

	@Override
	public Clause getGoal() {
		return goalState;
	}

	@Override
	public Clause getKnowledgeBase() {
		return knowledgeBase;
	}

	@Override
	public AbstractAction getSatisfyingAction(Predicate top) {
		MyPredicate popped = (MyPredicate) top;
		System.out.println("unsatisfied");
		System.out.println(new PredicateToState().clauseToState(knowledgeBase));
		if (popped.isPlayer() || popped.isPlayerHole()) { // predicate looks
			// ("playerAt","","x,y");
			MyAction action = new MovePlayer(knowledgeBase, popped.getValue(), creator);
			if (action.getActionPlan().isFailed()) {
				return new StopCondition("searching alg failed");
			}
			return action;
		}
		System.out.println(top.getType());

		if (top.getType().equals("boxHoleAt")) {
			// MoveBox action = new MoveBox(knowledgeBase,boxBeforePos, boxAfterPos, boxId);
			CoverHole action = new CoverHole(knowledgeBase, top.getValue(), creator);
			if (action.isFailed())
				return new StopCondition("failed satisfy cover-hole predicate, no path");
			return action;
		}

		if (top.getType().equals("clearPath")) {
			return new StopCondition("No clear path\nno solutio");
		}

		else {
			return new StopCondition("Unknown unsatisfied predicate: " + top);
		}

	}

}
