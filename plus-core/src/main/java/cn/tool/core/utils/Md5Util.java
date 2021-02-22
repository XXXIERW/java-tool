/**   
 * @Title: Md5Util.java
 * @version V1.0   
 */

package cn.tool.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: Md5Util
 * @Description: TODO(md5加密类)
 * 用于密码的比对
 * 
 */
public class Md5Util {
	public static long lastRand;
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 创建md5生成密码
	 * cipher password
	 * 
	 * @param inputString
	 * @return
	 */
	public static String generatePassword(String inputString) {
		return encode(inputString);
	}

	/**
	 * 进行密码的校验
	 * validate password
	 * 
	 * @param password
	 * @param inputString
	 * @return
	 */
	public static boolean validatePassword(String password, String inputString) {
		if (password.equals(encode(inputString))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * encode md5编码
	 * 
	 * @param originString
	 * @return
	 */
	public static String encode(String originString) {
		if (originString != null) {
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] results = md.digest(originString.getBytes("UTF-8"));
				String resultString = byteArrayToHexString(results);
				return resultString;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * change the Byte[] to hex string
	 * 
	 * @param b
	 * @return
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (byte element : b) {
			resultSb.append(byteToHexString(element));
		}
		return resultSb.toString();
	}

	/**
	 * change a byte to hex string
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * @Title：generateUserId
	 * @Description：
	 * @return  Long
	 */
	public static Long generateUserId() {
		Random and = new Random(System.currentTimeMillis() + lastRand);
        long randN = and.nextLong() + lastRand;
        randN = Math.abs(randN) % 100000000;
        lastRand = randN;
        String s = "168" + randN + "00000000";
        return Long.valueOf(s.substring(0, 11));
	}

	/**
	 * @Title：generate10Key
	 * @Description：生成10位数的主键
	 * @return  Long
	 */
	public static Long generate10Key() {
		Date date = new Date();
		long time = date.getTime();
		String dateline = time + "";
		dateline = dateline.substring(6, 13);
		if (dateline.startsWith("0")) {
			dateline = dateline.replaceFirst("0", "1");
		}
		String sPwd = String.valueOf(Math.random()).substring(2,5);
		return Long.parseLong(dateline+sPwd);
	}

	/**
	 * 对于传输内容加密
	 * @param content
	 * @return
	 */

	public static String encryptSHA1(String content){
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(content.getBytes());
			byte messageDigest[] = digest.digest();
			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : messageDigest) {
				String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace(); // ignored
			return "";
		}
	}

	public static void main(String[] arg){
		System.out.println(generateUserId()); // 构建用户id
		System.out.println(generate10Key());
		System.out.println(encryptSHA1("raveyXie"));
	}

}
