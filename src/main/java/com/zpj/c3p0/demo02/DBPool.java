package com.zpj.c3p0.demo02;

/***
 @author  Perkins Zhu
 @date  2017年2月20日 上午8:30:24
 */
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zpj.tool.printCollection.PrintCollection;

public class DBPool {
	private static DBPool dbPool;
	private ComboPooledDataSource dataSource;

	static {
		dbPool = new DBPool();
	}

	public   DBPool(){       
           try   {       
                   dataSource=new   ComboPooledDataSource();       
                   dataSource.setUser( "root");       
                   dataSource.setPassword( "jinzhao");       
//                   dataSource.setJdbcUrl( "jdbc:mysql://192.168.3.12:3306/zpj_bdmcs?autoReconnect=true&failOverReadOnly=false&useUnicode=true&characterEncoding=utf-8&connectTimeout=10000"); 
                   dataSource.setJdbcUrl( "jdbc:mysql://192.168.3.195:3306/mytest?autoReconnect=true&failOverReadOnly=false&useUnicode=true&characterEncoding=utf-8&connectTimeout=10000"); 
                   dataSource.setDriverClass( "com.mysql.jdbc.Driver"); 
                   dataSource.setInitialPoolSize(2); 
                   dataSource.setMinPoolSize(1); 
                   dataSource.setMaxPoolSize(10); 
                   dataSource.setIdleConnectionTestPeriod(5000);
                   dataSource.setMaxStatements(50); 
                   dataSource.setMaxIdleTime(60); 
//                   dataSource.setCheckoutTimeout(2000);
//                   System.out.println("==="+dataSource.getCheckoutTimeout());
           }   catch   (PropertyVetoException   e)   {       
               throw   new   RuntimeException(e);       
           }       
   }	public final static DBPool getInstance() {
		return dbPool;
	}

	public final Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("无法从数据源获取连接 ", e);
		}
	}
	static int  num = 0;
	public static void main(String[] args) throws SQLException {
		List<Connection> lists = new ArrayList<Connection>();
		Long data = new Date().getTime();
		Connection con = null;
		try {
			while(true){
				con = DBPool.getInstance().getConnection();
				lists.add(con);
				Long temp = new Date().getTime();
				System.out.println(++num+"----"+(temp - data));
				data = temp;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
//			PrintCollection.printByIterator(lists);
		System.out.println(lists.size());
		}
	}
}