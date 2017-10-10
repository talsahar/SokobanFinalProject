# Sokoban Final Project
Sokoban project were written in Java with the most innovative features of Java 8, while maintaining the OOP, GRASP and SOLID principles, implementation of the various design patterns, using MVC (model-view-controller) and MVVM (model-view-viewmodel) architectures while the user-interface design and input has written using the JavaFX libraries. A searching and planning OOP algorithms for the auto solver library which automatically solves Sokoban levels. A restful Jersey web server runs by apache tomcat 9 which responsible for auto-solver and integration with msql database.
### Download link at bottom of the page
## Project Layers
There are 5 Different layers on the project: "Desktop Application" layer, "Central Server" layer, "Auto Solver" library, the "Web Server" layer and "Database" layer. Each layer in the project has a different warranty it is about to decouple the huge code, it allows the developer easily to maintain the code, to strengthen the Single Responsibility principle and to make the project more scalability.
## Desktop Application Layer
#### The desktop application which every user may download and use.
##### Sokoban Desktop Application allows user to:
1.	Play and solve Sokoban levels.
2.	Load and save Sokoban levels.
3.	Create and download new levels.
4.	Request for current level solution from server which will solve the level for the user by his auto-solver library and the respond will displayed to the user as an solving animation.
5.	In case the user has solved a level by himself, he will be asked for approval to post his record on the "World Record List" in the server which stores all the user records on server's database. As well, user can request for the record list of the current loaded level, and the response of all the stored records of the various users in the world will displayed in the Record-Wall of the application and the user may filter/sort those records at will.
6.	The app supports some various skins so the user may change to, it also supports key changing and control the background music as he wishes.

The client side has written using a strict MVC approach, so that all the logic of the app stays on the model layer, while the view layer which written by JavaFX application code responsible for listening for input from the user and for the user interface presentation design.

The controller layer represents the connection between those layers and the "Command Design Pattern" which maintains an active object- a thread pool of a generic interface called Command which has an execute() method. The pool will run as a thread and can execute any type of inherited of Command which called by the other layers. That's how the other layers will exchange data between them. So I used the "Observer Design Pattern" while the controller is the observer of the view and the model, so it called only when the one of them notify a "command key" and the controller which maintains a map of <Key,Command> will retrieve the required command on O(1) and will execute it on the invoker, the thread pool in pure generic way.
Any level of the game can be loaded from any of those file extension types: .txt, .obj, xml. and can be converted to any of them. In addition, a level text file which represented by the following format example is easy to create and anyone may create, load and play.
```
########
##    ##
# oo   #
# # @@@#
#   #Ao#
########
```
The request/response of the client-server exchanges are transferred on TCP protocol, the client opens socket and connects to a central server which maintains thousands of Sokoban user's connections in parallel, and the server respond will be notified as a regular generic command on the client side.

## Central Server Layer
A central body, operated by Sokoban Administrator who connected with his own username and password, handles thousands of client's connections and service requesters. The administrator may send them chat messages, kick them from server and to deny their requests. He also allowed to limit the amount of the client's connections in overload situations. So that the client is not connects to web server directly however he connects to this central server which is connected to the web server who supplies the various services. The central server responsible for handle client's requests and provide them the web server services responses.

The central server written using a MVVM (model-view-viewmodel) architecture. Its approach good for small applications, there is a binding between the view and the viewmodel by their data members. The viewmodel holds the model which responsible for all the logic of the server. So, when the admin does any action it called from the view (Window Controller of the javaFX) as function to the viewmodel and the viewmodel will use the bounded data members to call the required action in model.

In our case our model maintains his own observable Server object. Its server object contains map of online user's handlers, and their IDs as key. The server object is an Observer for each one of those handlers (to handle their requests) and a listener for new users connections, so when a new request notified, the model will create a new inherited task from the generic web server Task object I created, which constitutes a jersey clients side which handles an http request/response for the restful webserver, the response will send back to the requester handler by his id, it also prevents the busy waiting of the handler he keeps running like the other handlers until the response will arrive. All those tasks invoked by an executor in TaskManager object in model which maintains all the current running tasks to the webserver. After the task ends it will be notified to the model or will execute his delegate which will send the response to the client. The request/respond with the web server exchanged by XML objects.
## Sokoban Solver Library
#### A separate library which responsible for solving Sokoban levels.
#### When a user fails to resolve a level, he may request a solution from the server.
#### The Sokoban Solver library represented as jar used by web server's Solver service.
In the Sokoban Solver we decoupled the algorithm from the problem it solves using the "dependency injection" technique. It's because we don’t want to duplicate the code we want the algorithms to be static and will use it on other domains. First, we were written the generic part, the independent algorithms:
#### Searcher:
The searcher interface provides a search (Searchable s) statement. Best-first-search and Dijkstra classes which are responsible for searching the quickest path between source and destination will implement this interface and will generically will take as argument any type of Searchable which represents the policy and the following states: source and destination. in our case Sokoban Searchable which the source and des are two-dimensional arrays, with the Sokoban's policy.
#### Planner:
The planner interface provides a solve (Plannable p, Heuristic h) statement. The planner algorithm called Strips maintains a stack, getting the domain's knowledgebase and goal-state, both represented by predicates, will insert the goal-state predicates to the stack will try to provide the unsatisfied predicates and update the knowledgebase by the policy of our current domain and will use the searching algorithm for searching purposes.
## Web Server Layer
#### A web server provides all the online services for the Central Server.
Sokoban Web Server based on a restful approach, written by using the jersey framework which provides a great web server API for developers. The data exchanged between the central server and the web server transferred as an XML-Object based on the Restful HTTP @Get and @Post approach for scalability and stable web-server. It also stores the game data collection on Microsoft SQL Server 2016 in "SokobanDB" database which includes various schemes and responsible for exchanging data with it effectively thanks the hibernate ORM, and accessed by the JDBC API.
#### The web-server provides 4 different services:
1.	Records Exchanging Service: Exchanging records with the dbo.Record table supports save, load by level-name or player-name.
2.	Solver Service: Using the "Sokoban Solver" jar above to solve and return a solution of Sokoban level.
3.	Solution Cashes Service: Stores solutions on database by its level hash, so that in the next requests on the same level it could just extract the previous solution from database without solving it again.
4.	Main Solver Service: Using the two Services above to return the requested level solution as fast as possible.
## Author
* **Tal Sahar** - *Software Developer* - [TalSahar](https://github.com/talsahar)
### [Presentation](https://drive.google.com/open?id=0B0VvuCx10Ud_UXRrNzRXeXd0Qms) - You can check the PowerPoint presentation
### [Download](https://drive.google.com/open?id=0B0VvuCx10Ud_WkdJQlhyZzNjWVk) - Windows 32/64 bit


