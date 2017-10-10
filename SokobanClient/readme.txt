/**
 * Sokoban Desktop Application allows users to: 1. Play and solve Sokoban
 * levels. 2. Load and save Sokoban levels. 3. Create and download new levels.
 * 4. Request for current level solution from server which will solve the level
 * for the user by his own algorithm library and the respond will displayed to
 * the user as solve animation or as hint. 5. In case the user has solved a
 * level by himself, he will be asked for approval to post his record on the
 * "World Record List" in the server which stores all the user records on
 * server's database. As well, user can request for the record list of the
 * current loaded level, and the response of all the stored records of the
 * various users in the world will displayed in the Record-Wall of the
 * application and the user may filter/sort those records at will. 6. The app
 * supports some various skins so the user may change to, it also supports key
 * changing and control the background music as he wishes.
 * 
 * The client side has written using a strict MVC approach, so that all the
 * logic of the app stays on the model layer, while the view layer which written
 * by JavaFX application code responsible for listening for input from the user
 * and for the user interface presentation design. The controller layer
 * represents the connection between those layers and the "Command Design
 * Pattern" which maintains an active object- a thread pool of a generic
 * interface called Command which has an execute() method. The pool will run as
 * a thread and can execute any type of inherited of Command which called by the
 * other layers. That's how the other layers will exchange data between them. So
 * I used the "Observer Design Pattern" while the controller is the observer of
 * the view and the model, so it called only when the one of them notify a
 * "command key" and the controller which maintains a map of <Key,Command> will
 * retrieve the required command on O(1) and will execute it on the invoker, the
 * thread pool in pure generic way. Any level of the game can be loaded from any
 * of those file extension types: .txt, .obj, xml. and can be converted to any
 * of them. In addition, a level text file which represented by the following
 * format example is easy to create and anyone may create, load and play.
 * --example--
 * 
 * 
 * The request/response of the client-server exchanges are transferred on TCP
 * protocol, the client opens socket and connects to a central server which
 * maintains thousands of Sokoban user's connections in parallel, and the server
 * respond will be notified as a regular generic command on the client side.
 * 
 * To connect the server as you run the application you enter the following
 * param.
 * 
 * @param -server [ip] [port]
 *
 */
