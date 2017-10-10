package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Observable;

import others.CommandNames;

public class SimpleClient extends Observable implements MyClient {

	private Socket clientSocket;
	DataInputStream in;
	DataOutputStream out;
	boolean isConnected;
	private int port;
	private String ip;
	private Thread thread;

	private SimpleClient() {
	}

	// important!
	public void setAddr(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	@Override
	public boolean isSet() {
		return !(ip == null && port == 0);

	}

	@Override
	public void disconnect() {
		if (isConnected) {
			try {
				isConnected = false;
				clientSocket.close();
				thread.join();
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean isConnected() {
		return isConnected;
	}

	@Override
	public void send(String s) {
		if (isConnected)
			try {
				out.writeUTF(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
		else
			notifyMessage("Please connect to server first");
	}

	@Override
	public void start() {

		if (!isConnected && isSet()) {
			try {
				clientSocket = new Socket(ip, port);
				in = new DataInputStream(clientSocket.getInputStream());
				out = new DataOutputStream(clientSocket.getOutputStream());
				isConnected = true;
				notifyMessage("connected to server " + "(" + ip + "," + port + ")");
				thread = new Thread(() -> {
					try {
						String msgin;
						while (isConnected) {
							if ((msgin = in.readUTF()) != null) {
								if (msgin.startsWith("#")) // response from server
									notifyCommand(msgin);
								else
									notifyMessage(msgin);
							}
						}

					} catch (IOException e) {
						System.out.println(e);
					}
				});
				thread.start();

			} catch (IOException e) {
				System.out.println(e);
				notifyMessage("connection refused");
			}
		}
	}

	private void notifyMessage(String msgin) {
		setChanged();
		notifyObservers(CommandNames.MESSAGE + " " + msgin);
	}

	private void notifyCommand(String msgin) {
		setChanged();
		notifyObservers(msgin);
	}

	private static class Holder {
		public static final SimpleClient instance = new SimpleClient();
	}

	public static SimpleClient getInstance() {
		return Holder.instance;
	}

}
