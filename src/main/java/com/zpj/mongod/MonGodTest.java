package com.zpj.mongod;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.QueryOperators;
import com.mongodb.ServerAddress;

public class MonGodTest {
	DBCollection users;
	MongoClient mg;
	DBCursor cursor;
	@Before
	public void init (){
		mg = new MongoClient();
		DB db = mg.getDB("myTest");
		users = db.getCollection("user");
	}
	@After
	public void destroy(){
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
		if(mg != null){
			mg.close();
		}
	}
	
	@Test
	public void testQuery(){
		DBCursor cursor = users.find();
		while(cursor.hasNext()){
			System.out.println(cursor.next());
		}
	}
	
	@Test
	public void testAdd(){
		DBObject  user = new BasicDBObject();
		user.put("name","lisi");
		user.put("age","132");
		user.put("sex","false");
		user.put("as","false");
		users.save(user);
		testQuery();
	}
	
	@Test
	public void testQueryByType(){
		/*
		 * $gt：>
		 * $gte：>=
		 * $eq: =
		 * $ne: !=
		 * $lt: <
		 * $lte: <=
		 * $in: in(后面的值为bson对象数组)
		 * $nin: not in(后面的值为bson对象数组)
		 */
		BasicDBObject gt = new BasicDBObject("$gte",25);
		 BasicDBObject queryObject = new BasicDBObject("age",gt);
		 
		//完全匹配
	        //Pattern pattern = Pattern.compile("^name$", Pattern.CASE_INSENSITIVE);
	        //右匹配
	        //Pattern pattern = Pattern.compile("^.*name$", Pattern.CASE_INSENSITIVE);
	        //左匹配
	        //Pattern pattern = Pattern.compile("^name.*$", Pattern.CASE_INSENSITIVE);
	        //模糊匹配
		Pattern pattern = Pattern.compile("^.*name8.*$", Pattern.CASE_INSENSITIVE);
		BasicDBObject query = new BasicDBObject();
		query.put("name", pattern);
		BasicDBObject sort = new BasicDBObject();
		// 1,表示正序； －1,表示倒序
		sort.put("name", 1);
		int start = 10;
		int pageSize = 100;
	        
	        
	        
	        
		 cursor = users.find(queryObject).sort(sort).skip(start).limit(pageSize);
	}
	
	@Test
	public void testDelete(){
	}
	
	public void query() {
		// $or (查询id等于1或者id等于2的数据)
		BasicDBObject queryObject = new BasicDBObject().append(QueryOperators.OR, new BasicDBObject[] {
				new BasicDBObject("id", 1), new BasicDBObject("id", 2) });
		find(queryObject, "(查询id等于1或者id等于2的数据)");

		// $and(查询id等于10并且name等于10的数据)
		queryObject = new BasicDBObject().append(QueryOperators.AND, new BasicDBObject[] { new BasicDBObject("id", 10),
				new BasicDBObject("name", "10") });
		find(queryObject, "(查询id等于10并且name等于10的数据)");

		// $gt（查询id大于10的数据）
		queryObject = new BasicDBObject().append("id", new BasicDBObject().append(QueryOperators.GT, 10));
		find(queryObject, "（查询id大于10的数据）");
		// $gte （查询id大于等于10的数据）
		queryObject = new BasicDBObject().append("id", new BasicDBObject().append(QueryOperators.GTE, 11));
		find(queryObject, "（查询id大于等于11的数据）");
		// $lt
		queryObject = new BasicDBObject().append("id", new BasicDBObject().append(QueryOperators.LT, 2));
		find(queryObject, "（查询id小于2的数据）");
		// $lte
		queryObject = new BasicDBObject().append("id", new BasicDBObject().append(QueryOperators.LTE, 2));
		find(queryObject, "（查询id小于等于2的数据）");

		// $in
		queryObject = new BasicDBObject().append("id", new BasicDBObject(QueryOperators.IN, new int[] { 1, 2 }));
		find(queryObject, "（查询id为1和2的数据）");
		// $nin
		queryObject = new BasicDBObject().append("id", new BasicDBObject(QueryOperators.NIN, new int[] { 1, 2, 3, 4, 5,
				6, 7, 8, 9 }));
		find(queryObject, "（查询id不为1,2,3,4,5,6,7,8,9的数据）");

		// 还有很多其他的高级查询方式可以参见QueryOperators类
	}  
    public void find(BasicDBObject condition, String str) {}  
  
    
    public static MongoClient getMongoClient()throws Exception{
        try {
            //===================================================//
            List<ServerAddress> serverList = new ArrayList<ServerAddress>();
            serverList.add(new ServerAddress("192.168.18.85", 27017));
            //===================================================//
            List<MongoCredential> mcList = new ArrayList<MongoCredential>();
            String username = "root";
            String database = "demo";
            char[] password = "root123".toCharArray();
            mcList.add(MongoCredential.createCredential(username, database,password));
            //===================================================//
            MongoClientOptions.Builder builder = MongoClientOptions.builder();
            // 与目标数据库能够建立的最大connection数量为50 
            builder.connectionsPerHost(50);  
            // 如果当前所有的connection都在使用中，则每个connection上可以有50个线程排队等待  
            builder.threadsAllowedToBlockForConnectionMultiplier(50); 
            // 一个线程访问数据库的时候，在成功获取到一个可用数据库连接之前的最长等待时间为2分钟 
            // 这里比较危险，如果超过maxWaitTime都没有获取到这个连接的话，该线程就会抛出Exception 
            // 故这里设置的maxWaitTime应该足够大，以免由于排队线程过多造成的数据库访问失败 
            builder.maxWaitTime(1000*60*2);  
            // 与数据库建立连接的timeout设置为1分钟  
            builder.connectTimeout(1000*60*1);   
            //===================================================//
            MongoClientOptions mco = builder.build(); 
            return new MongoClient(serverList, mcList, mco);
        } catch (Exception e) {
            throw e;
        }
    }
}
