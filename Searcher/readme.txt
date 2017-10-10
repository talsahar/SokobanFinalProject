 * 
 * Searcher 
 * The searcher interface provides a search (Searchable s) statement. 
 * Best-first-search and Dijkstra classes which are responsible for
 * searching the quickest path between source and destination will implement
 * this interface and will generically will take as argument any type of
 * Searchable which represents the policy and the following states: source and
 * destination. in our case Sokoban Searchable which the source and des are
 * two-dimensional arrays, with the Sokoban's policy. 
 * 