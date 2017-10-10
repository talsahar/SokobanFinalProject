package planner;

import action.Predicate;
import run.Position;

public class MyPredicate extends Predicate {

	public MyPredicate(String type, String id, String value) {
		super(type, id, value);
	}

	public Position getPosition() {
		String[] arr = value.split(",");
		return new Position(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));
	}

	public Position[] getDoublePosition() {
		String[] sArr = value.split(",");
		Position s = new Position(Integer.parseInt(sArr[0]), Integer.parseInt(sArr[1]));
		Position d = new Position(Integer.parseInt(sArr[2]), Integer.parseInt(sArr[3]));
		return new Position[] { s, d };
	}

	@Override

	public boolean contradicts(Predicate other) {
		return super.contradicts(other) ? true
				: (type.equals(other.getType()) && id.equals(other.getID()) && !value.equals(other.getValue())
						&& !type.equals("clearAt"));

	}

	public boolean isWall() {
		return type.equals("wallAt");
	}

	public boolean isClear() {
		return type.equals("clearAt");
	}

	public boolean isPlayer() {
		return type.equals("playerAt");
	}

	public boolean isBox() {
		return type.equals("boxAt");
	}

	public boolean isHole() {
		return type.equals("holeAt");
	}

	public boolean isPlayerHole() {
		return type.equals("playerHoleAt");
	}

	public boolean isBoxHole() {
		return type.equals("boxHoleAt");
	}

	public boolean isClearPath() {
		return type.equals("clearPath");
	}

}
