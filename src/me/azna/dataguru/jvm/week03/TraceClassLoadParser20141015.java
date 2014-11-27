package me.azna.dataguru.jvm.week03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据Load Class Trace 的日志从jre class中裁剪出用到的class
 * 
 * @author yulei.ma
 *
 */
public class TraceClassLoadParser20141015 {
	/**
	 * args 1: log path args 2: jre lib path args 3: copy class path ex.:
	 * "D:/dev/workspace/dataguru/bin/class_loading_trace_20141014_2.log D:/temp/lib/rt/ D:/temp/cutjre/lib/"
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// log file
		String file = args[0];
		// file =
		// "D:/dev/workspace/dataguru/bin/class_loading_trace_20141014_2.log";
		// ..work directory
		String fromPath = args[1];
		// fromPath = "D:/temp/lib/rt/";
		String toPath = args[2];
		// toPath = "D:/temp/cutjre/lib/";
		// get list
		List<String> list = getLoadClassList(file);
		// output to file
		for (String claz : list) {
			copyFile(getFilePath(claz, fromPath), getFilePath(claz, toPath));
		}
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分析日志获得载入类的列表
	 * 
	 * @param file
	 * @return
	 */
	public static List<String> getLoadClassList(String file) {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;
		String regex = "\\[Loaded\\s{1}(.*?)\\s{1}from\\s{1}(.*?)\\]";
		try {
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(line);
				while (m.find()) {
					// System.out.println(m.group(1) + " " + m.group(2));
					list.add(m.group(1));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	private static String getFilePath(String className, String path) {
		return path + className.replaceAll(".", "/") + ".class";
	}

	private static void copyFile(String fromPath, String toPath) {
		// int bytesum = 0;
		int byteread = 0;
		try {
			File fromfile = new File(fromPath);
			if (fromfile.exists()) {
				InputStream inStream = new FileInputStream(fromPath);
				// create output path
				File file = new File(toPath);
				File parent = file.getParentFile();
				if (parent != null && !parent.exists()) {
					parent.mkdirs();
				}
				FileOutputStream fs = new FileOutputStream(toPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inStream.read(buffer)) != -1) {
					// bytesum += byteread;
					// System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}