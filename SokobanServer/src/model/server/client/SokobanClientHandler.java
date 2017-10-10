/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package model.server.client;

/**
 * 
 * Handle with peer all received messages will notify to the observer(some are requests for web server)
 * 
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

import app.conf.Conf;

public class SokobanClientHandler extends Observable implements ClientHandler {
	private int id;
	Socket connectedSocket;
	DataInputStream in;
	DataOutputStream out;
	Thread thread;

	public SokobanClientHandler(Socket connectedSocket, int counterId) {
		try {
			this.id = counterId;
			this.connectedSocket = connectedSocket;
			in = new DataInputStream(connectedSocket.getInputStream());
			out = new DataOutputStream(connectedSocket.getOutputStream());
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getId() {
		return id;
	}

	public void start() {
		thread = new Thread(() -> {
			try {
				String inMessage;
				while ((inMessage = in.readUTF()) != null)
					notifier(inMessage);
			} catch (IOException e) {
				System.out.println(e);
			}
		});
		thread.start();
	}

	@Override
	public void send(String message) {
		try {
			out.writeUTF(message);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void stop() {
		try {
			connectedSocket.close();
			thread.join();
			notifier(Conf.removeClientNotify + id);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void notifier(String msg) {
		setChanged();
		notifyObservers(msg);
	}

}
