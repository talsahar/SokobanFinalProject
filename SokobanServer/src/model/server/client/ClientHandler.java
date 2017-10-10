/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package model.server.client;

public interface ClientHandler {
	void send(String message);

	void start();

	void stop();
}
