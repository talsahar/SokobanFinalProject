/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package plannable;

import action.AbstractAction;
import action.Clause;
import action.Predicate;

public interface Plannable {

	Clause getGoal();

	Clause getKnowledgeBase();

	AbstractAction getSatisfyingAction(Predicate top);

}
