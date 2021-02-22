package cn.tool.core.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组处理 进行对象转string等；
 */
public class ArrayUtil {
	private ArrayUtil() {
	}

	/**
	 * Object数组 转为 String数组
	 *
	 * @param args 参数
	 * @return String数组
	 */
	public static String[] objectToString(Object[] args) {
		if (ArrayUtils.isEmpty(args))
			return null;

		String[] temp = new String[args.length];
		for (int i = 0; i < args.length; i++) {
			temp[i] = args[i].toString();
		}
		return temp;
	}
}