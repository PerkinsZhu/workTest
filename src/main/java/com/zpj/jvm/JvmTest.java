package com.zpj.jvm;


import java.util.ArrayList;
import java.util.List;

import com.zpj.bean.Student;

public class JvmTest {

	public static void main(String[] args) {
		testOutOfMemory();
	}

	

	
	/**
	*VM Args：-Xmx20M-XX：MaxDirectMemorySize=10M
	*@author zzm
	*//*
	private static  int _1MB=1024*1024;
	public static void test()throws Exception{
	Field unsafeField=Unsafe.class.getDeclaredFields()[0];
	unsafeField.setAccessible(true);
	Unsafe unsafe=(Unsafe)unsafeField.get(null);
	while(true){
	unsafe.allocateMemory(_1MB);
	}
	}*/
	
	
	
	/**
	 * VM Args：-Xms20m-Xmx20m-XX：+HeapDumpOnOutOfMemoryError
	 * 
	 * @author zzm
	 */
	public static void testOutOfMemory() {
		List<Student> list = new ArrayList<Student>();
		for (int i = 0; i < 10000000; i++) {
			Student str = new Student();
			list.add(str);
		}
	}

	/**
	 * VM Args：-Xss=128k
	 */
	private static int stackLength = 1;

	public static void stackLeak() {
		stackLength++;
		stackLeak();
	}
	
	/**
	 *-XX:PermSize=10M -XX:MaxPermSize=10M
	 */
	public static void testConstantPool() {
		// 使用List保持着常量池引用,避免Full GC回收常量池行为
		List<String> list = new ArrayList<String>();
		// 10MB的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}

	

	/*public static void main(String[] args) throws Exception {
		ClassLoader myLoader = new ClassLoader() {
			@Override
			public Class<?> loadClass(String name) throws ClassNotFoundException {
				try {
					String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
					InputStream is = getClass().getResourceAsStream(fileName);
					if (is == null) {
						return super.loadClass(name);
					}
					byte[] b = new byte[is.available()];
					is.read(b);
					return defineClass(name, b, 0, b.length);
				} catch (IOException e) {
					throw new ClassNotFoundException(name);
				}
			}
		};
		Object obj = myLoader.loadClass("org.fenixsoft.classloading.ClassLoaderTest").newInstance();
		System.out.println(obj.getClass());
		System.out.println(obj instanceof org.fenixsoft.classloading.ClassLoaderTest);
	}*/
	
	
}
