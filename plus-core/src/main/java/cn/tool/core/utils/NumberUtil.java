package cn.tool.core.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * 进行数字的乘除加减 避免精度丢失问题
 */
public final class NumberUtil {
	/**数据格式化，小数位都为0是省去小数**/
	public static final DecimalFormat ROUND_INT_FORMATER = new DecimalFormat("##.##");
	/**数据格式化，保留两位小数**/
	public static final DecimalFormat TWO_DECIMAL_FORMATER = new DecimalFormat("#0.00");
	
	/**
	 * 四舍五入，精确到小数点后两位 
	 */
	public static double round(double d) {
		BigDecimal number = new BigDecimal(String.valueOf(d));
		return number.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 精确的double加法运算，可避免浮点数直接进行加法运算出现的精度丢失问题
	 */
	public static double addDouble(double d1, double d2) {
		BigDecimal bgd1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bgd2 = new BigDecimal(String.valueOf(d2));
		return bgd1.add(bgd2).doubleValue();
	}
	
	/**
	 * 精确的double减法运算，可避免浮点数直接进行减法运算出现的精度丢失问题
	 */
	public static double subDouble(double d1, double d2) {
		BigDecimal bgd1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bgd2 = new BigDecimal(String.valueOf(d2));
		return bgd1.subtract(bgd2).doubleValue();
	}
	
	/**
	 * 精确的double乘法运算，可避免浮点数直接进行乘法运算出现的精度丢失问题
	 */
	public static double mulDouble(double d1, double d2) {
		BigDecimal bgd1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bgd2 = new BigDecimal(String.valueOf(d2));
		return bgd1.multiply(bgd2).doubleValue();
	}
	
	/**
	 * 精确的double除法运算，可避免浮点数直接进行除法运算出现的精度丢失问题
	 * @param d1
	 * @param d2
	 * @param scale 最大小数位数
	 */
	public static double divDouble(double d1, double d2, int scale) {
		BigDecimal bgd1 = new BigDecimal(String.valueOf(d1));
		BigDecimal bgd2 = new BigDecimal(String.valueOf(d2));
		return bgd1.divide(bgd2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	/**
	 * 判断两个double对象值是否相等，相等返回true，否则返回false
	 * @param d1
	 * @param d2
	 * @return
	 * @deprecated replaced by {@link Objects#equals(Object, Object)} since java 1.7
	 */
	public static boolean isEqual(Double d1, Double d2) {
		if(d1==d2) {
			return true;
		}
		if(d1==null || d2==null) {
			return false;
		}
		return d1.equals(d2);
	}
	
	/**
	 * 判断两个Integer对象值是否相等，相等返回true，否则返回false
	 * @param i1
	 * @param i2
	 * @return
	 * @deprecated replaced by {@link Objects#equals(Object, Object)} since java 1.7
	 */
	public static boolean isEqual(Integer i1, Integer i2) {
		if(i1==i2) {
			return true;
		}
		if(i1==null || i2==null) {
			return false;
		}
		return i1.equals(i2);
	}
	
	/**
	 * 按指定格式格式化数据
	 * @param d 要格式化的整形数据
	 * @param formatter 格式
	 * @return 返回格式化后的数据如果 formatter为null，返直接回d的字符串形式
	 */
	public static String format(Double d, DecimalFormat formatter) {
		if(d==null || formatter==null) {
			return String.valueOf(d);
		}
		
		return formatter.format(d);
	}
}
