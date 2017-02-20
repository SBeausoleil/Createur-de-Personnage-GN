package com.sb.cdp.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

public class Connection {
    private final Socket CONNECTED_TO;
    private OutputStream toClient;
    private BufferedInputStream fromClient;

    public Connection(Socket client, OutputStream toClient, BufferedInputStream fromClient) {
	this.CONNECTED_TO = client;
	this.toClient = toClient;
	this.fromClient = fromClient;
    }

    public void writeTo(byte[] data) throws IOException {
	toClient.write(data.length);
	toClient.write(data);
    }

    public byte[] listenTo() throws IOException {
	byte[] length = new byte[4]; // Message length (int Big Endian)
	fromClient.read(length);
	byte[] message = new byte[ByteBuffer.wrap(length).getInt()];
	fromClient.read(message);
	return message;
    }

    /**
     * Returns the socket the connection is tied to.
     * 
     * @return the socket the connection is tied to
     */
    public Socket getConnectedTo() {
	return CONNECTED_TO;
    }
}
