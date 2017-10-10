package model.keys;

import java.io.Serializable;

import javafx.scene.input.KeyCode;

public class SokobanKeys implements Serializable {

	private KeyCode up;
	private KeyCode down;
	private KeyCode left;
	private KeyCode right;
	private KeyCode restart;

	public SokobanKeys() {

	}

	public SokobanKeys(KeyCode... keys) {
		if (keys.length != 4)
			throw new RuntimeException("the constractors parameters length isnt 4");
		up = keys[0];
		down = keys[1];
		left = keys[2];
		right = keys[3];
		restart = KeyCode.R;
	}

	public void setUp(KeyCode up) {
		this.up = up;
	}

	public void setDown(KeyCode down) {
		this.down = down;
	}

	public void setLeft(KeyCode left) {
		this.left = left;
	}

	public void setRight(KeyCode right) {
		this.right = right;
	}

	public void setRestart(KeyCode restart) {
		this.restart = restart;
	}

	public KeyCode getDown() {
		return down;
	}

	public KeyCode getLeft() {
		return left;
	}

	public KeyCode getRestart() {
		return restart;
	}

	public KeyCode getRight() {
		return right;
	}

	public KeyCode getUp() {
		return up;
	}

	public void setKey(String keyName, KeyCode k) {
		switch (keyName) {
		case "Up":
			up = k;
			break;
		case "Down":
			down = k;
			break;
		case "Left":
			left = k;
			break;
		case "Right":
			right = k;
			break;

		}

	}

	@Override
	public String toString() {

		String s = "";
		return (up + "\n" + down + "\n" + left + "\n" + right + "\n" + restart);

	}

}
