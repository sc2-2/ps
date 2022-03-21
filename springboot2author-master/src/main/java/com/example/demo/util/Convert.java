package com.example.demo.util;

import java.util.Arrays;
import java.util.List;

/**
 * 类型转换器
 * 
 * 
 */
public class Convert
{
	/**
     * 转换为List<String>数组<br>
     * 
     * @param split 被转换的值
     * @return 结果
     */
    public static List<String> toListStrArray(String str){
    	String[] stringArray= toStrArray(str);
    	List<String> stringB = Arrays.asList(stringArray);
    	return stringB;
    }
    /**
     * 转换为String数组<br>
     * 
     * @param split 被转换的值
     * @return 结果
     */
    public static String[] toStrArray(String str)
    {
        return toStrArray(",", str);
    }
    /**
     * 转换为String数组<br>
     * 
     * @param split 分隔符
     * @param split 被转换的值
     * @return 结果
     */
    public static String[] toStrArray(String split, String str)
    {
        return str.split(split);
    }

}
