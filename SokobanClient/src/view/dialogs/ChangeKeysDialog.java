package view.dialogs;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import model.keys.SokobanKeys;
import others.CONF;

public class ChangeKeysDialog {

	public void chKeys(SokobanKeys myKeys) {

		String[] keyNames = { "Up", "Down", "Left", "Right" };
		for (String keyName : keyNames) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Enter " + keyName);
			alert.setHeaderText(null);
			alert.setContentText("Enter " + keyName + " and click on the button.");

			ButtonType buttonType = new ButtonType("<enter key>");
			alert.getButtonTypes().setAll(buttonType);
			Button buttom = (Button) alert.getDialogPane().lookupButton(buttonType);
			buttom.setOnKeyPressed(event -> {
				if (event.getCode() != KeyCode.ENTER && event.getCode() != KeyCode.SPACE) {
					buttom.setText(event.getCode().getName());
					myKeys.setKey(keyName, event.getCode());
				}
			});
			alert.showAndWait();
		}

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Keys Update");
		alert.setHeaderText(null);
		alert.setContentText(
				"Your new keys: \n UP: " + myKeys.getUp().getName() + "\nDown: " + myKeys.getDown().getName()
						+ "\nLeft: " + myKeys.getLeft().getName() + "\nRight: " + myKeys.getRight().getName());

		ButtonType buttomType1 = new ButtonType("Save as default");
		ButtonType buttomType2 = new ButtonType("One-time");
		alert.getButtonTypes().setAll(buttomType1, buttomType2);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttomType1) {
			try {
				XMLEncoder d = new XMLEncoder(new FileOutputStream(CONF.KEYSPATH));
				d.writeObject(myKeys);
				d.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}

	}

}
