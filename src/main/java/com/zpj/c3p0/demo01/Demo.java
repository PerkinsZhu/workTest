package com.zpj.c3p0.demo01;

/***
 @author  Perkins Zhu
 @date  2017年2月20日 上午8:25:27
 */
import java.util.ArrayList;
import java.util.List;

public class Demo {

	private SQLHelper sqlHelper = new SQLHelper();

	/**
	 * 测试query
	 */
	/*
	 * public void testQuery(){ String sql =
	 * "select * from crh2_station where id = 1"; List list =
	 * sqlHelper.query(sql); List<Crh2Station> crh2StationList = new
	 * ArrayList<Crh2Station>(); //对查询结果进行封装 for (int i = 0; i < list.size();
	 * i++) { Object object[] = (Object[]) list.get(i); Crh2Station crh2Station
	 * = new Crh2Station();
	 * crh2Station.setId(Integer.parseInt(object[0].toString()));
	 * crh2Station.setSlopeId(Integer.parseInt(object[1].toString()));
	 * crh2Station.setSlope(Double.parseDouble(object[2].toString()));
	 * crh2Station.setLength(Double.parseDouble(object[3].toString()));
	 * crh2Station.setEnd(Double.parseDouble(object[4].toString()));
	 * crh2Station.setHeight(Double.parseDouble(object[5].toString()));
	 * crh2StationList.add(crh2Station); } return crh2StationList; }
	 */

	/**
	 * 测试insert、update、delete
	 */
	/*
	 * public void testInsertOrUpdate(){ String sql =
	 * "delete from crh2_station where id = 1"; boolean b =
	 * sqlHelper.update(sql); if(b){//b为true则操作成功 System.out.println("操作成功");
	 * }else{//b为false则操作失败 System.out.println("操作失败"); } }
	 */

}