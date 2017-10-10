package others;

import controller.MySokobanController;
import model.MyModel;
import server.SimpleClient;
import view.MainWindowController;

public class mvcBuilder {

	public void buildSingleton(MainWindowController mainWindowController) {
		MySokobanController controller = MySokobanController.getInstance();
		MyModel model = MyModel.getInstance();
		model.addObserver(controller);
		mainWindowController.addObserver(controller);

		SimpleClient client = SimpleClient.getInstance();
		client.addObserver(controller);
		controller.intCommandMap(mainWindowController, model, controller, client);
	}
}
