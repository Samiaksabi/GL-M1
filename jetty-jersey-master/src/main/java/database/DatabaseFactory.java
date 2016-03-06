package database;

import database.fake.DatabaseFake;

public class DatabaseFactory {
	
	public static Database getDatabase(){
		DatabaseFake db = new DatabaseFake();
		//DatabaseFake.DatabaseFakeUtils.populate_db(db);
		return db;
	}
	
}
