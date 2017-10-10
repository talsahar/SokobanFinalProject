/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package model;

public interface Model {
	void runServer(String id, String pass);
	void stopServer();
	void setPort(int port);
	void kickClient(String id);
	void sendMessage(String id, String message);
	void setMaxConnections(int parseInt);
	void endProcess(String id);

}
