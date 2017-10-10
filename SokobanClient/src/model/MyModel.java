package model;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;

import javafx.beans.property.SimpleStringProperty;
import model.data.BackgroundMusic;
import model.data.Level;
import model.data.MovePlayer;
import model.policy.Policy;
import model.records.GetRecordsFromXML;
import model.records.Record;
import model.records.RecordList;
import model.streamtypes.StreamManager;
import others.CommandNames;

public class MyModel extends Observable implements Model {

	private Level lvl;
	private RecordList currentRecordList;

	private Policy p;
	private BackgroundMusic music;
	private SimpleStringProperty steps;
	private MovePlayer movePlayer;
	private StreamManager streamManager;

	private TimeCounter timeCounter;

	private MyModel() {
		timeCounter = new TimeCounter();
		steps = new SimpleStringProperty();
		lvl = new Level();
		p = new Policy();
		music = new BackgroundMusic("./resources/data/song.mp3");
		streamManager = StreamManager.getInstance();
	}

	@Override
	public void showSolutionAnimation(String solution) {
		Queue<Character> queue = new LinkedList<>();// FIFO
		for (int i = 0; i < solution.length(); i++)
			queue.add(solution.charAt(i));

		new Timer().scheduleAtFixedRate(new AnimationSolver(queue, d -> {
			movePlayer.move(d);
			notifier(CommandNames.DISPLAY);
			steps.set("Steps: " + lvl.getSteps());
		}, () -> restart()), 0, 500);
	}

	@Override
	public void load(String s) {

		lvl = new Level();
		try {
			streamManager.LevelLoader(s, lvl);
			notifier(CommandNames.DISPLAY);
			notifier(CommandNames.MESSAGE + " " + "Charging completed");
			steps.set("Steps: " + lvl.getSteps());
			timeCounter.startCounting();
			movePlayer = new MovePlayer(lvl, p);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void save(String s) {

		if (!lvl.isExist()) {
			notifier(CommandNames.MESSAGE + " " + "Level not exists");
			return;
		}
		streamManager.LevelSaver(s, lvl);
		notifier(CommandNames.MESSAGE + " " + "Saving Completed");
	}

	@Override
	public void restart() {
		if (!lvl.isExist()) {
			notifier(CommandNames.MESSAGE + " " + "Couldnt restart, level not exists");
			return;
		}
		load(lvl.getPath());
		notifier(CommandNames.MESSAGE + " " + "Restart Completed");
	}

	@Override
	public void move(String direction) {

		if (!lvl.isExist())
			return;

		if (!movePlayer.move(direction)) {
			notifier(CommandNames.MESSAGE + " illegal move");
			return;
		}
		notifier(CommandNames.DISPLAY);
		steps.set("Steps: " + lvl.getSteps());

		if (lvl.isCompleted())
			notifier(CommandNames.COMPLETED);

	}

	@Override
	public RecordList analyzeServersScoreRespond(String str) {
		currentRecordList = new GetRecordsFromXML().getRecords(str);
		return currentRecordList;

	}

	@Override
	public Record buildRecord(String playerName) {

		String date = new SimpleDateFormat("dd/MM/yy HH:mm:ss").format(new Date());
		date += new Random().nextInt(1000);
		return new Record(date, playerName, getLevelName(), timeCounter.getTimeCounter(), lvl.getSteps());

	}

	@Override
	public String getLevelName() {
		if (lvl.isExist()) {
			String name = Paths.get(lvl.getPath()).getFileName().toString();
			return name.substring(0, name.length() - 4);
		}
		return null;
	}

	@Override
	public void notifier(String msg) {

		setChanged();
		notifyObservers(msg);

	}

	private static class Holder {
		public static final MyModel instance = new MyModel();
	}

	public static MyModel getInstance() {
		return Holder.instance;
	}

	@Override
	public RecordList getCurrentRecordList() {
		return currentRecordList;
	}

	@Override
	public BackgroundMusic getMusic() {
		return music;
	}

	@Override
	public Level getLevel() {
		return lvl;

	}

	@Override
	public SimpleStringProperty getSteps() {
		return steps;
	}

	@Override
	public TimeCounter getTimeCounter() {
		return timeCounter;
	}

}