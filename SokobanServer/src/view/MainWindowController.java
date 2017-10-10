package view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import viewmodel.MyViewModel;

public class MainWindowController implements View {

	private MyViewModel viewModel;

	@FXML
	TextField idField;
	@FXML
	TextField passwordField;
	@FXML
	Button loginButton;
	@FXML
	Button disconnectButton;
	@FXML
	BorderPane contentBox;
	@FXML
	TextArea textField;
	@FXML
	Button sendButton;
	@FXML
	ListView<String> userList;
	@FXML
	ListView<String> processList;
	@FXML
	Button kickButton;
	@FXML
	Button endProcessButton;
	@FXML
	Label label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindViewModel();
		loginButton.setOnAction(e -> viewModel.login());
		disconnectButton.setOnAction(e -> viewModel.disconnectAction());
		kickButton.setOnAction(e -> viewModel.kickClient(getUserListSelectedID()));
		endProcessButton.setOnAction(e -> viewModel.endProcess(getProcessListSelectedID()));
		sendButton.setOnAction(e -> viewModel.sendMessage(getUserListSelectedID()));
	}

	private String getUserListSelectedID() {
		return (userList.getSelectionModel().getSelectedItem() != null)
				? userList.getSelectionModel().getSelectedItem().split(":")[0]
				: null;
	}

	private String getProcessListSelectedID() {
		return (processList.getSelectionModel().getSelectedItem() != null)
				? processList.getSelectionModel().getSelectedItem().split(" ")[0]
				: null;
	}

	private void bindViewModel() {
		viewModel = (MyViewModel) MyViewModel.getInstance();
		viewModel.setEnableButtonsFunction(() -> buttonStateLock(false));
		viewModel.setDisableButtonsFunction(() -> buttonStateLock(true));
		viewModel.getIdField().bind(idField.textProperty());
		viewModel.getPassField().bind(passwordField.textProperty());
		viewModel.getTextField().bind(textField.textProperty());
		label.textProperty().bind(viewModel.getLabelField());
		userList.setItems(viewModel.getUserList());
		processList.setItems(viewModel.getProcessList());
	}

	@Override
	public void setMaxConnections() {
		if (!loginButton.isDisable()) {
			TextInputDialog dialog = new TextInputDialog("10");
			dialog.setTitle("Max Connections");
			dialog.setContentText("Enter max connections:");
			Optional<String> result = dialog.showAndWait();
			viewModel.updateMaxConnections(Integer.parseInt(result.get()));

		}

	}

	@Override
	public void exitAction() {
		if (loginButton.isDisabled())
			viewModel.disconnectAction();
		Platform.exit();

	}

	// online?false:true
	private void buttonStateLock(boolean b) {
		idField.setDisable(!b);
		passwordField.setDisable(!b);
		loginButton.setDisable(!b);
		disconnectButton.setDisable(b);
		contentBox.setDisable(b);

	}

	@Override
	public void aboutAction() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("About");
		alert.setHeaderText("Tal Sahar");
		alert.showAndWait();
	}

}
