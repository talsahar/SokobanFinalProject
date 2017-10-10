/** © 2017 TAL SAHAR 
ALL RIGHTS RESERVED
*/

package model.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import app.conf.Conf;
import model.server.client.ClientHandler;
import model.server.client.SokobanClientHandler;

public class SokobanServer extends Observable implements Server, Observer {

	ServerSocket socket;
	private HashMap<String, ClientHandler> handlerMap;
	private int maxConnections;
	Thread thread;

	public SokobanServer(int maxConnections) {

		handlerMap = new HashMap<>(maxConnections);
		this.maxConnections = maxConnections;
	}

	public void removeClient(String id) {
		String noty = "";
		ClientHandler rHandler = handlerMap.remove(id);
		if (rHandler == null)
			notifier(Conf.noSuchAnIDExsistNotify + " (server) " + id);
		else
			rHandler.stop();

	}

	public void sendMessage(String seq, String message) {
		if (handlerMap.containsKey(seq))
			handlerMap.get(seq).send(message);
		else
			notifier(Conf.noSuchAnIDExsistNotify + " (server send smg) " + seq);

	}

	@Override
	public void startServer(int port) {
		thread = new Thread(() -> {
			try {
				socket = new ServerSocket(port);
				notifier(Conf.serverOnline + " on Port " + port);
				int counterId = 0;
				Socket connectedSocket;
				while ((connectedSocket = socket.accept()) != null) {
					if (handlerMap.size() < maxConnections) {
						SokobanClientHandler newConnection = new SokobanClientHandler(connectedSocket, counterId);
						newConnection.addObserver(this);
						handlerMap.put("" + counterId, newConnection);
						notifier(Conf.newClientNotify + counterId + ": "
								+ connectedSocket.getInetAddress().toString().substring(1) + ":"
								+ connectedSocket.getPort());
						counterId++;
					} else
						new DataOutputStream(connectedSocket.getOutputStream()).writeUTF("server full");
				}
			} catch (IOException e) {
				System.out.println(e);
			}
		});
		thread.start();
	}

	@Override
	public void stopServer() {
		try {
			handlerMap.keySet().forEach(k -> removeClient(k));
			socket.close();
			thread.join();
			notifier(Conf.serverOffline);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		SokobanClientHandler handler = (SokobanClientHandler) arg0;
		String str = (String) arg1;
		if (str.equals(Conf.removingMessage))// from client
		{
			removeClient(handler.getId() + "");
			return;
		}

		if (str.startsWith(Conf.removeClientNotify))
			removeClient(handler.getId() + "");

		if (str.startsWith(Conf.recordQuery) || str.startsWith(Conf.solveCommand) || str.startsWith(Conf.saveRecord))
			arg1 += "_" + handler.getId();

		notifier(arg1 + "");
	}

	private void notifier(String arg1) {
		setChanged();
		notifyObservers(arg1);
	}

}
