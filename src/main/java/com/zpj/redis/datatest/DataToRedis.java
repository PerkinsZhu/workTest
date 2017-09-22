package com.zpj.redis.datatest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import redis.clients.jedis.Jedis;

import com.zpj.c3p0.demo02.DBPool;

/***
 * @author Perkins Zhu
 * @date 2017年3月1日 下午1:49:57
 */
public class DataToRedis {

	public static void main(String[] args) {

		String url = "jdbc:mysql://192.168.3.11:3306/bdmcs_0122?autoReconnect=true&failOverReadOnly=false&useUnicode=true&characterEncoding=utf-8&connectTimeout=10000";
		String user = "root";
		String password = "123456";
		int num = 0;
		Long startTime = new Date().getTime();
		try {
			Class.forName("com.mysql.jdbc.Driver");// 指定连接类型
			Connection conn = DriverManager.getConnection(url, user, password);// 获取连接
			PreparedStatement statement = conn.prepareStatement("select * from tb_alarm_current");
			ResultSet rs = statement.executeQuery();
			Jedis jedis = new Jedis("127.0.0.1", 6379);
			jedis.select(3);
			while (rs.next()) {
				num++;
				String id = rs.getString("id");
				StringBuilder sb = new StringBuilder(id + rs.getString("affirmStatus") + rs.getString("affirmTime")
						+ rs.getString("affirmant") + rs.getString("alarmContent") + rs.getString("alarmStatus")
						+ rs.getString("alarmUpgrade") + rs.getString("areaID") + rs.getString("bureauID")
						+ rs.getString("deviceID") + rs.getString("deviceID") + rs.getString("endTime")
						+ rs.getString("isJob") + rs.getString("persistTime") + rs.getString("startTime")
						+ rs.getString("stationID") + rs.getString("alarmText") + rs.getString("servityID")
						+ rs.getString("idKey"));
				jedis.set(id, sb.toString());
			}

			System.out.println(new Date().getTime() - startTime + "-----" + num);

		} catch (Exception e) {
			System.out.println(new Date().getTime() - startTime + "-----" + num);
			e.printStackTrace();
		}
	}

	class ToRedis implements Runnable {
		@Override
		public void run() {

		}

	}

}
