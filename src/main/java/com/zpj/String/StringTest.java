package com.zpj.String;

import java.util.Date;
import java.util.StringTokenizer;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.CharRange;
import org.apache.commons.lang.CharSet;
import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.junit.Test;

public class StringTest {

	@Test
	public void test01() {
		String str = "sdfs:ds##d5:4fg|sd|#6g54s#dfg";
		str = str.replace("#", ":").replace("|", ":").replace("::", ":");
		System.out.println(str);
	}

	@Test
	public void test02() {
		StringTokenizer token = new StringTokenizer("0:0:0:0:0:0:0:0:0:0:0:0:0:0:0:0:0:0:0:1:0:0:0:0:0:0:0:0:0:0:", ";");
		System.out.println(token.nextToken());
		System.out.println(token.nextToken());
	}

	@Test
	public void test03() {
		float i = Float.parseFloat("06.0200");
		// String str = String.format("%04d", i);
		System.out.println(i);
		StringUtils.isBlank("");
	}

	@Test
	public void test04() {
		String str = "\t  f  d   nsds";
		System.out.println(str);
		System.out.println(StringUtils.clean(str));
		System.out.println(str.trim());
		System.out.println("----"+stripStart( "   xyIfa",null));
	}
	
	
	public  String stripStart(String str, String stripChars) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }
        int start = 0;
        if (stripChars == null) {
            while ((start != strLen) && Character.isWhitespace(str.charAt(start))) {
                start++;
            }
        } else if (stripChars.length() == 0) {
            return str;
        } else {
            while ((start != strLen) && (stripChars.indexOf(str.charAt(start)) != -1)) {
                start++;
            }
        }
        return str.substring(start);
    }

	@Test
	public void test05() {
		char c1 = 'a';
		char c2 = ' ';
		System.out.println((int)c1);
	}
	
	
	@Test
	public void test06(){
		System.out.println(StringUtils.ordinalIndexOf("werwed", "rw", 3));
		char [] chars = {'s','d'};
		System.out.println(StringUtils.indexOfAnyBut("sdfwe",chars));
		
	}
	
	@Test
	public void test07(){
		System.out.println(RandomStringUtils.randomAlphabetic(14));
		System.out.println(RandomStringUtils.randomAscii(14));
		System.out.println(RandomStringUtils.randomAlphabetic(14));
		System.out.println(NumberUtils.createInteger("45"));
		System.out.println(CharEncoding.isSupported("UTF-8"));
		System.out.println(DateFormatUtils.format(new Date(), "yyyy"));
	}
	
	@Test
	public void test08(){
		System.out.println("56.5".compareTo("56.55"));
	}
	
	

}
