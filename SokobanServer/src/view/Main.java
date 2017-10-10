package view;

import java.util.List;

import com.sun.research.ws.wadl.Param;

import app.conf.Conf;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.MyModel;

/**
 * A central body, operated by Sokoban Administrator who connected with his own
 * username and password, handles thousands of client's connections and service
 * requesters. The administrator may send them chat messages, kick them from
 * server and to deny their requests. He also allowed to limit the amount of the
 * client's connections in overload situations. So that the client is not
 * connects to web server directly however he connects to this central server
 * which is connected to the web server who supplies the various services. The
 * central server responsible for handle client's requests and provide them the
 * web server services responses.
 * 
 * The central server written using a MVVM
 * (model-view-viewmodel) architecture. Its approach good for small
 * applications, there is a binding between the view and the viewmodel by their
 * data members. The viewmodel holds the model which responsible for all the
 * logic of the server. So, when the admin does any action it called from the
 * view (Window Controller of the javaFX) as function to the viewmodel and the
 * viewmodel will use the bounded data members to call the required action in
 * model. 
 * 
 * In our case our model maintains his own observable Server object. Its
 * server object contains map of online user's handlers, and their IDs as key.
 * The server object is an Observer for each one of those handlers (to handle
 * their requests) and a listener for new users connections, so when a new
 * request notified, the model will create a new inherited task from the generic
 * web server Task object I created, which constitutes a jersey clients side
 * which handles an http request/response for the restful webserver, the
 * response will send back to the requester handler by his id, it also prevents
 * the busy waiting of the handler he keeps running like the other handlers
 * until the response will arrive. All those tasks invoked by an executor in
 * TaskManager object in model which maintains all the current running tasks to
 * the webserver. After the task ends it will be notified to the model or will
 * execute his delegate which will send the response to the client. The
 * request/respond with the web server exchanged by XML objects.

 
 The default port is 5555 to configure another port just enter the following param
 * @param -port [port] 
 * 
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
			Scene scene = new Scene(root, 506, 249);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setTitle("Sokoban Administrator API - Tal Sahar");
			primaryStage.setResizable(false);

			List<String> list = getParameters().getRaw();
			if (list.size() >= 2 && list.get(0).equals("-port"))
				MyModel.getInstance().setPort(Integer.parseInt(list.get(1)));
			else
				MyModel.getInstance().setPort(Conf.defaultPort);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
