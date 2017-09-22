package com.zpj.jvm;

import com.zpj.extendsAndImplement.Person;

/***
 @author  Perkins Zhu
 @date  2017年3月14日 下午2:44:57
 */
public class ClassLoaderTest {

	ClassLoader classLoader = new Person().getClass().getClassLoader();
}
