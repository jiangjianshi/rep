package com.css.utils.cache.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.css.sysbase.service.CommonService;

public class LoginSessionCache {
	//[用户菜单资源]缓存
	private List<Map> menuList;
	//[用户角色资源]缓存
	private List<Map> roleList;
	//[用户动作资源]缓存
	private Map rightPointCache = new HashMap();
	//[用户信息]缓存
	private Map userInforMap;
	
	/**
	 * 初始化[用户访问资源]缓存
	 * @param commonService
	 */
	public void initSessoinCache(HttpSession session,CommonService commonService){
		Map userMap = (Map) session.getAttribute(session.getId());
		userInforMap = userMap;
		Map searchParam = new HashMap();
		//加载角色
		String userId = (String)userMap.get("user_id");
		searchParam.put("userId", userId);
		roleList = commonService.loadData("role-sql", "getRoleListByUserId",searchParam);
		
		//加载菜单
		List<String> roleCodeList = new ArrayList<String>();
		if(roleList.size()>0){
			for (Map role : roleList) {
				roleCodeList.add((String)role.get("roleCode"));
			}
		}else{
			roleCodeList.add("");
		}
		searchParam.put("roleCodes", roleCodeList);
		menuList = commonService.loadData("right-sql", "sysLeftMenuTree",searchParam);
		List<Map> rightPoints = commonService.loadData("right-sql", "rightPointsCache",searchParam);
		for (Map rp : rightPoints) {
			rightPointCache.put((String)rp.get("rightCode"), (String)rp.get("rightName"));
		}
	}

	public List<Map> getMenuList() {
		return menuList;
	}

	public List<Map> getRoleList() {
		return roleList;
	}

	public Map getRightPointCache() {
		return rightPointCache;
	}
	
	public boolean isHaveRight(String rightCode){
		return rightPointCache.get(rightCode)==null?false:true;
	}
	
}
