package com.sb.cdp.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

import com.sb.cdp.User;

public class ServerConnection {
    private User user;

    private Socket client;
    private OutputStream toClient;
    private BufferedInputStream fromClient;

    public ServerConnection(User user, Socket client, OutputStream toClient, BufferedInputStream fromClient) {
	this.user = user;
	this.client = client;
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
     * Returns the user.
     * 
     * @return the user
     */
    public User getUser() {
	return user;
    }

    /**
     * Sets the value of user to that of the parameter.
     * 
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }

    /**
     * Returns the client.
     * 
     * @return the client
     */
    public Socket getClient() {
	return client;
    }

    /**
     * Sets the value of client to that of the parameter.
     * 
     * @param client
     *            the client to set
     */
    public void setClient(Socket client) {
	this.client = client;
    }

    /**
     * Returns the toClient.
     * 
     * @return the toClient
     */
    public OutputStream getToClient() {
	return toClient;
    }

    /**
     * Sets the value of toClient to that of the parameter.
     * 
     * @param toClient
     *            the toClient to set
     */
    public void setToClient(OutputStream toClient) {
	this.toClient = toClient;
    }

    /**
     * Returns the fromClient.
     * 
     * @return the fromClient
     */
    public BufferedInputStream getFromClient() {
	return fromClient;
    }

    /**
     * Sets the value of fromClient to that of the parameter.
     * 
     * @param fromClient
     *            the fromClient to set
     */
    public void setFromClient(BufferedInputStream fromClient) {
	this.fromClient = fromClient;
    }
}
