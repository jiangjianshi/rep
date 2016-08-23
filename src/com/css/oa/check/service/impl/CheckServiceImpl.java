package com.css.oa.check.service.impl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.css.oa.check.service.CheckService;
import com.css.oa.check.service.FlowService;
import com.css.sysbase.dao.CommonManager;
import com.css.sysbase.service.CommonService;
import com.css.utils.TimeUtils;

@Service("checkService")
public class CheckServiceImpl implements CheckService {
	
	@Resource(name = "commonManager")
	private CommonManager commonManager;
	
	@Resource(name = "commonService")
	private CommonService commonService;
	
	@Resource(name = "flowService")
	private FlowService flowService;

	@Override
	public void checkDetailSubSave(HttpServletRequest request) throws Exception {
		Map paramMap = new HashMap();
		String[] minoIds = request.getParameterValues("minoId");
		String[] opinCodes = request.getParameterValues("opinCode");
		String[] opinOptions = request.getParameterValues("opinOption");
		String[] opinSpecNums = request.getParameterValues("opinSpecNum");
		String[] opinIds = request.getParameterValues("opinId");
		if(opinIds!=null){
			for(int i=0;i<opinIds.length;i++){
				paramMap.put("minoId",minoIds[i]);
				paramMap.put("opinCode",new String(opinCodes[i].getBytes("ISO-8859-1"), "UTF-8")  );
				paramMap.put("opinOption",new String(opinOptions[i].getBytes("ISO-8859-1"), "UTF-8") );
				paramMap.put("opinSpecNum", new String( opinSpecNums[i].getBytes("ISO-8859-1"), "UTF-8") );
				paramMap.put("opinId", opinIds[i]);
				commonManager.saveOrUpdate("apply_opinion-sql", "updateObj", paramMap);
			}
		}
		
		
		String applyStatus = request.getParameter("applyStatus");
		if("hg".equals(applyStatus) || "dfk".equals(applyStatus)){
			String proId = request.getParameter("proId");
			String majorId = request.getParameter("majorId");
			flowService.saveCheckMajorSubmit(proId, majorId, applyStatus);
		}
	}
	
	@Override
	public void proofreadDetailSubSave(HttpServletRequest request) throws Exception {
		String proId = request.getParameter("proId");
		String majorId = request.getParameter("majorId");
		String applyStatus = request.getParameter("applyStatus");
		String maxRound = request.getParameter("maxRound");
		
		Map paramMap = new HashMap();
		paramMap.put("proId", proId);
		paramMap.put("majorId", majorId);
		
		if("dfk".equals(applyStatus)){
			String opinOption = request.getParameter("opinOption");
			paramMap.put("opinId", String.valueOf(UUID.randomUUID()));
			paramMap.put("proId", proId);
			paramMap.put("majorId", majorId);
			paramMap.put("opinOption",new String(opinOption.getBytes("ISO-8859-1"), "UTF-8") );
			paramMap.put("round", Integer.parseInt(maxRound)+1);
			paramMap.put("createTime", TimeUtils.getInstance().getTimeByFormat("yyyy-MM-dd HH:mm:ss"));
			int n = commonManager.saveOrUpdate("apply_opinion-sql", "addProofreadOpinion", paramMap);
		}
		
		if("hg".equals(applyStatus) || "dfk".equals(applyStatus)){
			flowService.saveCheckMajorSubmit(proId, majorId, applyStatus);
		}
	}
	
}
