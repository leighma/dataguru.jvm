package me.azna.dataguru.jvm.week03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 根据Load Class Trace 的日志从jre class目录中裁剪出用到的class
 * 
 * @author yulei.ma
 *
 */
public class TraceClassLoadParser20141016 {
	/**
	 * args 1: log path args 2: jre lib path args 3: copy class path ex.:
	 * "D:/temp/cutjre/TraceClassLoad1/TraceClassLoad1_loadclass_20141015_1.log D:/temp/lib/rt/  D:/temp/cutjre/TraceClassLoad1/lib/"
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// log file
		String file = args[0];
		// ..work directory
		String fromPath = args[1];
		String toPath = args[2];
		try {
			// get list
			List<String> list = getLoadClassList(file);
			// output to file
			for (String claz : list) {
				copyFile(getFilePath(claz, fromPath), getFilePath(claz, toPath));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 分析日志获得载入类的列表
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static List<String> getLoadClassList(String file) throws IOException {
		List<String> list = new ArrayList<String>();
		BufferedReader br = null;
		String line = null;
		String regex = "\\[Loaded\\s{1}(.*?)\\s{1}from\\s{1}(.*?)\\]";
		br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(line);
			while (m.find()) {
				list.add(m.group(1));
			}
		}
		br.close();
		return list;
	}

	/**
	 * 由输入目录和类名（含包名）获得class文件地址
	 * 
	 * @param className
	 * @param path
	 * @return
	 */
	private static String getFilePath(String className, String path) {
		return path + className.replaceAll("\\.", "/") + ".class";
	}

	/**
	 * 拷贝文件
	 * 
	 * @param fromPath
	 * @param toPath
	 * @throws IOException
	 */
	private static void copyFile(String fromPath, String toPath)
			throws IOException {
		int byteread = 0;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		inputStream = new FileInputStream(fromPath);
		// create output path
		File file = new File(toPath);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
		outputStream = new FileOutputStream(toPath);
		byte[] buffer = new byte[1024];
		while ((byteread = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, byteread);
		}
		inputStream.close();
		outputStream.close();
	}
}
