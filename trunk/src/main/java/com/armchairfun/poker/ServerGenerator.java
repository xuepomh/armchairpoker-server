package com.armchairfun.poker;

import java.util.ArrayList;
import java.util.List;

import com.armchairfun.poker.common.Table;
import com.armchairfun.poker.dao.NoTablesFoundException;
import com.armchairfun.poker.dao.TableDao;

/**
 * Main is used to start the servers and handle the debug messages.
 * 
 */
public class ServerGenerator {
	private static final int lobbyPort = 5555;
	private static final int policyPort = lobbyPort + 1;
	private LobbyServer lobbyServer;
	private PolicyServer policyServer;
	private List<TableServer> tableServers;

	public void buildAllServers() {
		buildPolicyServer();
		buildLobbyServer();
		buildTableServers();

	}

	public void buildPolicyServer() {

		// fire up the policy server
		PolicyServer policyServer = new PolicyServer(policyPort);
		policyServer.start();
		this.policyServer = policyServer;

	}

	public void buildLobbyServer() {

		// we need one lobby server for all players for global chat
		// and table info etc
		LobbyServer lobbyServer = new LobbyServer(lobbyPort);
		lobbyServer.start();
		this.lobbyServer = lobbyServer;

	}

	public void buildTableServers() {
		// load list of tables
		// for each table, create a table server
		TableDao tableDao = new TableDao();
		List<Table> tableList;
		tableServers = new ArrayList<TableServer>();

		try {
			tableList = tableDao.getTableList();
			for (Table table : tableList) {

				TableServer tableServer = new TableServer(table.getPort());
				tableServer.start();
				this.tableServers.add(tableServer);

			}
		} catch (NoTablesFoundException e) {
			// TODO should LOG something
		}

	}

	public void stopAllServers() {
		stopPolicyServer();
		stopLobbyServer();
		stopTableServers();
	}

	public void stopPolicyServer() {
		if (this.policyServer != null && this.policyServer.listening) {
			this.policyServer.finalize();
		}
	}

	public void stopLobbyServer() {
		if (this.lobbyServer != null && this.lobbyServer.listening) {
			this.lobbyServer.finalize();
		}
	}

	public void stopTableServers() {
		if (this.tableServers == null) {
			return;
		}
		
		for (TableServer tableServer : tableServers) {
			if (tableServer != null && tableServer.listening) {
				tableServer.finalize();
			}
		}
	}

	public LobbyServer getLobbyServer() {
		return lobbyServer;
	}

	public void setLobbyServer(LobbyServer lobbyServer) {
		this.lobbyServer = lobbyServer;
	}

	public PolicyServer getPolicyServer() {
		return policyServer;
	}

	public void setPolicyServer(PolicyServer policyServer) {
		this.policyServer = policyServer;
	}

	public List<TableServer> getTableServers() {
		return tableServers;
	}

	public void setTableServers(List<TableServer> tableServers) {
		this.tableServers = tableServers;
	}

}
