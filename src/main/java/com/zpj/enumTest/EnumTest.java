package com.zpj.enumTest;

import java.util.EnumMap;
import java.util.EnumSet;

import org.junit.Test;

import com.zpj.tool.printCollection.PrintCollection;

public class EnumTest {

	@Test
	public void test01() {
		System.out.println(Color.BLACK.toString());
	}

	@Test
	public void enumSetTest() {
		EnumSet<Color> eset = EnumSet.allOf(Color.class);
		PrintCollection.printByIterator(eset);
	}

	@Test
	public void enumMapTest() {
		EnumMap<Color, String> emap = new EnumMap<Color, String>(Color.class);
		emap.put(Color.BLACK, "heise");
		emap.put(Color.BLUE, "labse");
		PrintCollection.printByIterator(emap.keySet());
	}

	@Test
	public void argsTest() {
		enumArgs(Color.BLACK);
	}

	private void enumArgs(Color col) {
		System.out.println(col);
	}

}
