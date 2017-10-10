package searcher;

import run.Position;

public class GoalStateBuilder {
	public char[][] buildGoalState(char[][] newBoard, Position sPlace, Position gPlace) {
		char sC = newBoard[sPlace.getY()][sPlace.getX()];
		char gC = newBoard[gPlace.getY()][gPlace.getX()];
		if (sC == 'A' && gC == ' ') {
			sC = ' ';
			gC = 'A';
		} else if (sC == 'A' && gC == 'o') {
			sC = ' ';
			gC = 'a';
		} else if (sC == 'a' && gC == ' ') {
			sC = 'o';
			gC = 'A';
		} else if (sC == 'a' && gC == 'o') {
			sC = 'o';
			gC = 'a';
		} else if (sC == '@' && gC == ' ') {
			sC = ' ';
			gC = '@';
		} else if (sC == '@' && gC == 'o') {
			sC = ' ';
			gC = 'X';
		} else if (sC == 'X' && gC == ' ') {
			sC = 'o';
			gC = '@';
		} else if (sC == 'X' && gC == 'o') {
			sC = 'o';
			gC = 'X';
		}
		newBoard[sPlace.getY()][sPlace.getX()] = sC;
		newBoard[gPlace.getY()][gPlace.getX()] = gC;
		return newBoard;

	}

}
