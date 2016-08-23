package com.css.sysbase.cache.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CodeTypeModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String ctypeName;
	
	private String ctypeCode;
	
	private List<CodeModel> clist= new ArrayList<CodeModel>();
	
	
	public String getCtypeName() {
		return ctypeName;
	}
	public void setCtypeName(String ctypeName) {
		this.ctypeName = ctypeName;
	}
	public String getCtypeCode() {
		return ctypeCode;
	}
	public void setCtypeCode(String ctypeCode) {
		this.ctypeCode = ctypeCode;
	}
	
	public Iterator<CodeModel> getListIt(){
		return clist.iterator();
	}
	public void addCodeModel(CodeModel codeModel){
		clist.add(codeModel);
	}
	public void addAllCodeModels( List<CodeModel> cmlsit){
		clist.addAll(cmlsit);
	}
	
	
	public  CodeModel getCode(String code){
		for(CodeModel c : this.clist){
			if(c.getCodeValue().equals(code)){
				return c;
			}
			
		}
		return null;
	}
	
	public List<Map<String,Object>> getCodeList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Iterator<CodeModel>  it = this.getListIt();
		while(it.hasNext()){
			CodeModel cm = it.next();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("code_name", cm.getCodeName());
			resultMap.put("code_value", cm.getCodeValue());
			//resultMap.put("code_other", cm.getCodeOther());
			list.add(resultMap);
		}
		
		return list;
	}
	
}
