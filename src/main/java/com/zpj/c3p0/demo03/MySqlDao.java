package com.zpj.c3p0.demo03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 @author  Perkins Zhu
 @date  2017年3月30日 下午5:00:11
 */
public class MySqlDao implements Runnable{
	Connection con;
	String name ;
	public PreparedStatement pst;
	String sql ="insert into test(name,time)  values(?,?)";
	
	public MySqlDao(Connection con,String name) {
		this.name = name ;
		this.con = con;
	}
	
	@Override
	public void run() {
		try {
			for(int i =0;i<10;i++){
			pst = con.prepareStatement(sql);
			pst.setString(1, name+i);
			pst.setString(2, new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date()));
			pst.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
