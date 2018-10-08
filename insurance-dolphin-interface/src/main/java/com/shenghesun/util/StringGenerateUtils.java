package com.shenghesun.util;

import java.util.Random;

/**
 * 生成随机字符串
 * 
 * @ClassName: StringGenerateUtils
 * @Description: TODO
 * @author: yangzp
 * @date: 2018年9月30日 下午5:07:51
 */
public class StringGenerateUtils {
	public static final String LETTERCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	/**
	 * 返回一个定长的随机纯字母字符串(只包含大小写字母) 
	 * @Title: generateUpperLowerString 
	 * @Description: TODO 
	 * @param length  随机字符串长度
	 * @return  String  随机字符串
	 * @author yangzp
	 * @date 2018年9月30日下午5:19:02
	 **/ 
	public static String generateUpperString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
		}
		return sb.toString();
	}
	/**
	 * 生成 n 个长度的由随机数字组成的字符串
	 * @Title: randomNum 
	 * @Description: TODO 
	 * @param n
	 * @return  String 
	 * @author yangzp
	 * @date 2018年9月30日下午5:22:22
	 **/ 
	public static String randomNum(int n){
		if(n < 1){
			return "";
		}
		Random r = new Random();// 定义随机类
		StringBuilder b = new StringBuilder();
		b.append(r.nextInt(10));
		if(n > 1){
			for(int i = 0 ; i < n - 1 ; i ++){
				b.append(r.nextInt(10));// 返回[0,10)集合中的整数，注意不包括10
			}
		}
		return b.toString();
	}
	
	/**
	 * 生成 ApplyId
	 * @Title: generateId 
	 * @Description: TODO 
	 * @return  String 
	 * @author yangzp
	 * @date 2018年9月30日下午5:26:04
	 **/ 
	public static String generateId() {
		return generateUpperString(3) + randomNum(11);
	}

	public static void main(String[] args) {
		System.out.println(generateUpperString(3));
		System.out.println(generateId());
	}
}
