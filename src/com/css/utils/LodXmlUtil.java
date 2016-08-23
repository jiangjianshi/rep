package com.css.utils;

import java.io.File;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;

public abstract class LodXmlUtil {
	
	
	public static Iterator<File>  getFolderFiles(String folderPath){
		
		String[] str = new  String[1];
		str[0]="xml";
		File file = new File(LodXmlUtil.class.getClassLoader().getResource(folderPath).getPath());
		
		return FileUtils.iterateFiles(file, str, true);

	}
	
}
