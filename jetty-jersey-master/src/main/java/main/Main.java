package main;

import datanucleus.dao.DAOFactory;
import server.FpsServer;

public class Main {

	public static void main(String[] args) throws Exception{
		DAOFactory.populate_db();
		FpsServer server = new FpsServer();
		server.start();
	}

}
