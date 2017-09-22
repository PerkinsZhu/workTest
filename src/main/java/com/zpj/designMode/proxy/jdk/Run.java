package com.zpj.designMode.proxy.jdk;

import com.zpj.designMode.proxy.MrLi;
import com.zpj.designMode.proxy.Person;

/***
 * @author Perkins Zhu
 * @date 2017年3月13日 上午8:51:31
 */
public class Run {

	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
		Person person = (Person) new JDKProxy().getInstance(new MrLi());
		//注意这里的person不是目标类person，而是代理类person：debug的时候显示null，有'$'标识符
		person.doWork();
	}
}
