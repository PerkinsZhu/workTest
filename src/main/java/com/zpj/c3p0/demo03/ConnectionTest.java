package com.zpj.c3p0.demo03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/***
 @author  Perkins Zhu
 @date  2017年3月30日 下午4:56:03
 */
public class ConnectionTest {
public static void main(String[] args) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.3.195:3306/mytest?autoReconnect=true&failOverReadOnly=false&useUnicode=true&characterEncoding=utf-8&connectTimeout=10000","root","jinzhao");
		for(int i =0; i<1000;i++){
			Thread thread = new Thread(new MySqlDao(connection,i+""));
			thread.start();
		}
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
	
}
