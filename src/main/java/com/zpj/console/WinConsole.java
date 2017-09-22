package com.zpj.console;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * @author Perkins Zhu
 * @date 2017年3月16日 上午10:45:19
 */
public class WinConsole {

	private void runCmd() {
		BufferedReader buf;
		try {
			Process pro = Runtime.getRuntime().exec("", null, null);
			buf = new BufferedReader(new InputStreamReader(pro.getInputStream(), "utf-8"));
			String line = "";
			while ((line = buf.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
