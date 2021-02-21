/*
 * Copyright (c) 2015 taqu.cn
 * All rights reserved.
 */
package cn.tool.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 进行字符串的各种转化 去除空格 转化为Integer/Long/等等
 * @author:laikunzhen
 */
public class StringUtil {

	private static final Pattern URL_DOMAIN_PATTERN = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");

	/**
	 * 生成指定数量的字母拼接 比如有的时候验证码生成等操作
	 * @param num
	 * @return
	 */
	public static String getAlphabet(int num) {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		StringBuffer alpBuffer = new StringBuffer();
		for (int i = 0; i < num; i++) {
			alpBuffer.append(chars.charAt((int) (Math.random() * 26)));
		}
		return alpBuffer.toString();
	}

	/**
	 * 生成指定数量的 数字和英文凭借
	 * @param length
	 * @return
	 */
	public static String gen(int length) {
		char[] ss = new char[length];
		int i = 0;
		while (i < length) {
			int f = (int) (Math.random() * 3);
			if (f == 0)
				ss[i] = (char) ('A' + Math.random() * 26);
			else if (f == 1)
				ss[i] = (char) ('a' + Math.random() * 26);
			else
				ss[i] = (char) ('0' + Math.random() * 10);
			i++;
		}
		String is = new String(ss);
		return is;
	}

	/**
	 * 获取url中的顶级域名 获取主域名
	 * http://www.biboom.cn 获取 biboom.cn
	 * @param url
	 */
	public static String getUrlTopDomain(String url) {
		if(StringUtils.isBlank(url)) {
			return "";
		}
		
		String host = null;
		try {
			host = new URL(url).getHost();
		} catch (MalformedURLException e) {
			return null;
		}
		
		Matcher matcher = URL_DOMAIN_PATTERN.matcher(host);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/**
	 * 进行手机号指定内容的脱敏处理
	 * @param mobile
	 * @return
	 */
	public static String mobileSecret(String mobile) {
		if(mobile == null || mobile.trim().length()==0) {
			return mobile;
		}
		
		mobile = mobile.trim();
		if(mobile.length()<4) {
			return mobile;
		}
		return mobile.substring(0, 3) + "****" + mobile.substring(mobile.length()-4);
	}
	
	/**
	 * 如果字符串为null，返回空字符串，否则返回trim后的字符串
	 *
	 * 进行字符串 首位空格处理
	 */
	public static String nullToEmptyWithTrim(String text) {
		if(text == null) {
			return "";
		}
		
		return text.trim();
	}
	
	/**
	 * 如果数值为null返回空字符串，否则返回该数值的字符串表示
	 */
	public static String nullObjectToEmptyString(Object obj) {
		if(obj == null) {
			return "";
		}
		
		return String.valueOf(obj);
	}
	/**
	 * 如果数值为null返回空字符串，否则返回该数值的字符串表示
	 * 将数字转为字符串
	 */
	public static String nullNumberToEmptyString(Number number) {
		if(number == null) {
			return "";
		}
		
		return number.toString();
	}
	
	/**
	 * 如果Boolean值为null返回空字符串，否则返回该boolean值的字符串表示
	 */
	public static String nullBooleanToEmptyString(Boolean bool) {
		if(bool == null) {
			return "";
		}
		
		return bool.toString();
	}
	
	/**
	 * 将字符串转换为Byte
	 */
	public static Byte toByte(String string, Byte defaultValue) {
		if(StringUtils.isBlank(string)) {
			return defaultValue;
		}
		return Double.valueOf(string).byteValue();
	}
	
	/**
	 * 将字符串转换为Short
	 */
	public static Short toShort(String string, Short defaultValue) {
		if(StringUtils.isBlank(string)) {
			return defaultValue;
		}
		return Double.valueOf(string).shortValue();
	}
	
	/**
	 * 将字符串转换为Integer
	 */
	public static Integer toInteger(String string, Integer defaultValue) {
		if(StringUtils.isBlank(string)) {
			return defaultValue;
		}
		return Double.valueOf(string).intValue();
	}
	
	/**
	 * 将字符串转换为Long
	 */
	public static Long toLong(String string, Long defaultValue) {
		if(StringUtils.isBlank(string)) {
			return defaultValue;
		}
		return Double.valueOf(string).longValue();
	}
	
	/**
	 * 将字符串转换为Float
	 */
	public static Float toFloat(String string, Float defaultValue) {
		if(StringUtils.isBlank(string)) {
			return defaultValue;
		}
		return Double.valueOf(string).floatValue();
	}
	
	/**
	 * 将字符串转换为Double
	 */
	public static Double toDouble(String string, Double defaultValue) {
		if(StringUtils.isBlank(string)) {
			return defaultValue;
		}
		return Double.valueOf(string.trim());
	}
	
	/**
	 * 判断字符串是否包含四字节汉字
	 */
	public static boolean hasUcs4(String text) {
		if(StringUtils.isBlank(text)) {
			return false;
		}
		byte[] bs = text.getBytes(Charset.forName("UTF-8"));
		for(byte b : bs) {
			if((b&0xF8) == 0xF0) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] arg){
		System.out.println(getAlphabet(6));
		System.out.println(gen(6));
		System.out.println(getUrlTopDomain("http://www.biboom.cn"));
		System.out.println(mobileSecret("18250790040"));
		System.out.println(nullToEmptyWithTrim("     hello word!"));
		System.out.println(nullNumberToEmptyString(123456));
//		System.out.println(toByte("raveyXie", (byte) 1));
		System.out.println(hasUcs4("你好呀啊"));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

	}
}
