package com.zpj.acm.l006;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		start(90f);
	}

	private static void start(double range) {
		double Vmh = 0.0916666666666666666;
		double Vsm = 5.90000000000000000;
		double Vsh = 5.9916666666666666666;
		List<double[]> TmhData = getDate(Vmh, range);
		List<double[]> TshData = getDate(Vsh, range);
		List<double[]> TsmData = getDate(Vsm, range);
		double allTime = getAllTime(TmhData, TshData, TsmData);
		System.out.println(allTime / 36);
	}

	private static double getAllTime(List<double[]> tmhData, List<double[]> tshData, List<double[]> tsmData) {
		double allTime = 0.0;
		for (double[] mh : tmhData) {
			for (double[] sm : tsmData) {
				if (sm[1] < mh[0]) {// 如果没有交集则不再进行第三层循环
					continue;
				}
				if (sm[0] > mh[1]) {
					break;
				}
				double[] tempData = getValidData(mh, sm);
				for (double[] sh : tshData) {
					if (sh[1] < tempData[0]) {// 如果没有交集则不再计算
						continue;
					}
					if (sh[0] > tempData[1]) {
						break;
					}
					double[] temp = getValidData(tempData, sh);
					allTime += temp[1] - temp[0];
				}
			}
		}
		return allTime;
	}

	private static double[] getValidData(double[] data1, double[] data2) {
		double A1 = data1[0];
		double A2 = data1[1];
		double B1 = data2[0];
		double B2 = data2[1];
		double[] result = new double[2];
		if (A1 < B1) {
			if (A2 > B1 && A2 < B2) {
				result[0] = B1;
				result[1] = A2;
			} else if (A2 > B2) {
				result[0] = B1;
				result[1] = B2;
			}
		}
		if (A1 > B1 && A1 < B2) {
			if (A2 < B2) {
				result[0] = A1;
				result[1] = A2;
			} else if (A2 > B2) {
				result[0] = A1;
				result[1] = B2;
			}
		}
		return result;
	}

	private static List<double[]> getDate(double v, double range) {
		List<double[]> result = new ArrayList<double[]>();
		double Tmin = range / v;
		double Tmax = (360 - range) / v;
		double step = 360 / v;
		do {
			double temp[] = new double[2];
			if (Tmin < 3600 && Tmax > 3600) {
				temp[0] = Tmin;
				temp[1] = 3600;
			} else {
				temp[0] = Tmin;
				temp[1] = Tmax;
			}
			result.add(temp);
			Tmin += step;
			Tmax += step;
		} while (Tmin < 3600);
		return result;
	}
}