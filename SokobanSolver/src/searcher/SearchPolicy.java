
package searcher;

import run.Position;

public class SearchPolicy {

	public boolean isLegalMove(char[][] state, Position p1, Position p2, Position p3) {

		char o1 = state[p1.getY()][p1.getX()];
		char o2 = state[p2.getY()][p2.getX()];
		if (o2 == '#' || o2 == 'A' || o2 == 'a' || o2 == 'X')
			return false;

		char o3 = state[p3.getY()][p3.getX()];

		if (o1 == 'A' || o1 == 'a')
			if ((o3 == '#' || o3 == '@' || o3 == 'X') && (o2 == '@' || o2 == 'X'))
				return false;
		if (o1 == '@' || o1 == 'X')
			if (o2 == '@' || o2 == 'X')
				return false;
		return true;
	}

}
