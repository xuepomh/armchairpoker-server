package com.armchairfun;

/**
 * Main is used to start the servers and handle the debug messages.
 *
 */
public class Main {
    public static LobbyServer lobbyServer;
    public static PolicyServer policyServer;
    public static TableServer tableServer;
    
    
    /**
     * Starts the chat server and the policy server
     *
     * @param args the command line arguments (first is the chat server port and second is the policy server port)
     */
    public static void main(String[] args) {
        try {
            int lobbyPort = 5555;
            int policyPort = lobbyPort + 1;
            int tablePort = policyPort + 1;
            
            for (int i = 0; i < args.length; i++) {
                if (i == 0) {
                	lobbyPort = Integer.parseInt(args[i]);
                }
                
                if (i == 1) {
                    policyPort = Integer.parseInt(args[i]);
                }
            }
            
            // fire up the policy server
            PolicyServer policyServer = new PolicyServer(policyPort);
            policyServer.start();
            
            // we need one lobby server for all players for global chat
            // and table info etc
            LobbyServer lobbyServer = new LobbyServer(lobbyPort);
            lobbyServer.start();
            
            // a separate server for each table, for localized chat and actions
            //TODO load all the tables from the DB and create a TableServer for each
            TableServer tableServer = new TableServer(tablePort);
            tableServer.start();
            
            Main.lobbyServer = lobbyServer;
            Main.policyServer = policyServer;
            Main.tableServer = tableServer;
        }
        catch (Exception e) {
            System.out.println("Main: "+e.getMessage());
        }
    }
}
