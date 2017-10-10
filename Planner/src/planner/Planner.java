/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package planner;

import plannable.Plannable;
import solution.Solution;

public interface Planner {

	Solution plan(Plannable plannable, Heuristic h);

}
