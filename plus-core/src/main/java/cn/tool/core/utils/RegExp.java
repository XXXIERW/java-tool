package cn.tool.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配类
 * @author qiuyuhua
 * 2016年8月5日 上午10:51:13
 */
public final class RegExp {
	/**
	 * 全字符串匹配
	 * @param regex
	 * @param input
	 * @return
	 */
    public static boolean isMatch(String regex, String input){
        if (null == input) return false;
        return input.matches(regex);
    }
    
    /**
     * 包含子字符串
     * @param regex
     * @param input
     * @return
     */
    public static boolean contains(String regex, String input){
        Pattern r = Pattern.compile(regex);
        return r.matcher(input).find();
    }

    /**
     * 实现indexOf
     * @param regex
     * @param input
     * @return
     */
    public static int indexOf(String regex, String input){
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(input);
        int index = -1;
        if(m.find())
            index = m.start();
        
        return index;
    }
    
    /**
     * 实现lastIndexOf
     * @param regex
     * @param input
     * @return
     */
    public static int lastIndexOf(String regex, String input){
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(input);
        int index = -1;
        while(m.find())
            index = m.start();
        
        return index;
    }
    
    /**
     * 替换全部匹配字符串 比如邮箱的转化等等；
     * @param regex
     * @param input
     * @param replacement
     * @return
     */
    public static String replaceAll(String regex, String input, String replacement){
        if (null == regex || regex.isEmpty()) return input;
        return input.replaceAll(regex, replacement);
    }
    /**
     * 替换第一个匹配的字符串
     * @param regex
     * @param input
     * @param replacement
     * @return
     */
    public static String replaceFirst(String regex, String input, String replacement){
    	if (null == regex || regex.isEmpty()) return input;
    	return input.replaceFirst(regex, replacement);
    }

    /**
     * 替换第N个匹配字符串
     * @param regex
     * @param input
     * @param replacement
     * @param n
     * @return
     */
    public static String replaceSome(String regex, String input, String replacement, int n){
        if (null == regex || regex.isEmpty()) return input;
        if (0 == n) return input.replaceFirst(regex, replacement);
        
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(input);
        int i = 0;
        StringBuffer buffer = new StringBuffer();
        while (i <= n && m.find()){
            if (i == n){
                m.appendReplacement(buffer, replacement);
                m.appendTail(buffer);
            }
            ++i;
        }
        if (0 == buffer.length())
            buffer.append(input);
        
        return buffer.toString();
    }
    
    /**
     * 获取全部匹配到的字符串的数量
     * @param regex
     * @param input
     * @return
     */
    public static int getSize(String regex, String input){
    	int size= 0;
    	Pattern r = Pattern.compile(regex);
    	Matcher m = r.matcher(input);
		while (m.find()) {
			size++;
		}
    	return size;
    }
    
    
    /**
     * 获取第一个匹配到的字符串
     * @param regex
     * @param input
     * @return
     */
    public static String getFirst(String regex, String input){
        String ret = null;
        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(input);
        if (m.find())
            ret = m.group();
        
        return ret;
    }
    
    /**
     * 获取全部匹配到的字符串
     * @param regex
     * @param input
     * @return
     */
    public static String[] getList(String regex, String input){
    	String[] list;
    	Pattern r = Pattern.compile(regex);
    	Matcher m = r.matcher(input);
    	int size = 0;
    	while (m.find()) {
			size++;
		}
		list = new String[size];
		m.reset();
		for (int i = 0; i < size; i++) {
			if (m.find())
				list[i] = m.group();
		}
    	return list;
    }
    
    /**
     * 拆分匹配的字符串
     * @param regex
     * @param input
     * @param limit
     * @return
     */
    public static String[] split(String regex, String input, int limit){
        if (null == input || input.isEmpty() 
                || null == regex || regex.isEmpty()) return new String[]{input};
        return input.split(regex, limit);
    }
    
    
}