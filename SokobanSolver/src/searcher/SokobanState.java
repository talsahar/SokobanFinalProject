/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/
package searcher;

import run.Position;

public class SokobanState {

	private char[][] board;
	private Position pointer;

	public SokobanState(char[][] board, Position pointer) {
		this.board = board;
		this.pointer = pointer;
	}

	@Override
	public boolean equals(Object obj) {
		SokobanState s = (SokobanState) obj;

		if (s.board.length != board.length)
			return false;

		for (int i = 0; i < board.length; i++) {
			if (s.board[i].length != board[i].length)
				return false;
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != s.board[i][j])
					return false;
			}

		}
		return true;
	}

	public char[][] getBoard() {
		return board;
	}

	public Position getPointer() {
		return pointer;
	}

	@Override
	public int hashCode() {
		return board.hashCode();

	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public void setPointer(Position pointer) {
		this.pointer = pointer;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++)
				s += board[i][j];
			s += "\n";
		}
		return s;
	}
}
