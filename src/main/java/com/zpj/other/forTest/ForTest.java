package com.zpj.other.forTest;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/***
 @author  Perkins Zhu
 @date  2017年4月27日 下午3:58:23
 */
public class ForTest {

	
	
	@Test
	public void testFor(){
		for(;;){
			System.out.println("=============");
		}
	}
	@Test
	public void testHashCode(){
		Student stu1 = new Student("JACK");
		Student stu2 = new Student("JACK");
		System.out.println(stu1.equals(stu2));//equal返回true
		System.out.println(stu1.hashCode()+"----"+stu2.hashCode());//hashCode 结果不同。由于Student未覆写hashCode方法，在计算的时候使用的是的Object.hashCode(),计算出来两者的hashCode不一致
		HashMap map = new HashMap();
		map.put(stu1,"123456");

		System.out.println(map.get(stu2));//（逻辑上）本想取出“123456”，但hashMap在取值的时候把两者的hashCode加入比较，
		/**
		 * 研究的对象是同一个类的两个不同对象（引用不同）
		 * hashMap 判断key相等的方法：
		 *		if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
		 *			return e;
		 *		hashCode 必须相等，key的引用或者内容两者只要有一个相等即可。
		 *
		 *因此，上述如果想要 map.get(stu2)中取出“123456”的时候就必须不适用Object.hashCode()来计算hashCoed,需要自己重新覆写hashCode().
		 *  覆写的时候要注意，生成hashCode的算法中不要使用 不参与equal判断的属性，否则就无法生成相同的hashCode。
		 *
		 *  “覆写equal必须要覆写hashCode” 并不是必须的  而是当hashCode方法参与判断等值运算的时候，为了根据自己自定义的“逻辑相等”得到自己想要的结果，此时需要根据
		 *   自己的需要覆写hashCode()方法，以便于让引用不同的两者在逻辑上视为相同的对象。
		 *
		 *
		 *  true
		 	668849042----434176574
            null
		 */
	}

	class Student {
		private String name;
		public Student(String name){
			this.name = name;
		}
		@Override
		public boolean equals(Object obj) {
			//逻辑上名字相等便认为两者相等
			return this.name.equals(((Student)obj).name);
		}
	}


	@Test
	public void testException() {
		dealInfo("helll");
		try {
			dealMessage("hello");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String dealInfo(String info) {
		throw new NullPointerException();//运行时异常，不强制进行处理(可以不进行捕获或抛出，但如果进行了也没有错误)
	}

	public static String dealMessage(String info) throws IOException {
		throw new IOException();//强制处理，要么抛出，要么捕获,如果抛出则在调用层继续进行处理，否则编译不通过
	}
/*	public void dealFile(String path){
		if(path == null){
			throw new FileNotFoundException();
		}

	}*/


}
