package accessories;

import planner.MyPredicate;

public class PredCreator {

	public MyPredicate createBox(String id, String value) {
		return new MyPredicate("boxAt", id, value);
	}

	public MyPredicate createWall(String value) {
		return new MyPredicate("wallAt", "", value);

	}

	public MyPredicate createPlayer(String value) {
		return new MyPredicate("playerAt", "", value);

	}

	public MyPredicate createClear(String value) {
		return new MyPredicate("clearAt", "", value);

	}

	public MyPredicate createBoxHole(String id, String value) {
		return new MyPredicate("boxHoleAt", id, value);

	}

	public MyPredicate createPlayerHole(String id, String value) {
		return new MyPredicate("playerHoleAt", id, value);

	}

	public MyPredicate createHole(String id, String value) {
		return new MyPredicate("holeAt", id, value);

	}

}
