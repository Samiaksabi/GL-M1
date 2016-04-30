package main;

import datanucleus.dao.DAOAccessor;
import server.FpsServer;

public class Main {

	public static void main(String[] args) throws Exception{
		DAOAccessor.populate_db();
		FpsServer server = new FpsServer();
		server.start();
	}

}
