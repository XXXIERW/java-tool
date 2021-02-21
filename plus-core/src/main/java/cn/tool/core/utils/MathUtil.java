package cn.tool.core.utils;

/**
 *
 * @since 1.0
 */
public class MathUtil {

    /**
     * return positive int value of originValue
     * 原始值的正int值
     */
    public static int getPositive(int originValue){
        return 0x7fffffff & originValue;
    }

    public static void main(String[] arg){
        System.out.println(getPositive(-4));
    }
}
