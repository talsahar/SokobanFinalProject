package searcher;

import java.util.HashMap;

import run.Position;

public class NextStateBuilder {
	char[][] getNextState(char[][] board, Position p1, Position p2, Position p3) {
		HashMap<Position, Character> map = new HashMap<>();
		map.put(p1, board[p1.getY()][p1.getX()]);
		map.put(p2, board[p2.getY()][p2.getX()]);
		map.put(p3, board[p3.getY()][p3.getX()]);

		if (map.get(p1) == '@' || map.get(p1) == 'X')
			someBox(board, map, p1, p2, p3);
		if (map.get(p1) == 'A' || map.get(p1) == 'a')
			itsPlayer(board, map, p1, p2, p3);

		return buildState(board, map, p1, p2, p3);
	}

	// moving box
	void itsPlayer(char[][] board, HashMap<Position, Character> map, Position p1, Position p2, Position p3) {
		if (map.get(p1) == 'A') {
			if (map.get(p2) == ' ') {
				map.put(p2, 'A');
				map.put(p1, ' ');
			} else if (map.get(p2) == 'o') {
				map.put(p2, 'a');
				map.put(p1, ' ');
			} else if (map.get(p2) == '@') {
				if (map.get(p3) == ' ') {
					map.put(p3, '@');
					map.put(p2, 'A');
					map.put(p1, ' ');
				}
				if (map.get(p3) == 'o') {
					map.put(p3, 'X');
					map.put(p2, 'A');
					map.put(p1, ' ');
				}

			} else if (map.get(p2) == 'X') {
				if (map.get(p3) == ' ') {
					map.put(p3, '@');
					map.put(p2, 'a');
					map.put(p1, ' ');
				}
				if (map.get(p3) == 'o') {
					map.put(p3, 'X');
					map.put(p2, 'a');
					map.put(p1, ' ');
				}
			}

		}

		else if (map.get(p1) == 'a') {
			if (map.get(p2) == ' ') {
				map.put(p2, 'A');
				map.put(p1, 'o');
			} else if (map.get(p2) == 'o') {
				map.put(p2, 'a');
				map.put(p1, 'o');
			} else if (map.get(p2) == '@') {
				if (map.get(p3) == ' ') {
					map.put(p3, '@');
					map.put(p2, 'A');
					map.put(p1, 'o');
				}
				if (map.get(p3) == 'o') {
					map.put(p3, 'X');
					map.put(p2, 'A');
					map.put(p1, 'o');
				}
			} else if (map.get(p2) == 'X') {
				if (map.get(p3) == ' ') {
					map.put(p3, '@');
					map.put(p2, 'a');
					map.put(p1, 'o');
				}
				if (map.get(p3) == 'o') {
					map.put(p3, 'X');
					map.put(p2, 'A');
					map.put(p1, 'o');
				}
			}

		}
	}

	// moving box
	public void someBox(char[][] board, HashMap<Position, Character> map, Position p1, Position p2, Position p3) {
		if (map.get(p1) == '@') {
			if (map.get(p2) == 'o') {
				map.put(p2, 'X');
				map.put(p1, ' ');
			} else if (map.get(p2) == ' ') {
				map.put(p2, '@');
				map.put(p1, ' ');
			}
		} else if (map.get(p1) == 'X') {
			if (map.get(p2) == 'o') {
				map.put(p2, 'X');
				map.put(p1, 'o');
			} else if (map.get(p2) == ' ') {
				map.put(p2, '@');
				map.put(p1, 'o');
			}

		}

	}

	public char[][] buildState(char[][] board, HashMap<Position, Character> map, Position p1, Position p2,
			Position p3) {
		board[p1.getY()][p1.getX()] = map.get(p1);
		board[p2.getY()][p2.getX()] = map.get(p2);
		board[p3.getY()][p3.getX()] = map.get(p3);
		return board;
	}

}
