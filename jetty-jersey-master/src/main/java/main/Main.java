package main;

import server.FpsServer;

public class Main {
	public static void main(String[] args) throws Exception{
		FpsServer server = new FpsServer();
		server.start();
	}

}
