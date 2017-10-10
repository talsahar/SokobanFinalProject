package model;

import javafx.beans.property.SimpleStringProperty;
import model.data.BackgroundMusic;
import model.data.Level;
import model.records.Record;
import model.records.RecordList;

public interface Model {

	RecordList analyzeServersScoreRespond(String str);

	Record buildRecord(String playerName);

	public Level getLevel();

	public String getLevelName();

	public void load(String s);

	public void move(String direction);

	public void notifier(String msg);

	public void restart();

	public void save(String s);

	BackgroundMusic getMusic();

	SimpleStringProperty getSteps();

	TimeCounter getTimeCounter();

	RecordList getCurrentRecordList();

	void showSolutionAnimation(String solution);

}
