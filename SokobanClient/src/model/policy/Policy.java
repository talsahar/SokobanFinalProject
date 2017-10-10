package model.policy;

import model.data.Box;
import model.data.BoxHole;
import model.data.Wall;
import model.data.XObj;

public class Policy {

	public boolean acceptableMove(XObj o2, XObj o3) {
		if (o2 instanceof Wall)
			return false;

		if (o2 instanceof Box || o2 instanceof BoxHole) //// box& (boxOnHole||box||WALL)
			if (o3 instanceof Box || o3 instanceof Wall || o3 instanceof BoxHole)
				return false;
		return true;

	}

}
