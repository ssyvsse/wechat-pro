package com.structure.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author llb
 *
 * @Date 2017年12月29日 下午10:20:55 
 */
public class TestMain {
	
	public static void main(String[] args) throws IOException {
		System.out.println(getDouble());
	}
	
	public static String getString() throws IOException{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String s = br.readLine();
		return s;
	}
	
	public static char getChar() throws IOException{
		String s = getString();
		return s.charAt(0);
	}
	
	public static int getInt() throws IOException{
		String s= getString();
		return Integer.parseInt(s);
	}
	
	public static double getDouble() throws IOException{
		String s = getString();
		Double d = Double.valueOf(s);
		return d.doubleValue();
	}
}
