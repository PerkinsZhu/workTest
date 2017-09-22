package com.zpj.other.package02;

public class TestMap<K, V, T> {// 三个泛型
	private T name;
	private V value;

	public TestMap(T name, V value) {
		super();
		this.name = name;
		this.value = value;
	}

}