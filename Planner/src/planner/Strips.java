/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package planner;

import java.util.Stack;

import action.AbstractAction;
import action.Clause;
import action.Predicate;
import action.StopCondition;
import plannable.Plannable;
import solution.Solution;

public class Strips implements Planner {

	@Override
	public Solution plan(Plannable plannable, Heuristic h) {

		Solution plan = new Solution();
		Clause goal = plannable.getGoal();
		if (h != null) {
			goal = useHeuristic(plannable.getKnowledgeBase(), goal, h);
		}
		Stack<Predicate> stack = new Stack<>();
		stack.push(goal);
		while (!stack.isEmpty()) {
			Predicate top = stack.peek();
			System.out.println("pop>>" + top);

			// first//
			if (top instanceof StopCondition) {
				System.out.println(((StopCondition) top).getStatus());
				return null;
			} else if (top instanceof AbstractAction) {
				AbstractAction newAction = (AbstractAction) stack.pop();
				plannable.getKnowledgeBase().update(newAction.getEffects());
				System.out.println(newAction.getActionPlan());
				plan.appendSolution(newAction.getActionPlan());
			}

			else if (!plannable.getKnowledgeBase().isSatisfied(top)) {
				if (top instanceof Clause) {
					Clause clause = (Clause) stack.pop();
					System.out.println("push clause<<" + clause);
					clause.getPredicates().forEach(p -> stack.push(p));
				} else {
					AbstractAction action = plannable.getSatisfyingAction(stack.pop());
					if (action != null) {
						stack.push(action);
						System.out.println("push action<<" + action);
						stack.push(action.getPreconditions());
						System.out.println("push precond<<" + action.getPreconditions());
						// System.out.println(action.getPreconditions());
					} else
						stack.push(new StopCondition(
								"error: strips got null action while asking for satisfied action\nno solution"));

				}
			} else
				stack.pop();
		}
		return plan;

	}

	Clause useHeuristic(Clause kb, Clause goal, Heuristic heuristic) {
		return heuristic.decomposeGoal(kb, goal);

	}

}
