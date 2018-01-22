package com.ssyvsse.crawl.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 六合彩的工具类
 * 
 * @author llb
 *
 * @Date 2018年1月18日 下午10:27:21
 */
public class SixHcUtil {

	/**
	 * 通过年份获取本年的主生肖<br>
	 * 子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马 、未羊 、申猴、酉鸡 、戌狗 、亥猪<br>
	 * 
	 * @param year
	 * @return
	 */
	public static String getAnimalNameByYear(Integer year) {
		if (year == null || year < 1900) {
			return "未知";
		}
		Integer start = 1900;
		String[] animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
		return animals[(year - start) % animals.length];
	}

	/****
	 * 根据开奖号码获取开奖号码对应的波色 1 红 2绿 3蓝
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer openColor(Integer openCode) {
		if (openCode <= 0 || openCode > 49) {
			return -1;
		}
		// 构造波色数组
		Integer[] attrs = new Integer[] 
				{1,1,3,3,2,2,1,1,3,3,2,1,
				 1,3,3,2,2,1,1,3,2,2,1,1,
				 3,3,2,2,1,1,3,2,2,1,1,3,
				 3,2,2,1,3,3,2,2,1,1,3,3,
				 				   2};
		return attrs[openCode-1];
	}
	
	/**
	 * 根据开奖号码获取对应的五行元素<br>
	 * 1、金 2、木 3、水 4、火 5、土
	 * @param openCode
	 * @return
	 */
	public static Integer fiveElement(Integer openCode) {
		if (openCode <= 0 || openCode > 49) {
			return -1;
		}
		// 构造五行数组
		Integer[] attrs = new Integer[] 
			{4,4,1,1,3,3,2,2,4,4,5,5,
			 3,3,2,2,1,1,5,5,3,3,4,4,
			 1,1,5,5,2,2,4,4,1,1,3,3,
			 2,2,4,4,5,5,3,3,2,2,1,1,
			 				   5};
		return attrs[openCode - 1];
	}
	

	/**
	 * 根据年份求出当年生肖数组
	 * 
	 * @param year
	 * @return
	 */
	public static List<String> getAnimalListByYear(Integer year) {
		// 求出当年的生肖
		String animal = getAnimalNameByYear(year);
		if ("未知".equals(animal)) {
			return null;
		}
		String[] animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
		int len = animals.length;
		int temp = 0;
		for (int i = 0; i < len; i++) {
			if (animal.equals(animals[i])) {
				temp = i;// 求出第一个生肖所在位置
				break;
			}
		}
		String[] before = new String[temp + 2];
		String[] after = new String[len - temp - 2];
		for (int i = 0; i < before.length; i++) {
			before[i] = animals[i];
		}
		for (int i = 0; i < after.length; i++) {
			after[i] = animals[len - after.length + i];
		}
		List<String> beforeList = Arrays.asList(before);
		List<String> afterList = Arrays.asList(after);
		Collections.reverse(beforeList);
		Collections.reverse(afterList);
		List<String> animalList = new ArrayList<String>();
		for (String string : beforeList) {
			animalList.add(string);
		}
		for (String string : afterList) {
			animalList.add(string);
		}
		return animalList;
	}

	/**
	 * 根据当年生肖数组获取家禽或野兽数组<br>
	 * 家禽野兽 对应12生肖 家禽 1 野兽 2<br>
	 * 家禽 ： 牛、 马、 羊、 鸡、 狗、 猪<br>
	 * 野兽 ： 鼠、 虎、 兔、 龙、 蛇、 猴<br>
	 * 
	 * @param animals
	 * @return
	 */
	public List<Integer> getPoutryOrBeast(List<String> animals) {
		List<Integer> poutryOrBeast = new ArrayList<Integer>();
		for (String animal : animals) {
			switch (animal) {
			case "牛":
			case "马":
			case "羊":
			case "鸡":
			case "狗":
			case "猪":
				poutryOrBeast.add(1);
				continue;
			case "鼠":
			case "虎":
			case "兔":
			case "龙":
			case "蛇":
			case "猴":
				poutryOrBeast.add(2);
				continue;
			}
		}
		return poutryOrBeast;
	}

	public static void main(String[] args) {
		
	}

}
