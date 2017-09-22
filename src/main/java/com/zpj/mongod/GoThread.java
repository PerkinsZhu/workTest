package com.zpj.mongod;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class GoThread implements Runnable {
	String type ="";
	
	
	public GoThread(String type) {
		this.type = type;
	}


	@Override
	public void run() {
		DBCollection users;
		Mongo mg = new Mongo();
		DB db = mg.getDB("myTest");
		users = db.getCollection("user");
		for (int i = 0; i < 1000000; i++) {
			System.out.println(type+i+"---------");
			DBObject user = new BasicDBObject();
			user.put("name", "lisi");
			user.put("age", type+"11"+i);
			user.put("sex", "false");
			user.put("as", "false");
			users.save(user);
		}
		mg.close();
}

}
