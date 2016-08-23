package com.css.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件工具类
 * 
 * @author syw
 * 
 */
public class MyFileUtils {

	public static File createFile(File file) throws IOException {
		if (!file.exists()) {
			makeDir(file.getParentFile());
		}
		file.createNewFile();
		return file;
	}

	public static void makeDir(File dir) {
		if (!dir.getParentFile().exists()) {
			makeDir(dir.getParentFile());
		}
		dir.mkdir();
	}

	public static void writeStrToFile(File file, String content)
			throws IOException {
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(content);
		bw.close();
	}

	public static String readTxtFile(File file) throws IOException {
		if (file.isFile() && file.exists()) { // 判断文件是否存在
		 InputStreamReader read = new InputStreamReader(new FileInputStream(
		 file));
//		 InputStreamReader read = new InputStreamReader(new FileInputStream(
//				 file), "UTF-8");// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			StringBuffer sb = new StringBuffer();
			while ((lineTxt = bufferedReader.readLine()) != null) {
				sb.append(lineTxt).append("\r\n");
			}
			read.close();
			return sb.toString();
		} else {
			System.out.println("找不到指定的文件");
			return "-1";
		}
	}
	public static String getWebRootDir(String dir){
		String separator = System.getProperty("file.separator");
		String classPath = MyFileUtils.class.getClassLoader().getResource("").getPath();
		File classPathDir = new File(classPath);
		String webRootPath = classPathDir.getParent();
		return webRootPath.replace("WEB-INF", "")+dir.replace("/", separator);
	}

	public static void main(String[] args) throws IOException {
	}

}
