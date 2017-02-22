package com.sb.cdp.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.sb.cdp.User;
import com.sb.cdp.network.Interpreter;

public class ServerNetworkLayer {
    
    public static final int PORT = 4396;
    
    private Map<User, Connection> connections;
    private ServerSocket server;
    private Interpreter interpreter;
    
    public ServerNetworkLayer(Interpreter interpreter) {
	this.interpreter = interpreter;
	connections = new HashMap<>();
    }

    @SuppressWarnings("resource") // Socket client is not closed
    public Connection waitForConnection() throws IOException {
	Socket client = server.accept();
	OutputStream toClient = client.getOutputStream();
	BufferedInputStream fromClient = new BufferedInputStream(client.getInputStream());
	Connection connection = new Connection(client, toClient, fromClient);
	// TODO get User identity
	
	return connection;
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
}
