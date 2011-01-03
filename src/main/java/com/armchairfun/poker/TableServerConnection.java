/*
 * ChatServerConnection.java
 *
 * This file is part of a tutorial on making a chat application using Flash
 * for the clients and Java for the multi-client server.
 * 
 * View the tutorial at http://www.broculos.net/
 */
package com.armchairfun.poker;

import java.net.*;
import java.io.*;

import com.armchairfun.poker.communication.PlayerRequest;

/**
 * Handles individual client connections to the server.
 *
 */
public class TableServerConnection extends Thread {
    protected Socket socket;
    protected BufferedReader socketIn;
    protected PrintWriter socketOut;
    protected TableServer server;
    
    /*
     * Creates a new instance of ChatServerConnection.
     *
     * @param socket the client's socket connection
     * @param server the server to each the client is connected
     **/
    public TableServerConnection(Socket socket, TableServer server) {
        this.socket = socket;
        this.server = server;
    }
    
    /**
     * Gets the remote address of the client.
     *
     * @return the socket address of the client connection
     */
    public SocketAddress getRemoteAddress() {
        return this.socket.getRemoteSocketAddress();
    }

    /**
     * Roots a debug message to the main application.
     * 
     * @param msg The debug message to be sent to the main application
     */
    protected void debug(String msg) {
        System.out.println(("ChatServerConnection (" + this.socket.getRemoteSocketAddress() + ")"+ ": " +msg));
    }
    
    /**
     * Waits from messages from the client and then instructs the server to send the messages to all clients.
     */
    public void run() {
        try {
            this.socketIn = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.socketOut = new PrintWriter(this.socket.getOutputStream(), true);
            this.server.writeToAll(this.getRemoteAddress() + " has connected.");
            String line = this.socketIn.readLine();
            debug("hello1:" + line);
            while (line != null) {
            	// try to create a PlayerRequest
            	PlayerRequest playerRequest = new PlayerRequest(line);
                
                // If it's a quit command, we remove the client from the server and exit
                if (line.compareToIgnoreCase("\\quit") == 0) {
                    if (this.server.remove(this.getRemoteAddress())) {
                        this.finalize();
                        return;
                    }
                }
                this.server.writeToAll(this.getRemoteAddress() + ": " + playerRequest.toString());
                line = this.socketIn.readLine();
            }
        }
        catch (Exception e) {
            debug("Exception (run): " + e.getMessage());
        }
    }
    
    /**
     * Sends a message to the connected party.
     *
     * @param msg the message to send
     */
    public void write(String msg) {
        try {
            this.socketOut.write(msg + "\u0000");
            this.socketOut.flush();
        }
        catch (Exception e) {
            debug("Exception (write): " + e.getMessage());
        }
    }

    /**
     * Closes the reader, the writer and the socket.
     */
    protected void finalize() {	 
        try {
            this.socketIn.close(); 
            this.socketOut.close();
            this.socket.close();
            debug("connection closed");
        }
        catch (Exception e) {
            debug("Exception (finalize): " + e.getMessage());
        }
    }
    
    public Socket getSocket() {
    	return this.socket;
    }
}