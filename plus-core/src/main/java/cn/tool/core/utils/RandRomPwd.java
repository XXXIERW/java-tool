package cn.tool.core.utils;
import java.util.Random;
/**
 *  获取 随机六位数内容 短信验证码，密码等等
 * @ClassName: RandRomPwd
 * @Description: TODO(获取像短信验证码之类的东西)
 * 
 */
public class RandRomPwd {
	/**
	 * 
	 * @Title: getRandRomPwd
	 * @Description: TODO(随机获取6位密码)
	 * @return
	 */
	public static String getRandRomPwd() {
		Random and = new Random(System.currentTimeMillis());
		long randN = and.nextLong();
		randN = Math.abs(randN);
		String randStr = String.valueOf(randN);
		int length = randStr.length() - 6;
		randStr = randStr.substring(length, randStr.length());
		return randStr;
	}
	public static void main(String[] args) {
		Random and = new Random(System.currentTimeMillis());
		long randN = and.nextLong();
		System.out.println(randN);
		randN = Math.abs(randN) % 1000000;
		String randStr = String.valueOf(randN);
		System.out.println(randStr);
	}
}