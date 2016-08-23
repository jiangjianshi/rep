package com.css.sysbase.cache;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.css.sysbase.cache.model.CodeModel;
import com.css.sysbase.cache.model.CodeTypeModel;
import com.css.sysbase.service.CommonService;

public class CodeTBCache implements Serializable {

	private static final long serialVersionUID = 7847565341696005841L;
	private final Log log = LogFactoryImpl.getFactory().getInstance(CodeTBCache.class);
	private static CodeTBCache codeTBCache = null;
	private static Map<String, CodeTypeModel> codeMap = new HashMap<String, CodeTypeModel>();
	
	private CodeTBCache() {
		init();
	}
	
	@SuppressWarnings("rawtypes")
	private void init(){
		try {
			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			CommonService commonService = (CommonService) wac.getBean("commonService");
			Map<String,Object> reqMap = new HashMap<String, Object>();
			List<Map> ctypes=commonService.loadData("sys_conf","searchCtype",reqMap);
			List<Map> codes=commonService.loadData("sys_conf","searchCode",reqMap);

			for(Map<String, Object>re:ctypes){
				CodeTypeModel ctModel = new CodeTypeModel();
				ctModel.setCtypeCode((String) re.get("ctype_code"));
				ctModel.setCtypeName((String) re.get("ctype_name"));
				
				try {
					if(re.get("ctype_flag").equals("S")){
						List<Map> css= commonService.executeRawSql(re.get("ctype_sql").toString());
						if(css.size()!=0) {
							for(Map<String,Object> ocs : css){
								CodeModel codeModel = new CodeModel();
								codeModel.setCodeName((String)ocs.get("code_name"));
								codeModel.setCodeValue(String.valueOf(ocs.get("code_value")));
								ctModel.addCodeModel(codeModel);
							}
						}
					}else{
						for(Map<String, Object>chre:codes){
							if(re.get("ctype_code").equals(chre.get("ctype_code"))){
								CodeModel codeModel = new CodeModel();
								codeModel.setCodeName((String)chre.get("code_name"));
								codeModel.setCodeValue((String)chre.get("code_value"));
								codeModel.setCodeOther((String)chre.get("code_other"));
								ctModel.addCodeModel(codeModel);
							}
						}
					}
					codeMap.put((String)re.get("ctype_code"), ctModel);
				}catch (Exception e) {
					if(log.isDebugEnabled()) log.debug(e.getMessage(),e);
					StringBuffer error = new StringBuffer();
					error.append("加载[");
					error.append(ctModel.getCtypeName());
					error.append("]数据失败,请检查配置 Code_type is [");
					error.append(ctModel.getCtypeCode());
					error.append("]");
					log.error(error);
				}
				
				
			}
			
		} catch (Exception e) {
			log.debug(e.getMessage(),e);
		}
		
	}
	
	
	
	
	public synchronized static CodeTBCache getInsCodeCache() {
		if (codeTBCache == null) {
			
			codeTBCache = new CodeTBCache();
		}
		return codeTBCache;
	}
	
	
	public void refresh() {
		synchronized (codeTBCache) {
			codeMap.clear();
			this.init();
		}
	}

	
	public CodeTypeModel getCodes(String codeType){
		return codeMap.get(codeType);
	}
	
	public List<Map<String,Object>> getCodeList(String codeType){
		
		return codeMap.get(codeType).getCodeList();
	}
	
	
}
