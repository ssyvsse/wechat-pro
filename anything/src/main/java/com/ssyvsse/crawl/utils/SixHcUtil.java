package com.ssyvsse.crawl.utils;

import java.util.ArrayList;
import java.util.List;

/**
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
		String[] years = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
		return years[(year - start) % years.length];
	}

	/***
	 * 通过生肖code获取生肖名称<br>
	 * 
	 * @param code
	 * @return
	 */
	public static String getAnimalNameByCode(Integer code) {
		String[] names = new String[] { "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗", "猪" };
		return names[code];
	}

	/***
	 * 通过年份获取生肖code<br>
	 * 
	 * @param year
	 * @return
	 */
	public static Integer getAnimalCodeByYear(Integer year) {
		if (year == null || year < 1900) {
			return -1;
		}
		Integer start = 1900;
		Integer[] years = new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
		return years[(year - start) % years.length];
	}

	/**
	 * 根据开奖号码查询所属生肖
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer getAnimalByOpenCode(Integer openCode) {
		Integer[] ani = new Integer[49];
		ani[11]=ani[23]=ani[35]=ani[47]=11;//猪
		ani[10]=ani[22]=ani[34]=ani[46]=10;//狗
		ani[9]=ani[21]=ani[33]=ani[45]=0;//鼠
		ani[8]=ani[20]=ani[32]=ani[44]=1;//牛
		ani[7]=ani[19]=ani[31]=ani[43]=2;//虎
		ani[6]=ani[18]=ani[30]=ani[42]=3;//兔
		ani[5]=ani[17]=ani[29]=ani[41]=4;//龙
		ani[4]=ani[16]=ani[28]=ani[40]=5;//蛇
		ani[3]=ani[15]=ani[27]=ani[39]=6;//马
		ani[2]=ani[14]=ani[26]=ani[38]=7;//羊
		ani[1]=ani[13]=ani[25]=ani[37]=8;//猴
		ani[0]=ani[12]=ani[24]=ani[36]=ani[48]=9;//鸡
		return ani[openCode-1];
	}
	
	public static void main(String[] args) {
		System.out.println(getAnimalNameByCode(getAnimalByOpenCode(47)));
	}

	/***
	 * 根据年龄查询所属的生肖code
	 * 
	 * @param age
	 * @return
	 */
	public static Integer getAnimalCodeByAgeAndYear(Integer age, Integer year) {
		if (age == null || year == null || age < 0 || age > 49 || year < 1900) {
			return -1;
		}

		// 首先需要获取本年的生肖code
		Integer thisYearCode = getAnimalCodeByYear(year);

		int mod12 = age % 12;
		if (mod12 == 0) {
			mod12 = 12;
		}

		// 生成生肖对应数组
		Integer[] code = new Integer[12];
		int index = 0; // 记录下标
		for (int i = thisYearCode; i >= 0; i--) {
			code[index] = i;
			index++;
		}
		for (int i = 11; i > thisYearCode; i--) {
			code[index] = i;
			index++;
		}
		// 从生肖数组中取出生肖码
		Integer ret = code[mod12 - 1];
		return ret;
	}

	/****
	 * 根据开奖号码获取开奖号码对应的波色<br>
	 * 1 红 2绿 3蓝<br>
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer openColor(Integer openCode) {
		if (openCode < 0 || openCode > 49) {
			return -1;
		}
		// 构造波色数组
		Integer[] attrs = new Integer[] { 1, 1, 3, 3, 2, 2, 1, 1, 3, 3, 2, 1, 1, 3, 3, 2, 2, 1, 1, 3, 2, 2, 1, 1, 3, 3,
				2, 2, 1, 1, 3, 2, 2, 1, 1, 3, 3, 2, 2, 1, 3, 3, 2, 2, 1, 1, 3, 3, 2 };
		return attrs[openCode];
	}

	/***
	 * 家禽野兽 对应12生肖 家禽 1 野兽 2<br>
	 * 家禽 ： 牛、 马、 羊、 鸡、 狗、 猪<br>
	 * 野兽 ： 鼠、 虎、 兔、 龙、 蛇、 猴<br>
	 * 子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马 、未羊 、申猴、酉鸡 、戌狗 、亥猪<br>
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer poultryOrBeast(Integer openCode) {
		if (openCode < 0 || openCode > 49) {
			return -1;
		}
		Integer[] attrs = new Integer[] { // 构造家禽野兽的数组
				2, 1, 2, 2, 2, 2, 1, 1, 2, 1, 1, 1 };
		return attrs[openCode];
	}

	/***
	 * 男女生肖 对应12生肖 男1 女2<br>
	 * 男肖：鼠、 牛、 虎、 龙、 马、 猴、 狗 <br>
	 * 女肖：兔、 蛇、 羊、 鸡、 猪<br>
	 * 子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马 、未羊 、申猴、酉鸡 、戌狗 、亥猪<br>
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer boyOrGirl(Integer openCode) {
		if (openCode < 0 || openCode > 49) {
			return -1;
		}
		Integer[] attrs = new Integer[] { // 构造男女生肖的数组
				1, 1, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		return attrs[openCode];
	}

	/***
	 * 天地生肖 1天 2地<br>
	 * 天肖 ：牛、 兔、龙、 马、 猴、 猪<br>
	 * 地肖 ：鼠、 虎、羊、 蛇、 鸡、 狗<br>
	 * 子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马 、未羊 、申猴、酉鸡 、戌狗 、亥猪<br>
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer heavenOrGround(Integer openCode) {
		if (openCode < 0 || openCode > 49) {
			return -1;
		}
		Integer[] attrs = new Integer[] { // 构造天地生肖的数组
				2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1 };
		return attrs[openCode];
	}

	/***
	 * 四季生肖 春1、夏2、秋3、冬4<br>
	 * 春肖 ： 虎、 兔、 龙<br>
	 * 夏肖 ： 蛇、 马、 羊<br>
	 * 秋肖 ： 猴、 鸡 、 狗<br>
	 * 冬肖 ： 鼠、 牛、 猪<br>
	 * 子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马 、未羊 、申猴、酉鸡 、戌狗 、亥猪<br>
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer fourSeasons(Integer openCode) {
		if (openCode < 0 || openCode > 49) {
			return -1;
		}
		Integer[] attrs = new Integer[] { // 构造四季生肖的数组
				4, 4, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4 };
		return attrs[openCode];
	}

	/**
	 * 五行: 1 金 2 木 3 水 4 火 5 土
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer fiveElement(Integer openCode) {
		if (openCode < 0 || openCode > 49) {
			return -1;
		}
		Integer[] attrs = new Integer[] { // 构造五行的数组
				5, 1, 1, 5, 4, 4, 5, 2, 2, 5, 3, 3 };
		return attrs[openCode % 12];
	}


	/***
	 * 琴1 棋2 书3 画4<br>
	 * 琴肖 ： 兔、 蛇、 鸡<br>
	 * 棋肖 ： 鼠、 牛、 狗<br>
	 * 书肖 ： 虎、 龙、 马<br>
	 * 画肖 ： 羊、 猴、 猪<br>
	 * 子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马 、未羊 、申猴、酉鸡 、戌狗 、亥猪<br>
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer poetryAndPainting(Integer openCode) {
		if (openCode < 0 || openCode > 49) {
			return -1;
		}
		Integer[] attrs = new Integer[] { // 构造琴棋书画的数组
				2, 2, 3, 1, 3, 1, 3, 4, 4, 1, 2, 4 };
		return attrs[openCode];
	}

	/***
	 * 三色生肖 红1、绿2、蓝3<br>
	 * 红肖 ： 鼠、 兔、 马、 鸡<br>
	 * 绿肖 ： 牛、 龙、 羊、 狗<br>
	 * 蓝肖 ： 虎、 蛇、 猴、 猪<br>
	 * 子鼠、丑牛、寅虎、卯兔、辰龙、巳蛇、午马 、未羊 、申猴、酉鸡 、戌狗 、亥猪<br>
	 * 
	 * @param openCode
	 * @return
	 */
	public static Integer threeColor(Integer openCode) {
		if (openCode < 0 || openCode > 49) {
			return -1;
		}
		Integer[] attrs = new Integer[] { // 构造三色生肖的数组
				1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3 };
		return attrs[openCode];
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

	/****
	 * 将开奖号码为String的转为int<br>
	 * 
	 * @param data
	 *            开奖号码
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
