/**
 * Copyright (c) 2015 taqu.cn
 * All rights reserved.
 * @Project Name:tq-core
 * @ClassName:ValidateUtil.java
 * @Package:cn.taqu.core.common.util
 * @Description:
 * @author:laikunzhen
 * @date:2015年9月24日
 */
package cn.tool.core.utils;


import cn.tool.core.exception.ServiceException;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 各种检测方式
 */
public class ValidateUtil {

	//校验是否为小数
	private static final Pattern DECIMAL_REG = Pattern.compile("^-?\\d+(\\.\\d+([Ee]{1}[+-]?\\d*)?)?$");
	//校验是否为整数
	private static final Pattern INTEGER_REG = Pattern.compile("^-?\\d+$");
	//校验手机号的正则表达式
	private static final Pattern MOBILE_REG = Pattern.compile("^[1][0-9]{10}$");
	//校验邮箱号的正则表达式
	private static final Pattern EMAIL_REG = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	//校验url的正则表达式
	private static final Pattern URL_REG = Pattern.compile("^http(s)?://([\\w-]+\\.?)+(:\\d+)?(/[\\w- ./?%&=]*)?$");
	//校验ip:port的正则表达式
	private static final Pattern IP_PORT_REG = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}:\\d{0,5}$");
	//校验字母数字的正常表达式
	private static final Pattern ALPHANUMERIC_REG = Pattern.compile("^[0-9a-zA-Z]+$");
	//校验字身份证号（粗略校验）
	private static final Pattern IDENTITY_NO_REG = Pattern.compile("(^[1-9]\\d{5}(18|19|([2]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)");
	/**
	 * 校验是否为手机号,是返回true;不是返回false
	 */
	public static boolean isMobile(String text) {
		return MOBILE_REG.matcher(text).matches();
	}
	
	/**
	 * 校验字符串长度是否在指定的范围内，一个中文字符算两个长度，是返回true；否则返回false
	 */
	public static boolean checkLength(String text, int minLength, int maxLength) {
		try {
			int length = text.getBytes("GBK").length;
			return length>=minLength && length<=maxLength;
		} catch (UnsupportedEncodingException e) {
			throw new ServiceException("校验字符串长度错误");
		}
	}
	
	/**
	 * 判断是否是Email
	 * @return
	 */
	public static boolean isEmail(String text) {
		Matcher matcher = EMAIL_REG.matcher(text);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是Url
	 * @return
	 */
	public static boolean isUrl(String text) {
		Matcher matcher = URL_REG.matcher(text);
		if (matcher.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是IP:PORT
	 */
	public static boolean isIpPort(String text) {
		Matcher matcher = IP_PORT_REG.matcher(text);
		return matcher.find();
	}
	
	/**
	 * 判断是否为整数
	 * @return
	 */
	public static boolean isInteger(String text) {
		return INTEGER_REG.matcher(text).matches();
	}
	
	/**
	 * 判断是否为小数(包括科学计数法的判断)
	 * @return
	 */
	public static boolean isDecimal(String text) {
		return DECIMAL_REG.matcher(text).matches();
	}

	/**
	 * 判断是否为字母数字
	 * @param text
	 * @return
	 */
	public static boolean isAplhanumeric(String text) {
		return ALPHANUMERIC_REG.matcher(text).matches();
	}
	
	/**
	 * 判断对象是否为空
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if ("".equals(obj) || "".equals(obj.toString().trim())) {
			return true;
		}
		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length() == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		}

		// else
		return false;
	}
	
	/**
	 * 判断数组是否为空
	 * @return
	 */
	public static boolean isEmpty(Object[] array) {
		return (array == null || array.length == 0);
	}

	/**
	 * 判断对像是否非空
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	/**
	 * 判断字符串长度是否在指定长度之间
	 * @param string 要判断的字符串
	 * @param min 最小长度
	 * @param max 最大长度
	 */
	public static boolean stringLenBetween(String string, int min, int max) {
		int length = string.length();
		return length >= min && length <=max;
	}
	
	/**
	 * 判断身份证是否合法
	 * @param text
	 * @return
	 */
	public static boolean isIdentityNo(String text) {
		return IDENTITY_NO_REG.matcher(text).matches();
	}

	public static void main(String[] a){
		System.out.println(isMobile("1825079005"));
		System.out.println(checkLength("你好",4,18));
		System.out.println(isEmail("14733051@"));
		System.out.println(isUrl("http://www.biboom.cn"));
		System.out.println(isIdentityNo("35032119"));
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

	}
}
