package model.data;

import model.policy.Policy;
import others.CONF;

public class MovePlayer {

	private Level myLevel;
	private int playerMode;
	private Place o1, o2, o3;
	private Policy policy;

	public MovePlayer(Level lvl, Policy policy) {
		myLevel = lvl;
		this.policy = policy;
	}

	public boolean acceptableMove() {
		return policy.acceptableMove(myLevel.getFrom(o2), myLevel.getFrom(o3));
	}

	public void initializeObjects(String w) {
		int x = myLevel.getPlayer().getPlace().getX();
		int y = myLevel.getPlayer().getPlace().getY();
		o1 = new Place(x, y);

		switch (w) {
		case CONF.DOWN:
			o2 = new Place(x, y + 1);
			o3 = new Place(x, y + 2);
			playerMode = 0;
			break;
		case CONF.UP:
			o2 = new Place(x, y - 1);
			o3 = new Place(x, y - 2);
			playerMode = 1;

			break;
		case CONF.LEFT:
			o2 = new Place(x - 1, y);
			o3 = new Place(x - 2, y);
			playerMode = 2;
			break;

		case CONF.RIGHT:
			o2 = new Place(x + 1, y);
			o3 = new Place(x + 2, y);
			playerMode = 3;
			break;

		}
		myLevel.setPlayerMode(playerMode);
	}

	public boolean move(String direction) {

		initializeObjects(direction);
		if (!acceptableMove())
			return false;

		myLevel.addStep();

		// HOLE
		if (myLevel.isHole(o2))
			playerIntoHole(direction);

		// SPACE
		else if (myLevel.isSpace(o2)) {

			// a_
			if (myLevel.getPlayer() instanceof PlayerHole) {
				addNewPlayer(o2);
				myLevel.setTo(new Hole(), o1);

			}
			// A_
			else
				swapObjects(o1, o2);

		}
		// BOX
		else if (myLevel.isBox(o2))
			moveBox();

		// BOXHOLE
		else if (myLevel.isBoxHole(o2))
			moveBoxOnHole(direction);

		return true;
	}

	private void moveBox() {

		if (myLevel.isHole(o3)) // into to hole
			boxIntoHole();
		else if (myLevel.isSpace(o3)) // space x3,y3
		{
			swapObjects(o2, o3);
			swapObjects(o1, o2);
			if (myLevel.getPlayer() instanceof PlayerHole) { // if its from hole
				myLevel.setTo(new Hole(), o1);
				addNewPlayer(o2);
			}
		}

	}

	private void moveBoxOnHole(String direction) {

		if (myLevel.isSpace(o3)) {
			myLevel.setTo(new Box(), o3);
			myLevel.setTo(new Hole(), o2);
			playerIntoHole(direction);
			myLevel.downWinCounter();
		} else if (myLevel.isHole(o3)) {
			swapObjects(o2, o3);
			playerIntoHole(direction);
		}
	}

	private void boxIntoHole() { /// push box->hole

		myLevel.setTo(new BoxHole(), o3);
		myLevel.setTo(o1, o2);

		if (myLevel.isPlayerHole(o2)) {
			myLevel.setTo(new Hole(), o1);
			addNewPlayer(o2);
		} else
			myLevel.setTo(new Space(), o1);

		myLevel.upWinCounter();

	}

	private void playerIntoHole(String direction) {
		// ao
		if (myLevel.getPlayer() instanceof PlayerHole)
			swapObjects(o1, o2);
		// Ao
		else {
			PlayerHole phole = new PlayerHole(o2.getX(), o2.getY());
			myLevel.setTo(phole, o2);
			myLevel.setPlayer(phole);
			myLevel.setTo(new Space(), o1);
		}

	}

	private void swapObjects(Place place1, Place place2) {
		XObj tmp = myLevel.getFrom(place2);
		myLevel.setTo(place1, place2);
		myLevel.setTo(tmp, place1);
	}

	private void addNewPlayer(Place pos) {
		Player player = new Player(pos.getX(), pos.getY());
		myLevel.setTo(player, pos);
		myLevel.setPlayer(player);
		myLevel.setPlayerMode(playerMode);
	}

}
