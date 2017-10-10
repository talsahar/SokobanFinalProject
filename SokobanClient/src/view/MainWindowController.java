package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.data.XObj;
import model.keys.SokobanKeys;
import model.keys.XMLkeysStream;
import model.records.RecordList;
import others.CONF;
import others.CommandNames;
import others.MyMapper;
import others.mvcBuilder;
import view.dialogs.ChangeKeysDialog;
import view.dialogs.RecordsDialogs;
import view.dialogs.RecvSolutionDialog;
import view.dialogs.SokobanFileChooser;
import view.dialogs.ThemeDialog;
import view.graphicsBuilder.CommonGraphicsBuilder;
import view.graphicsBuilder.LevelDisplayerManager;

public class MainWindowController extends Observable implements View, Initializable {

	private SokobanKeys myKeys;
	private MyMapper<KeyCode, Runnable> keyMap;
	@FXML
	BorderPane borderPane;

	// canvas
	@FXML
	private LevelDisplayer levelDisplayer;
	@FXML
	private FrontDisplayer frontDisplayer;

	// vbox texts
	@FXML
	private Text message;

	@FXML
	private Text steps;

	@FXML
	private Text time;

	// menu items
	@FXML
	MenuItem connectServerMenuItem;

	@FXML
	MenuItem showChampionsMenuItem;

	/// Menu Buttons
	@FXML
	VBox mainMenuButtons;
	@FXML
	ImageView playButton;
	@FXML
	ImageView loadButton;
	@FXML
	ImageView saveButton;
	@FXML
	ImageView exitButton;
	// secondary buttons
	@FXML
	HBox secondaryButtons;
	@FXML
	private Button backButton;
	@FXML
	private Button showMoreButton;
	@FXML
	private Button sortBy;
	@FXML
	private Button Filter;

	// flags
	boolean freeze = true;

	boolean lvlExist = false;
	boolean lvlCompleted = false;

	public MainWindowController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		new mvcBuilder().buildSingleton(this);

		new LevelDisplayerManager(this).setAll(new CommonGraphicsBuilder("Ninja Pro/"));
		allButtonsOff();
		mainMenuShow();
		levelDisplayer.setFocusTraversable(true);
		initKeys();
		// Platform.runLater(() -> playMusic());
		setMessage("Developed By Tal Sahar");
		
		notifier(CommandNames.BIND);

	}

	// SERVER
	public void connectServer() {
		notifier(CommandNames.CONNECTSERVER);
	}

	public void disconnectServer() {
		notifier(CommandNames.DISCONNECTSERVER);
	}


	private void autorunServer() {
		new Thread(() -> {
			try {
				Thread.sleep(1000);
				connectServer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
	}

	private void initKeys() {
		myKeys = new XMLkeysStream().loadKeys(CONF.KEYSPATH);
		levelDisplayer.setOnKeyPressed((KeyEvent event) -> {
			if (freeze == true)
				return;

			if (event.getCode() == myKeys.getUp())
				notifier(CommandNames.MOVE + " " + CommandNames.UP);
			else if (event.getCode() == myKeys.getDown())
				notifier(CommandNames.MOVE + " " + CommandNames.DOWN);
			else if (event.getCode() == myKeys.getLeft())
				notifier(CommandNames.MOVE + " " + CommandNames.LEFT);
			else if (event.getCode() == myKeys.getRight())
				notifier(CommandNames.MOVE + " " + CommandNames.RIGHT);
			else if (event.getCode() == myKeys.getRestart())
				restartLevel();
			setMessage("");
		});

	}

	//////////// notify string
	public void notifier(String arg) {
		setChanged();
		notifyObservers(arg);
	}

	////////////// KEYS
	public void changeKeys() {

		new ChangeKeysDialog().chKeys(myKeys);

	}

	// LOAD SAVE RESTART
	public void playButton() {
	}

	public void loadButton() {
		File chosen;
		if ((chosen = new SokobanFileChooser().getLoadFile()) != null) {
			notifier(CommandNames.LOAD + " " + chosen.getAbsolutePath());
			allButtonsOff();
			frontDisplayer.setVisible(false);
			lvlExist = true;
			lvlCompleted = false;
			showChampionsMenuItem.setDisable(false);
			freeze = false;
		}
		levelDisplayer.requestFocus();

	}

	public void saveButton() {
		File chosen;
		if (lvlExist && !lvlCompleted && (chosen = new SokobanFileChooser().getSaveFile()) != null) {
			notifier(CommandNames.SAVE + " " + chosen.getAbsolutePath());
			levelDisplayer.requestFocus();

		}
	}

	public void restartLevel() {

		if (lvlExist) {
			frontDisplayer.setVisible(false);
			allButtonsOff();
			freeze = false;
			notifier(CommandNames.RESTART);
		}
	}

	// Music
	public void playMusic() {

		notifier(CommandNames.SwitchMusic + " on");
	}

	public void stopMusic() {
		notifier(CommandNames.SwitchMusic + " off");

	}
	// DISPLAYERS

	@Override
	public void levelCompleted(String steps, String time) {
		freeze = true;
		lvlCompleted = true;
		setMessage("Congratulations!");

		frontDisplayer.winPop(time, steps);
		frontDisplayer.setVisible(true);
		notifier(CommandNames.SetTimer + " off");
		Platform.runLater(() -> askToRegisterRecord());
	}

	@Override
	public void mainMenuShow() {
		allButtonsOff();
		freeze = true;
		frontDisplayer.setVisible(true);
		frontDisplayer.clearAndDraw(null);
		mainMenuButtons.setVisible(true);
		if (lvlExist == true && lvlCompleted == false)
			backButton.setVisible(true);
	}

	@Override
	public void displayLevel(ArrayList<ArrayList<XObj>> myLevel, int px, int py) {

		levelDisplayer.redraw(myLevel, px, py);

	}

	public void about() throws FileNotFoundException {
		allButtonsOff();
		freeze = true;
		frontDisplayer.setVisible(true);
		frontDisplayer.aboutPop();

		backButton.setVisible(true);

	}

	public void changeTheme() {
		Optional<String> result = new ThemeDialog().changeTheme();
		if (result.isPresent())
			new LevelDisplayerManager(this).setAll(new CommonGraphicsBuilder(result.get() + "/"));

	}

	@Override
	public void setMessage(String s) {

		message.setText(s);

	}

	// AUTO SOLVE
	public void autoSolve() {

		notifier(CommandNames.ServerSolutionRequest);

	}

	@Override
	public int hintOrSolution() {
		return new RecvSolutionDialog().hintOrSolution();
	}

	////////////////////////////
	//////// RECORDS///////////
	///////////////////////////////////////

	@Override
	public void showChamionsList(RecordList list) {

		backButton.setVisible(true);
		setRecordButtons(true);
		frontDisplayer.showRecords(list.getCurrentPageList());

	}

	public void showChampionsButton() {
		frontDisplayer.setVisible(true);
		frontDisplayer.loadingDatabase();
		allButtonsOff();
		freeze = true;
		notifier(CommandNames.ServerLoadRecordsRequest + " " + "getLevel");

	}

	public void filterRecords() {
		new RecordsDialogs().filterRecords((str) -> notifier(str));
	}

	private void askToRegisterRecord() { // when level completed
		new RecordsDialogs().askToRegisterRecord((str) -> notifier(str));

	}

	public void sortBy() {
		new RecordsDialogs().sortBy(str -> notifier(str), str -> setMessage(str));
	}

	public void showMoreButton() {
		notifier(CommandNames.NextPageRecords);
	}

	// BUTTONS

	public void allButtonsOff() {

		mainMenuButtons.setVisible(false);
		backButton.setVisible(false);
		showMoreButton.setVisible(false);
		sortBy.setVisible(false);
		Filter.setVisible(false);

	}

	@Override
	public void backButton() {
		allButtonsOff();
		if (!lvlCompleted && lvlExist) {
			freeze = false;
			frontDisplayer.setVisible(false);
			notifier(CommandNames.DISPLAY);
		} else if (lvlCompleted || !lvlExist) {
			mainMenuShow();
			backButton.setVisible(false);
		}

	}

	public void setRecordButtons(boolean b) {

		showMoreButton.setVisible(b);
		sortBy.setVisible(b);
		Filter.setVisible(b);

	}

	public void exitButton() {

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Exit");
		alert.setContentText("Are You Sure?");

		if (alert.showAndWait().get().getText().equals("OK")) {
			notifier(CommandNames.EXIT);
		}

	}

	// GETTERS
	@Override
	public Text getStepsField() {
		return steps;
	}

	@Override
	public Text getTimeField() {
		return time;
	}

	public Canvas getDisplayer() {
		return levelDisplayer;
	}

	public ImageView getExitButton() {
		return exitButton;
	}

	public FrontDisplayer getFrontDisplayer() {
		return frontDisplayer;
	}

	public ImageView getLoadButton() {
		return loadButton;

	}

	public ImageView getPlayButton() {
		return playButton;

	}

	public ImageView getSaveButton() {
		return saveButton;
	}

	public BorderPane getBorderPane() {
		return borderPane;
	}

}