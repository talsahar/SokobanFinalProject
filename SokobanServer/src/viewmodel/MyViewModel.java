/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package viewmodel;

import java.util.Observable;
import java.util.Observer;

import app.conf.Conf;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MyModel;

public class MyViewModel extends Observable implements ViewModel, Observer {

	private StringProperty idField;
	private StringProperty passField;
	private StringProperty textField;
	private StringProperty labelField;
	private ObservableList<String> userList;
	private ObservableList<String> processList;
	private MyModel model;

	Runnable enableButtons, DisableButtons;

	private MyViewModel() {
		model = (MyModel) MyModel.getInstance();
		model.addObserver(this);
		idField = new SimpleStringProperty();
		passField = new SimpleStringProperty();
		textField = new SimpleStringProperty();
		labelField = new SimpleStringProperty();
		userList = FXCollections.observableArrayList();
		processList = FXCollections.observableArrayList();
	}

	@Override
	public void disconnectAction() {
		model.stopServer();
	}

	public void endProcess(String id) {
		if (id != null)
			model.endProcess(id);

	}

	public void kickClient(String id) {
		if (id != null)
			model.kickClient(id);

	}

	@Override
	public void login() {

		model.runServer(idField.get(), passField.get());

	}

	public void sendMessage(String id) {

		model.sendMessage(id, textField.get());

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// update label
		Platform.runLater(() -> labelField.set((String) arg1));

		String param = (String) arg1;

		// update client list
		if (param.startsWith(Conf.newClientNotify)) {
			Platform.runLater(() -> userList.add(param.substring(Conf.newClientNotify.length())));
		} else if (param.startsWith(Conf.removeClientNotify)) {
			Platform.runLater(
					() -> userList.removeIf(p -> p.startsWith(param.substring(Conf.removeClientNotify.length()))));
		}
		// update process List
		else if (param.startsWith(Conf.newProcessNotify)) {
			Platform.runLater(() -> processList.add(param.substring(Conf.newProcessNotify.length())));
		}

		else if (param.startsWith(Conf.overProcessNotify)) {
			Platform.runLater(
					() -> processList.removeIf(p -> p.startsWith(param.substring(Conf.overProcessNotify.length()))));
		}

		else if (param.startsWith(Conf.serverOnline))
			enableButtons.run();
		else if (param.startsWith(Conf.serverOffline))
			DisableButtons.run();
	}

	public void updateMaxConnections(int parseInt) {
		model.setMaxConnections(parseInt);

	}

	//////////////////////////////////////////////////// getters & setters &
	//////////////////////////////////////////////////// singleton

	private static class Holder {
		public static final ViewModel instance = new MyViewModel();
	}

	public static ViewModel getInstance() {
		return Holder.instance;
	}

	public void setEnableButtonsFunction(Runnable onServerOnlineFunction) {
		enableButtons = onServerOnlineFunction;
	}

	public void setDisableButtonsFunction(Runnable onServerDownFunction) {
		DisableButtons = onServerDownFunction;
	}

	public ObservableList<String> getUserList() {
		return userList;
	}

	public ObservableList<String> getProcessList() {
		return processList;
	}

	public StringProperty getIdField() {
		return idField;
	}

	public StringProperty getLabelField() {

		return labelField;
	}

	public StringProperty getPassField() {
		return passField;
	}

	public StringProperty getTextField() {
		return textField;
	}

}
