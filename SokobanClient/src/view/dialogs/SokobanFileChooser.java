package view.dialogs;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import others.CONF;

public class SokobanFileChooser {
	public FileChooser genericPart(String title) {
		FileChooser fc = new FileChooser();
		fc.setTitle(title);
		fc.setInitialDirectory(new File(CONF.LEVELSPATH));
		fc.getExtensionFilters().addAll(new ExtensionFilter("Text files", "*.txt"),
				new ExtensionFilter("Object files", "*.obj"), new ExtensionFilter("XML files", "*.xml"));
		return fc;

	}

	public File getLoadFile() {
		return genericPart("Load Level").showOpenDialog(null);

	}

	public File getSaveFile() {
		return genericPart("Save Level").showSaveDialog(null);

	}
}
