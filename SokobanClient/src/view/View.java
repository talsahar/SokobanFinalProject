package view;

import java.util.ArrayList;

import javafx.scene.text.Text;
import model.data.XObj;
import model.records.RecordList;

public interface View {

	void backButton();

	void displayLevel(ArrayList<ArrayList<XObj>> myLevel, int px, int py);

	int hintOrSolution();

	void levelCompleted(String steps, String time);

	void setMessage(String s);

	void showChamionsList(RecordList list);

	Text getStepsField();

	Text getTimeField();

	FrontDisplayer getFrontDisplayer();

	void mainMenuShow();
}
