/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package planner;

import action.Clause;

public interface Heuristic { // the last predicate on the list will pop first
	Clause decomposeGoal(Clause kb, Clause goal);
}
