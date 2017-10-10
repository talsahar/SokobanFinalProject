package server;

public interface MyClient {

	void disconnect();

	boolean isConnected();

	void send(String s);

	void start();

	boolean isSet();

}
