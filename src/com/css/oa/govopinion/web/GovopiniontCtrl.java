package com.css.oa.govopinion.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.css.oa.govopinion.service.GovopinionService;
import com.css.sysbase.service.CommonService;
import com.css.utils.MyFileUtils;
import com.css.utils.PropertyConfigurerSon;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;
import com.css.utils.web.DateUtils;

@Controller
@RequestMapping("/oa/govopinion")
public class GovopiniontCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "oa/govopinion/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "govopinionService")
	private GovopinionService govopinionService;
	
	/**
	 * 跳转 页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/{fileName}")
	public ModelAndView vistPage(@PathVariable String fileName,HttpServletRequest request) throws Exception{
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		paramMap.put("basePath", request.getContextPath());
		return getModelAndView(subPath, fileName, paramMap);
	}
	
	/**
	 * 上传政府审批意见-页面
	 * @param res
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/setGovOpinion")
	public ModelAndView setGovOpinion(HttpServletResponse res,HttpServletRequest request) throws Exception {
		Map paramMap = CtrlUtils.getReqParametersMap_Utf8(request);
		String proId = (String) paramMap.get("proId");
		Map dataMap = commonService.loadSingleData("fn_govopinion_sql", "getGovopinionByProId",paramMap);
		paramMap.put("govopinionInfor",dataMap);
		
		paramMap.put("id", proId);
		Map proInfor = commonService.loadSingleData("build_pro-sql", "getObjInfor",paramMap);
		paramMap.put("proInfor",proInfor);
		
		
		return getModelAndView(subPath, "setGovOpinion", paramMap);
	}
	
	
	
	/**
	 * 处理上传
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/setGovOpinionSave")
	public String setGovOpinionSave(HttpServletRequest request, @RequestParam("opinionFile") MultipartFile opinionFile) throws Exception{
		String proId = request.getParameter("proId");
		String saveRootPath = PropertyConfigurerSon.getContextProperty("fileupload.path");
		saveRootPath = PropertyConfigurerSon.replaceFileSeparator(saveRootPath);
		String govopinionPath = PropertyConfigurerSon.getContextProperty("govopinion.path");
		govopinionPath = PropertyConfigurerSon.replaceFileSeparator(govopinionPath);
		String savePath = saveRootPath+govopinionPath;
		
    	String fileName = opinionFile.getOriginalFilename();
    	String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
    	
    	 //构建新的文件名，用于服务端存储文件
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyyMMddHHmmssSSS");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        String filePath = savePath + newFileName;  
        opinionFile.transferTo(MyFileUtils.createFile(new File(filePath))); 
        
        String curLoginUserRealName = (String) CtrlUtils.getSessionUserInforMap(request).get("realname");
        
        
        Map paramMap = new HashMap();
        paramMap.put("proId", proId);
		Map dataMap = commonService.loadSingleData("fn_govopinion_sql", "getGovopinionByProId",paramMap);
        if(dataMap==null){
        	Map<String,Object> addParams = new HashMap<String,Object>();
            addParams.put("opinId",String.valueOf(UUID.randomUUID()));
            addParams.put("proId",proId);
    		addParams.put("opinFilePath", govopinionPath+newFileName);
    		addParams.put("opinFileName",fileName);
    		addParams.put("updateTime",DateUtils.getDateNow(null));
    		addParams.put("updateUser",curLoginUserRealName);
    		commonService.saveOrUpdate("fn_govopinion_sql", "addGovopinion", addParams);
        }else{
        	String delFilePath  = saveRootPath + (String) dataMap.get("opin_file_path");
        	new File(delFilePath).delete();
        	Map<String,Object> updateParams = new HashMap<String,Object>();
        	updateParams.put("proId",proId);
        	updateParams.put("opinFilePath", govopinionPath+newFileName);
        	updateParams.put("opinFileName",fileName);
        	updateParams.put("updateTime",DateUtils.getDateNow(null));
        	updateParams.put("updateUser",curLoginUserRealName);
    		commonService.saveOrUpdate("fn_govopinion_sql", "updateGovopinion", updateParams);
        }
		return "redirect:setGovOpinion.c?proId="+proId;  
	}
	
	@RequestMapping("/download")   
    public ModelAndView download(String proId, HttpServletRequest request, HttpServletResponse response)   
            throws Exception {   
        response.setContentType("text/html;charset=utf-8");   
        request.setCharacterEncoding("UTF-8");   
        java.io.BufferedInputStream bis = null;   
        java.io.BufferedOutputStream bos = null;   
        
        String saveRootPath = PropertyConfigurerSon.getContextProperty("fileupload.path");
		saveRootPath = PropertyConfigurerSon.replaceFileSeparator(saveRootPath);
		
        Map paramMap = new HashMap();
        paramMap.put("proId", proId);
		Map dataMap = commonService.loadSingleData("fn_govopinion_sql", "getGovopinionByProId",paramMap);
		String opinionFilePath = (String) dataMap.get("opin_file_path");
		String opinionFileName = (String) dataMap.get("opin_file_name");
		String downLoadPath = saveRootPath+opinionFilePath;
        try {   
            long fileLength = new File(downLoadPath).length();   
            response.setContentType("application/x-msdownload;");   
            response.setHeader("Content-disposition", "attachment; filename="  
                    + new String(opinionFileName.getBytes("utf-8"), "ISO8859-1"));   
            response.setHeader("Content-Length", String.valueOf(fileLength));   
            bis = new BufferedInputStream(new FileInputStream(downLoadPath));   
            bos = new BufferedOutputStream(response.getOutputStream());   
            byte[] buff = new byte[2048];   
            int bytesRead;   
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {   
                bos.write(buff, 0, bytesRead);   
            }   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            if (bis != null)   
                bis.close();   
            if (bos != null)   
                bos.close();   
        }   
        return null;   
    }   
	
}
