package com.zpj.bigData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.crypto.Data;

import org.junit.Test;

public class BigDataTest {

	@Test
	public void writeToFile() {
		float count = 0;
		File file = new File("F:\\test\\output\\bigData.txt");
		if (file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
			FileOutputStream out = new FileOutputStream(file, true);
			while (count < 10000000) {
				if (count % 100 == 0) {
					out.write("\n".getBytes());
				}
				out.write(((int) (Math.random() * 100000) + "\t").getBytes());
				out.flush();
				count++;
			}
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testMathRandom() {
		for (int i = 0; i < 20; i++) {
			double num = Math.random() * 10000;
			System.out.println((int) num);
		}
	}

	@Test
	public void testStatisticsByTreeMap() {
		try {
			Date date1 = new Date();
			Map<String, Integer> map = new HashMap<String, Integer>();
			FileReader fr = new FileReader("F:\\test\\output\\bigData02.txt");
			BufferedReader br = new BufferedReader(fr);
			String str =  br.readLine();
			while (str != null&&str.length() > 0) {
				String[] array = str.split("\\s+");
				for (int i = 0; i < array.length; i++) {
					Integer result = map.get(array[i]);
					if (result != null) {
						map.put(array[i], ++result);
					} else {
						map.put(array[i], 1);
					}
				}
				 str =  br.readLine();
			}
			Date date2 = new Date();
			System.out.println(date2.getTime()-date1.getTime());
		/*	Set keySet = map.keySet();
			Iterator<String> it = keySet.iterator();
			while (it.hasNext()) {
				String key = it.next();
				System.out.println(key + "---" + map.get(key));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int readData(FileInputStream input) {

		return 0;
	}

	@Test
	public void testReader() throws Exception {
		FileReader fr = new FileReader("F:\\test\\output\\bigData.txt");
		BufferedReader br = new BufferedReader(fr);
		String str = "";
		/*
		 * while((str =br.readLine()).length()>0){ System.out.println(str); }
		 */

		/*
		 * for(int i = 0; i<10; i++){ System.out.println(br.readLine()); }
		 */
		String str1 = "59453	46749	10148	43198	44198	15493	69842	63342	40251	33778	77377	78353	60103	93628	29751	36174	9413	26140	11269	6819	34137	53300	81005	8501	59812	27232	59938	81079	19037	92194	40229	40820	76151	18404	34876	59549	61776	86190	22434	85982	24076	43952	18524	67367	88486	24095	23545	35502	93130	77339	54282	92797	33271	77721	66053	71239	4872	58113	11564	39970	84713	59243	62853	5622	22486	13946	43450	83654	48924	42798	79366	18638	23228	7280	79530	96759	52958	52279	98996	24781	20006	71005	21516	46280	37405	42272	22034	28170	43455	22646	34186	78387	12017	24088	53054	49029	20811	71484	49265	38355	";
		String[] array = str1.split("\\s+");

		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}

	}

	@Test
	public void bigDataByArray(){
		int [] array = new int[1000000];

		try {
			Date date1 = new Date();
			Map<String, Integer> map = new HashMap<String, Integer>();
			FileReader fr = new FileReader("F:\\test\\output\\bigData02.txt");
			BufferedReader br = new BufferedReader(fr);
			String str =  br.readLine();
			while (str != null&&str.length() > 0) {
				String[] temp = str.split("\\s+");
				for (int i = 0; i < temp.length; i++) {
					int index = Integer.parseInt(temp[i]);
					array[index] = ++array[index];
					}
				 str =  br.readLine();
			}
			Date date2 = new Date();
			System.out.println(date2.getTime()-date1.getTime());
			for(int i= 0 ;i< array.length;i++){
				System.out.println(i+"---"+array[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
