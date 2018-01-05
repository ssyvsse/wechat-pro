package com.ssyvsse.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author llb
 *
 * @Date 2018年1月3日
 */
public class SearchUtil {

	private final static String path = "C:/Users/2349/Desktop/log";
	
	private final static Set<String> pcSet = new TreeSet<String>();
	
	private final static Set<String> noSet = new TreeSet<String>();
	
	private final static Set<String> mSet = new TreeSet<String>();
	
	public static void main(String[] args) throws IOException {
		File file = new File(path);
		readFiles(file);
		int size = 1;
		for (String string : pcSet) {
			System.out.println(size+":"+string);
			size ++ ;
		}
		for (String string : mSet) {
			System.out.println(size+":"+string);
			
		}
		for (String string : noSet) {
			System.out.println(size+":"+string);
			size ++ ;
		}
		output();
	}
	
	
	public static void readFiles(File file) {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				readFiles(files[i]);
			}
		}else {
			if(file.getAbsolutePath().endsWith(".log")) {
				FileReader fr = null;
				BufferedReader br = null;
				try {
					fr = new FileReader(file);
					br = new BufferedReader(fr);
					String line = null;
					while((line=br.readLine())!=null) {
						if(line.indexOf(" 404 ")>=0) {
							if(line.indexOf("m.2349m")>=0) {
								line = line.substring(line.indexOf("+0800]")+8);
								
								mSet.add(line);
							}else if(line.indexOf("2349m")>=0) {
								line = line.substring(line.indexOf("+0800]")+8);
								
								pcSet.add(line);
							}else {
								line = line.substring(line.indexOf("+0800]")+8);
								
								noSet.add(line);
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						fr.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public static void output() {
		File file = new File("C:/Users/2349/Desktop/record2");
		if(!file.exists()) {
			file.mkdirs();
		}
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			File file1 = new File("C:/Users/2349/Desktop/record2/pcRecord.txt");
			fw = new FileWriter(file1);
			bw = new BufferedWriter(fw);
			for (String string : pcSet) {
				bw.write(string+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
			}
			try {
				fw.close();
			} catch (IOException e) {
			}
		}
		try {
			File file1 = new File("C:/Users/2349/Desktop/record2/mRecord.txt");
			fw = new FileWriter(file1);
			bw = new BufferedWriter(fw);
			for (String string : mSet) {
				bw.write(string+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
			}
			try {
				fw.close();
			} catch (IOException e) {
			}
		}
		try {
			File file1 = new File("C:/Users/2349/Desktop/record2/noRecord.txt");
			fw = new FileWriter(file1);
			bw = new BufferedWriter(fw);
			for (String string : noSet) {
				bw.write(string+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
			} catch (IOException e) {
			}
			try {
				fw.close();
			} catch (IOException e) {
			}
		}
	}
}
