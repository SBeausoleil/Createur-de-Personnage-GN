package com.sb.cdp.server;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Set;

public class Server {
    
    public static final int PORT = 4396;
    
    private Set<ServerConnection> connections;

    private ServerSocket server;
    
    private Interpreter interpreter;
    private DatabaseAccessor accessor;
    
    public Server(Interpreter interpreter, DatabaseAccessor accessor) {
	this.interpreter = interpreter;
	this.accessor = accessor;
    }

    @SuppressWarnings("resource") // Socket client is not closed
    public ServerConnection waitForConnection() throws IOException {
	Socket client = server.accept();
	OutputStream toClient = client.getOutputStream();
	BufferedInputStream fromClient = new BufferedInputStream(client.getInputStream());
	ServerConnection connection = new ServerConnection(null, client, toClient, fromClient);
	// TODO get User identity
	return connection;
    }
        
    /**
     * Returns the connections.
     * @return the connections
     */
    public Set<ServerConnection> getConnections() {
        return connections;
    }

    /**
     * Sets the value of connections to that of the parameter.
     * @param connections the connections to set
     */
    public void setConnections(Set<ServerConnection> connections) {
        this.connections = connections;
    }

    /**
     * Returns the interpreter.
     * @return the interpreter
     */
    public Interpreter getInterpreter() {
        return interpreter;
    }

    /**
     * Sets the value of interpreter to that of the parameter.
     * @param interpreter the interpreter to set
     */
    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    /**
     * Returns the accessor.
     * @return the accessor
     */
    public DatabaseAccessor getAccessor() {
        return accessor;
    }

    /**
     * Sets the value of accessor to that of the parameter.
     * @param accessor the accessor to set
     */
    public void setAccessor(DatabaseAccessor accessor) {
        this.accessor = accessor;
    }
}
