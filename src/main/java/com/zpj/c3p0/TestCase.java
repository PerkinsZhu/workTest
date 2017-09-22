package com.zpj.c3p0;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

import com.zpj.c3p0.demo02.DBPool;

/***
 * @author Perkins Zhu
 * @date 2017年3月6日 下午3:55:53
 */
public class TestCase {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager
					.getConnection(
							"jdbc:mysql://192.168.3.195:3306/mytest?autoReconnect=true&failOverReadOnly=false&useUnicode=true&characterEncoding=utf-8&connectTimeout=10000",
							"root", "jinzhao");
			PreparedStatement psmt = null;
			String sql = "INSERT INTO user values(?,?,?,?,?,?,?,?,?,?,?)";
			for (int i = 6679; i < 20000000; i++) {
				
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, i);
				psmt.setString(2, i + "department");
				psmt.setString(3, i + "job");
				psmt.setString(4, i + "name");
				psmt.setString(5, i + "password");
				psmt.setString(6, i + "state");
				psmt.setString(7, i + "team");
				psmt.setString(8, i + "workid");
				psmt.setInt(9, i + 1);
				psmt.setDate(10, new Date(new java.util.Date().getTime()));
				psmt.setInt(11, i + 2);
				psmt.executeUpdate();
//				con.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
