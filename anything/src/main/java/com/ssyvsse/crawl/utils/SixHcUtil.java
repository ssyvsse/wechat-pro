package com.ssyvsse.crawl.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

	/**
	 * 根据年份算出所属的生肖
	 * 
	 * @param year
	 * @return
	 */
	public static String getYear(Integer year) {
		if (year < 1900) {
			return "未知";
		}
		Integer start = 1900;
		String[] animals = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
		return animals[(year - start) % animals.length];
	}

	/**
	 * 根据开奖号码计算生肖
	 * 
	 * @param openCode
	 * @param year
	 * @return
	 */
	public static String getAnimalByOpenCode(Integer openCode, Integer year) {
		if (year == null) {
			return null;
		}
		// 1、获取当年的生肖
		String animal = getYear(year);
		if ("未知".equals(animal)) {
			return null;
		}

		if (openCode <= 0 || openCode > 49) {
			return null;
		}
		List<String> fullLi = getAnimalListByFirstAnimalPerYear(year);
		return fullLi.get(openCode % 12);
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
		Integer[] attrs = new Integer[] { 1, 1, 3, 3, 2, 2, 1, 1, 3, 3, 2, 1, 1, 3, 3, 2, 2, 1, 1, 3, 2, 2, 1, 1, 3, 3,
				2, 2, 1, 1, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 3, 3, 2, 2, 1, 1, 3, 3, 2 };
		return attrs[openCode - 1];
	}

	/**
	 * 根据开奖号码获取对应的五行元素<br>
	 * 1、金 2、木 3、水 4、火 5、土
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer fiveElement(Integer openCode) {
		if (openCode <= 0 || openCode > 49) {
			return -1;
		}
		// 构造五行数组
		Integer[] attrs = new Integer[] { 4, 4, 1, 1, 3, 3, 2, 2, 4, 4, 5, 5, 3, 3, 2, 2, 1, 1, 5, 5, 3, 3, 4, 4, 1, 1,
				5, 5, 2, 2, 4, 4, 1, 1, 3, 3, 2, 2, 4, 4, 5, 5, 3, 3, 2, 2, 1, 1, 5 };
		return attrs[openCode - 1];
	}

	/**
	 * 根据年份求出当年生肖数组
	 * 
	 * @param year
	 * @return
	 */
	public static List<String> getAnimalListByFirstAnimalPerYear(Integer year) {
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
	public static List<Integer> getPoutryOrBeastList(List<String> animals) {
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

	/**
	 * 根据开奖号码获取家禽或野兽
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer getPoultryOrBeastListByOpenCode(Integer openCode, Integer year) {
		List<Integer> poultryOrBeastList = getPoutryOrBeastList(getAnimalListByFirstAnimalPerYear(year));
		return poultryOrBeastList.get(openCode % 12);
	}

	/**
	 * 根据当年生肖数组获取男女数组<br>
	 * 1、男 2、女
	 * 
	 * @param animals
	 * @return
	 */
	public static List<Integer> getBoyOrGirl(List<String> animals) {
		List<Integer> boyOrGirlList = new ArrayList<Integer>();
		for (String string : animals) {
			switch (string) {
			case "鼠":
			case "牛":
			case "虎":
			case "龙":
			case "马":
			case "狗":
			case "猴":
				boyOrGirlList.add(1);
				continue;
			case "兔":
			case "蛇":
			case "羊":
			case "鸡":
			case "猪":
				boyOrGirlList.add(2);
				continue;
			}
		}
		return boyOrGirlList;
	}

	/**
	 * 根据开奖号码获取男或女
	 * 
	 * @param openCode
	 * @param year
	 * @return
	 */
	public static Integer getBoyOrGirlByOpenCode(Integer openCode, Integer year) {
		List<Integer> boyOrGirlList = getBoyOrGirl(getAnimalListByFirstAnimalPerYear(year));
		return boyOrGirlList.get(openCode % 12);
	}

	/**
	 * 根据当年生肖数组获取天地数组
	 * 
	 * @param animals
	 * @return
	 */
	public static List<Integer> getHeavenOrGroundList(List<String> animals) {
		List<Integer> heavenOrGroundList = new ArrayList<Integer>();
		for (String string : animals) {
			switch (string) {
			case "牛":
			case "兔":
			case "龙":
			case "马":
			case "猴":
			case "猪":
				heavenOrGroundList.add(1);
				continue;
			case "鼠":
			case "虎":
			case "羊":
			case "蛇":
			case "鸡":
			case "狗":
				heavenOrGroundList.add(2);
				continue;
			}
		}
		return heavenOrGroundList;
	}

	/**
	 * 根据开奖号码获取天地
	 * 
	 * @param openCode
	 * @param year
	 * @return
	 */
	public static Integer getHeavenOrGroundByOpenCode(Integer openCode, Integer year) {
		List<Integer> heavenOrGroundList = getHeavenOrGroundList(getAnimalListByFirstAnimalPerYear(year));
		return heavenOrGroundList.get(openCode % 12);
	}

	/**
	 * 根据当年生肖数组获取四季
	 * 
	 * @param animals
	 * @return
	 */
	public static List<Integer> getFourSeasonList(List<String> animals) {
		List<Integer> fourSeason = new ArrayList<Integer>();
		for (String string : animals) {
			switch (string) {
			case "虎":
			case "兔":
			case "龙":
				fourSeason.add(1);
				continue;
			case "蛇":
			case "马":
			case "羊":
				fourSeason.add(2);
				continue;
			case "猴":
			case "鸡":
			case "狗":
				fourSeason.add(3);
				continue;
			case "鼠":
			case "牛":
			case "猪":
				fourSeason.add(4);
				continue;
			}
		}
		return fourSeason;
	}

	/**
	 * 根据开奖号码获取四季
	 * 
	 * @param openCode
	 * @param year
	 * @return
	 */
	public static Integer getFourSeasonByOpenCode(Integer openCode, Integer year) {
		List<Integer> fourSeason = getFourSeasonList(getAnimalListByFirstAnimalPerYear(year));
		return fourSeason.get(openCode % 12);
	}

	/**
	 * 根据当年生肖数组获取琴棋书画
	 * 
	 * @param openCode
	 * @return
	 */
	public static List<Integer> getPoetryAndPaintingList(List<String> animals) {
		List<Integer> poetryAndPainting = new ArrayList<Integer>();
		for (String string : animals) {
			switch (string) {
			case "兔":
			case "蛇":
			case "鸡":
				poetryAndPainting.add(1);
				continue;
			case "鼠":
			case "牛":
			case "狗":
				poetryAndPainting.add(2);
				continue;
			case "虎":
			case "龙":
			case "马":
				poetryAndPainting.add(3);
				continue;
			case "羊":
			case "猴":
			case "猪":
				poetryAndPainting.add(4);
				continue;
			}
		}
		return poetryAndPainting;
	}

	/**
	 * 根据开奖号码获取琴棋书画
	 * 
	 * @param openCode
	 * @param year
	 * @return
	 */
	public static Integer getPoetryAndPainting(Integer openCode, Integer year) {
		List<Integer> poetryAndPainting = getPoetryAndPaintingList(getAnimalListByFirstAnimalPerYear(year));
		return poetryAndPainting.get(openCode % 12);
	}

	/**
	 * 根据当年生肖数组获取三色生肖
	 * 
	 * @param animals
	 * @return
	 */
	public static List<Integer> getThreeColorList(List<String> animals) {
		List<Integer> threeColorList = new ArrayList<Integer>();
		for (String string : animals) {
			switch (string) {
			case "鼠":
			case "兔":
			case "马":
			case "鸡":
				threeColorList.add(1);
				continue;
			case "牛":
			case "龙":
			case "羊":
			case "狗":
				threeColorList.add(2);
				continue;
			case "虎":
			case "蛇":
			case "猴":
			case "猪":
				threeColorList.add(3);
				continue;
			}
		}
		return threeColorList;
	}

	/**
	 * 根据开奖号码获取三色生肖
	 * 
	 * @param openCode
	 * @param year
	 * @return
	 */
	public static Integer getThreeColor(Integer openCode, Integer year) {
		List<Integer> threeColorList = getThreeColorList(getAnimalListByFirstAnimalPerYear(year));
		return threeColorList.get(openCode % 12);
	}

	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		int i = 0;
		System.out.println("请输入开奖号码:");
		while ((i = sc.nextInt()) <= 49) {
			System.out.println("号码：" + i);
			System.out.println("生肖：" + getAnimalByOpenCode(i, 2017) + ":");
			System.out.println("家禽：" + (getPoultryOrBeastListByOpenCode(i, 2017) == 1 ? "家禽" : "野兽"));
			System.out.println("男女：" + (getBoyOrGirlByOpenCode(i, 2017) == 1 ? "男" : "女"));
			System.out.println("天地：" + (getHeavenOrGroundByOpenCode(i, 2017) == 1 ? "天" : "地"));
			Integer fourSeason = getFourSeasonByOpenCode(i, 2017);
			switch (fourSeason) {
			case 1:
				System.out.println("四季：春");
				break;
			case 2:
				System.out.println("四季：夏");
				break;
			case 3:
				System.out.println("四季：秋");
				break;
			case 4:
				System.out.println("四季：冬");
				break;
			}
			Integer poetryAndPainting = getPoetryAndPainting(i, 2017);
			switch (poetryAndPainting) {
			case 1:
				System.out.println("琴棋书画：琴");
				break;
			case 2:
				System.out.println("琴棋书画：棋");
				break;
			case 3:
				System.out.println("琴棋书画：书");
				break;
			case 4:
				System.out.println("琴棋书画：画");
				break;
			}
			Integer threeColor = getThreeColor(i, 2017);
			switch (threeColor) {
			case 1:
				System.out.println("三色生肖：红");
				break;
			case 2:
				System.out.println("三色生肖：绿");
				break;
			case 3:
				System.out.println("三色生肖：蓝");
				break;
			}
			System.out.println("请输入开奖号码:");
		}
		sc.close();
		System.exit(0);
	}

	/***
	 * 求出号码的总和
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer sum(List<String> openCode) {
		List<Integer> parsedIntData = parseInt(openCode); // 转为整形数据数组
		int ret = 0;
		for (Integer data : parsedIntData) {
			ret += data;
		}
		return ret;
	}

	/**
	 * List<string>转化为List<Integer>
	 * 
	 * @param data
	 * @return
	 */
	public static List<Integer> parseInt(List<String> data) {
		List<Integer> ret = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			ret.add(Integer.parseInt(data.get(i)));
		}
		return ret;
	}
}
