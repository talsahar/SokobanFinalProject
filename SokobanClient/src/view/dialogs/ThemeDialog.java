package view.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import others.CONF;

public class ThemeDialog {

	public Optional<String> changeTheme() {

		List<String> choices = new ArrayList<String>();

		File folder = new File(CONF.GRAPHICPATH);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++)
			if (listOfFiles[i].isDirectory())
				choices.add(listOfFiles[i].getName());

		ChoiceDialog<String> dialog = new ChoiceDialog<>(null, choices);
		dialog.setTitle("Change Theme");
		dialog.setHeaderText("Change Theme");
		dialog.setContentText("Choose your theme:");

		return dialog.showAndWait();
		// if (result.isPresent())
		// new LevelDisplayerManager(this).setAll(new CommonGraphicsBuilder(result.get()
		// + "/"));

	}

}
