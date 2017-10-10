package model;

import java.util.Timer;
import java.util.TimerTask;

import javafx.beans.property.SimpleStringProperty;

public class TimeCounter {
	Timer timer;
	int timeCounter;

	public int getTimeCounter() {
		return timeCounter;
	}

	SimpleStringProperty timeLine;

	public TimeCounter() {
		timeLine = new SimpleStringProperty();
		timer = new Timer();
		timeCounter = 0;
	}

	public void stopCounting() {
		timer.cancel();
	}

	public void startCounting() {
		stopCounting();
		timer = new Timer();
		timeCounter = 0;
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				timeLine.set("Time: " + (++timeCounter));
			}
		}, 0, 1000);
	}

	public SimpleStringProperty getProperty() {
		return timeLine;
	}
}
