package cn.tool.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 
 * @ClassName: SerialNumber
 * @Description: 流水号生成
 * 
 */
public class SerialNumber {

	private static int lastNumber = (int) getStart();

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");

	/********************************************************************
	 * 函数名称：GenerateFlowNumber::GenerateFlowNumber 功能描述：构造函数，初始化参数
	 * 
	 * 参数输入：void 参数输出：无
	 * 
	 * 历史记录： 创建日期： 作者：
	 * 
	 * 最后修改日期： 修改人： 修改内容：
	 ********************************************************************/
	public SerialNumber() {
	}

	/********************************************************************
	 * 函数名称：GenerateFlowNumber::getStart 功能描述：初始化流水号
	 * 
	 * 参数输入：void 参数输出：long randN：流水号 一般是生成随机的6位数
	 * 
	 * 历史记录： 创建日期： 作者：
	 * 
	 * 最后修改日期： 修改人： 修改内容：
	 ********************************************************************/
	public static long getStart() {
		Random and = new Random(System.currentTimeMillis());
		long randN = and.nextLong();
		randN = Math.abs(randN) % 1000000;
		return randN;
	}

	/********************************************************************
	 * 函数名称：GenerateFlowNumber::GenNumber 功能描述：获取流水号
	 * 
	 * 参数输入：void 参数输出：String：流水号  210221212944959387
	 * 
	 * 历史记录： 创建日期： 作者：
	 * 
	 * 最后修改日期： 修改人： 修改内容：
	 ********************************************************************/
	public static String GenNumber() {
		String s = sdf.format(new Date());
		lastNumber++;
		String m = "000000" + lastNumber;
		m = m.substring(m.length() - 6);
		return s + m;
	}

	/**
	 * 
	 * @Title: primaryKey 一般都是可以避免重复生成
	 * @Description: TODO(生成17位序列号)
	 * @return
	 */
	public static Long primaryKey() {
		Date date = new Date();
		long time = date.getTime();
		String timeStr = time + "";
		timeStr = timeStr.substring(3, timeStr.length());
		time = Long.parseLong(timeStr);
		int le = 17 - (time + "").length();
		String c = String.valueOf(Math.random()).substring(2, le + 2);
		return Long.parseLong(time + c);
	}

	/**
	 * 构建订单号 但是有自己默认需要的头部内容
	 * @param prefix
	 * @return
	 */
	public static String generalOrderId(String prefix) {
		long currentTimeMillis = System.currentTimeMillis();
		String order_id = prefix + currentTimeMillis
				+ ((int) (Math.floor(Math.random() * 9000)) + 1000);
		return order_id;
	}

	/**
	 * 
	 * @Title: primaryKey
	 * @Description: TODO(生成13位序列号)
	 * @return
	 */
	public static Long Key13() {
		Date date = new Date();
		long time = date.getTime();
		String dateline = time + "";
		dateline = dateline.substring(0, 10);
		String sPwd = String.valueOf(Math.random()).substring(2, 5);
		return Long.parseLong(dateline + sPwd);
	}

	/**
	 * 生成7位序列号
	 * @return
	 */
	public static Long key7() {
		Date date = new Date();
		long time = date.getTime();
		String dateline = time + "";
		dateline = dateline.substring(6, 13);
		if (dateline.startsWith("0")) {
			dateline = dateline.replaceFirst("0", "1");
		}
		return Long.parseLong(dateline);
	}

	/**
	 * 
	 * @Title: primaryKey
	 * @Description: TODO(生成10位序列号)
	 * @return
	 */
	public static Long Key10() {
		Date date = new Date();
		long time = date.getTime();
		String dateline = time + "";
		dateline = dateline.substring(6, 13);
		if (dateline.startsWith("0")) {
			dateline = dateline.replaceFirst("0", "1");
		}
		String sPwd = String.valueOf(Math.random()).substring(2, 5);
		return Long.parseLong(dateline + sPwd);
	}

	public static void main(String[] arg){
		System.out.println(getStart());
		System.out.println(GenNumber()); // 210221213026474435
		System.out.println(primaryKey()); // 39142267320604841
		System.out.println(generalOrderId("raveyXie")); // 利用数据构建订单号
		System.out.println(Key13());
		System.out.println(key7());
		System.out.println(Key10());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();

	}
}
