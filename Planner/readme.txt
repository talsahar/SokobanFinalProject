 * 
 * Planner 
 * The planner interface provides a solve (Plannable p, Heuristic h) statement. 
 * The planner algorithm called Strips maintains a stack, getting the domain's knowledgebase
 * and goal-state, both represented by predicates, will insert the goal-state
 * predicates to the stack will try to provide the unsatisfied predicates and
 * update the knowledgebase by the policy of our current domain and will use the
 * searching algorithm for searching purposes.
 *
 * 