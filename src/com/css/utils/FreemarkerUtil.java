package com.css.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.css.utils.MyFileUtils;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * Freemarker 服务类
 * @author zzg
 */
public class FreemarkerUtil {

	/** 存放freemarker模板的文件夹路径，依赖注入 */
	private String templateDir;
	private Configuration cfg;
	private static final Log logger = LogFactory.getLog(FreemarkerUtil.class);

	public FreemarkerUtil(){
		init(null);
	}
	public FreemarkerUtil(String tempDir){
		init(tempDir);
	}
	
	/**
	 * 实例化Freemarker Configuration
	 */
	public void init(String tempDir) {
		try {
			String separator = System.getProperty("file.separator");
			if(tempDir!=null){
				templateDir = tempDir;
			}else{
				templateDir = this.getClass().getResource("").getPath()+separator+"ftl"+separator;
			}
			logger.info("开始实例化Freemarker Configuration，templateDir：" + templateDir);
			cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templateDir));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			cfg.setDefaultEncoding("UTF-8");  
		} catch (Exception e) {
			logger.error("实例化Freemarker Configuration时出现错误", e);
		}
	}

	/**
	 * 使用模板文件生成配置文件
	 * @param rootMap freemarker数据模型
	 * @param destFile 目标文件
	 * @param ftlFile freemarker模板文件
	 */
	public void write(Object rootMap, String destFile, String ftlFile) {
		try {
			logger.info("开始使用【" + ftlFile + "】模板文件生成【 " + destFile + "】");
			Template tpl = cfg.getTemplate(ftlFile);
			tpl.setEncoding("UTF-8");  
			File f = new File(destFile);
			if(!f.exists()){
				MyFileUtils.createFile(f);
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(  
                    new FileOutputStream(destFile), "UTF-8"));  
			tpl.process(rootMap, out);
			out.flush();
			out.close();
			logger.info("使用【" + ftlFile + "】模板文件生成【 " + destFile + "】成功");
		} catch (Exception e) {
			logger.error("使用【" + ftlFile + "】模板文件生成【 " + destFile + "】时出错错误", e);
		}
	}
	
	public void replaceFileContent(File file,String replaceStr,String afterStr){
		String s;
		try {
			s = MyFileUtils.readTxtFile(file);
			s = s.replaceAll(replaceStr, afterStr);
			MyFileUtils.writeStrToFile(file, s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}
}
