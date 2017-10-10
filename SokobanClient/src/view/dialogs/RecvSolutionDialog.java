package view.dialogs;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class RecvSolutionDialog {
	public int hintOrSolution() {
		final FutureTask query = new FutureTask(() -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Got a Solution!");
			alert.setHeaderText("Choose how you want to see the solution");
			alert.setContentText("Choose your option.");
			ButtonType buttonTypeOne = new ButtonType("Hint");
			ButtonType buttonTypeTwo = new ButtonType("Animation");
			ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne)
				return 1;
			else if (result.get() == buttonTypeTwo)
				return 2;
			else
				return 0;
		});

		Platform.runLater(query);
		try {
			return (int) query.get();
		} catch (InterruptedException | ExecutionException e) {

			e.printStackTrace();
		}
		return 0;

	}
}
