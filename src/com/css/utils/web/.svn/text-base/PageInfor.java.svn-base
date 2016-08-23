package com.css.utils.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PageInfor {

	private int pageSize = 10;

	private int pageNumber = 1;

	private int totalRows;
	
	private String sort;

	private String order;
	
	private String jumpUrl;

	private List datas;

	String reqPageNumber;
	String reqPageSize;
	String reqSort;
	String reqOrder;

	public PageInfor() {
	}

	public PageInfor(int defaultPageSize, int defaultPageNumber) {
		pageSize = defaultPageSize;
		pageNumber = defaultPageNumber;
	}

	/**
	 * 从request请求中，获得参数
	 * 
	 * @param req
	 */
	public void setPageSizeAndPageNumber(HttpServletRequest req) {
		reqPageNumber = req.getParameter("page");
		reqPageSize = req.getParameter("rows");
		reqSort = req.getParameter("sort");
		reqOrder = req.getParameter("order");

		if (reqPageNumber != null) {
			pageNumber = Integer.parseInt(reqPageNumber);
		}
		if (reqPageSize != null) {
			pageSize = Integer.parseInt(reqPageSize);
		}
		if(reqSort != null){
			sort = reqSort;
		}
		if(reqOrder != null){
			order = reqOrder;
		}
	}

	/**
	 * 获得当前行
	 * 
	 * @return
	 */
	public int getCurrentRow() {
		return pageSize * (pageNumber - 1);
	}
	
	/**
	 * 获得总页数
	 * @return
	 */
	public int getTotalPages(){
		if(totalRows<=0){
			return 0;
		}else{
			return totalRows/pageSize+(totalRows%pageSize==0?0:1);
		}
	}
	

	public String getPageHtml() {
		StringBuffer pageHtml = new StringBuffer(); 
		
		if(pageNumber == 1 || getTotalPages() == 0){
			pageHtml.append("<a class='disabled'> << </a>");
			pageHtml.append("<a class='disabled'> < </a>");
		}else{
			pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+1+");' > << </a>");
			pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+(pageNumber-1)+");' > < </a>");
		}
		
		if(pageNumber - 3 - 2 <= 1){
			for(int i=1;i<pageNumber;i++){
				pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+i+");'> "+i+" </a>");
			}
		}else{
			pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage(1);'> 1 </a>");
			pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage(2);'> 2 </a>");
			pageHtml.append("...");
			for(int i=pageNumber-3;i<pageNumber;i++){
				pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+i+");'> "+i+" </a>");
			}
		}
		
		pageHtml.append("<a class='current'> "+pageNumber+" </a>");
		
		if(getTotalPages() - 2 - (pageNumber+3) <= 1){
			for(int i=pageNumber+1;i<=getTotalPages();i++){
				pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+i+");'> "+i+" </a>");
			}
		}else{
			for(int i=pageNumber+1;i<=pageNumber+3;i++){
				pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+i+");'> "+i+" </a>");
			}
			pageHtml.append("...");
			for(int i=getTotalPages()-2;i<=getTotalPages();i++){
				pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+i+");'> "+i+" </a>");
			}
		}
		
		if(pageNumber == getTotalPages()){
			pageHtml.append("<a class='disabled'> > </a>");
			pageHtml.append("<a class='disabled'> >> </a>");
			
		}else{
			pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+(pageNumber+1)+");'> > </a>");
			pageHtml.append("<a href='javascript:void(0);' onClick='gotoPage("+getTotalPages()+");' > >> </a>");
		}
		
//		pageHtml.append("<font color='#666666'>跳转到：</font>");
//		pageHtml.append("<label><input type='text' id='jumpToPageInput' size='3' value='"+pageNumber+"' class='btn' /></label>&nbsp;");
//		pageHtml.append("<a href='javascript:void(0);' onClick='jumpToPage();'>GO</a>");
		pageHtml.append("<a class='disabled'>合计<font color='#FE6500'>"+totalRows+"</font>条记录， 每页<font color='#FE6500'>"+pageSize+"</font>条，共<font color='#FE6500'>"+getTotalPages()+"</font>页 </a>");
		return pageHtml.toString();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public List getDatas() {
		return datas;
	}

	public void setDatas(List datas) {
		this.datas = datas;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
