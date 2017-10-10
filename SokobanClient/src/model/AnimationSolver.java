package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.TimerTask;
import java.util.function.Consumer;

import others.MyMapper;

//every second while queue not empty poll direction and move
class AnimationSolver extends TimerTask {

	Queue<Character> queue = new LinkedList<>();
	Consumer<String> move;
	Runnable onOver;

	public AnimationSolver(Queue<Character> stack, Consumer<String> move, Runnable onOver) {
		queue = stack;
		this.move = move;
		this.onOver = onOver;
	}

	@Override
	public void run() {
		String direction = null;
		if (queue.isEmpty()) {
			onOver.run();
			this.cancel();
		}

		else {
			direction = new MyMapper<Character, String>().add('u', "up").add('d', "down").add('l', "left")
					.add('r', "right").getByKey(queue.poll());
			move.accept(direction);

		}
	}

}