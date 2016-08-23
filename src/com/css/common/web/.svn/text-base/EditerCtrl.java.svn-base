package com.css.common.web;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.css.sysbase.service.CommonService;
import com.css.utils.MyFileUtils;
import com.css.utils.web.BaseController;
import com.css.utils.web.CtrlUtils;
import com.css.utils.web.DateUtils;
import com.css.utils.web.PageInfor;

@Controller
@RequestMapping("/common")
public class EditerCtrl extends BaseController {
	/** 视图文件位置 */
	public String subPath = "common/";
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	/**
	 * 跳转 页面
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/{fileName}")
	public ModelAndView vistPage(@PathVariable String fileName,HttpServletRequest request) throws Exception{
		Map paramMap = CtrlUtils.getReqParametersMap(request);
		paramMap.put("basePath", request.getContextPath());
		return getModelAndView(subPath, fileName, paramMap);
	}
	
	@RequestMapping("/editerImgList")
	public ModelAndView editerImgList(HttpServletRequest request) throws Exception{
		Map map = new HashMap();
		//设置分页信息
		PageInfor pageInfo = new PageInfor();
		pageInfo.setPageSize(14);
		pageInfo.setPageSizeAndPageNumber(request);
		Map paramMap = CtrlUtils.getReqParametersMap(request);
		
		//加载数据
		pageInfo = commonService.loadDataByPager("editer-sql", "searchImgList", paramMap,pageInfo);
		map.put("dataList", pageInfo.getDatas());
		map.put("pageInfo", pageInfo);
		return getModelAndView(subPath, "editerImgList", map);
	}
	
	/**
	 * 处理上传图片
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/uploadEditerImg")
	public String uploadEditerImg(HttpServletRequest request, @RequestParam("imgFile") MultipartFile imgFile) throws Exception{
		String webImgPath = "common/editerimg/";
		String fileuploadPath = CtrlUtils.getWebrootPath("/common/editerimg/");
    	String fileName = imgFile.getOriginalFilename();
    	String fileExt = fileName.substring(fileName.lastIndexOf(".")+1);
    	 //构建新的文件名，用于服务端存储文件
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern("yyyyMMddHHmmssSSS");
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
        String filePath = fileuploadPath + newFileName;  
        imgFile.transferTo(MyFileUtils.createFile(new File(filePath))); 
        Map<String,Object> fileParams = new HashMap<String,Object>();
        fileParams.put("id",String.valueOf(UUID.randomUUID()));
        fileParams.put("name",fileName);
		fileParams.put("filePath", webImgPath+newFileName);
		fileParams.put("cTime", DateUtils.getDateNow(null));
		commonService.saveOrUpdate("editer-sql", "addImg", fileParams);
		return "redirect:/common/editerImgList.c";  
	}

}
