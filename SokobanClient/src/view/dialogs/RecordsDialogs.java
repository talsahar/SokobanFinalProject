package view.dialogs;

import java.util.Optional;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import others.CommandNames;

public class RecordsDialogs {

	public void filterRecords(Consumer<String> func) {
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Filtering");
		dialog.setHeaderText("Enter Name or Level");

		ButtonType findButton = new ButtonType("Find", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(findButton, ButtonType.CANCEL);

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField playerName = new TextField();
		playerName.setPromptText("<PlayerName>");
		TextField levelName = new TextField();
		levelName.setPromptText("<LevelName>");

		grid.add(new Label("Player:"), 0, 0);
		grid.add(playerName, 1, 0);
		grid.add(new Label("Level:"), 0, 1);
		grid.add(levelName, 1, 1);

		dialog.getDialogPane().setContent(grid);
		Platform.runLater(() -> playerName.requestFocus());

		dialog.setResultConverter(dialogButton -> {
			if (dialogButton == findButton) {
				return new Pair<>(playerName.getText(), levelName.getText());
			}
			return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();

		result.ifPresent(res -> {
			String name = res.getKey();
			String level = res.getValue();
			String noty = CommandNames.ServerLoadRecordsRequest;
			if (!name.equals(""))
				noty += " " + "player=" + name;
			if (!level.equals(""))
				noty += " " + "level=" + level;

			func.accept(noty);
		});
	}

	public void askToRegisterRecord(Consumer<String> func) {
		TextInputDialog dialog = new TextInputDialog("");
		dialog.setTitle("Registration");
		dialog.setHeaderText("Do you Want to Appear on World Records List?");
		dialog.setContentText("Your name:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			String s = result.get();
			if (s.equals(""))
				askToRegisterRecord(func);
			else
				func.accept(CommandNames.ServerSaveScore + " " + result.get());
		}
	}

	public void sortBy(Consumer<String> accept, Consumer<String> deny) {
		Alert alert = new Alert(javafx.scene.control.Alert.AlertType.CONFIRMATION);
		alert.setTitle("Sort");
		alert.setHeaderText("Sort Scores by:");
		ButtonType buttonTypeOne = new ButtonType("Steps");
		ButtonType buttonTypeTwo = new ButtonType("Time");
		ButtonType buttonTypeThree = new ButtonType("lvlName");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeThree, buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			accept.accept(CommandNames.SortRecords + " steps");
		} else if (result.get() == buttonTypeTwo) {
			accept.accept(CommandNames.SortRecords + " time");
		} else if (result.get() == buttonTypeThree) {
			accept.accept(CommandNames.SortRecords + " levelname");
		} else {
			deny.accept("sorting denided");
		}
	}

}
