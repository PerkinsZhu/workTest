package com.zpj.tool.creator;

import org.apache.commons.lang.math.RandomUtils;

public class Creator {

	public static Integer[] createIntegerArray(int size) {
		return createIntegerArray(size, null);
	}

	public static Integer[] createIntegerArray(int size, Integer maxValue) {
		if (size < 0) {
			size = 10;
		}
		Integer[] result = new Integer[size];
		for (int i = 0; i < size; i++) {
			if (maxValue == null) {
				result[i] = RandomUtils.nextInt();
			} else {
				result[i] = RandomUtils.nextInt(maxValue);
			}
		}
		return result;
	}
}
