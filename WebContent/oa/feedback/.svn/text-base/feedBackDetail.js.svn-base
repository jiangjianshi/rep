var grid;
// 数据加载参数
var dataGridParams = {
	queryParams : {
		pageSize : 20,// 配置每页显示条数，如果不配置，默认为10
	},
	pageList : [ 10, 20, 30, 50, 100 ],
	columns : [ [ 
	{
		field : 'opinId',
		title : '主键',
		checkbox:true
	}, 
	
		{
			field : 'proId',
			title : '项目ID',
			hidden : true
		},
		{
			field : 'majorId',
			title : '专业ID',
			hidden : true
		},
		{
			field : 'minoId',
			title : '单体ID',
			hidden : true
		},
		{
			field : 'minoName',
			title : '单体名称',
			align : 'left',
			sortable : true,
			width : 130
		},
		{
			field : 'opinCode',
			title : '图号',
			align : 'left',
			sortable : true,
			width : 80
		},
		{
			field : 'opinOption',
			title : '审查意见',
			align : 'left',
			sortable : true,
			width : 160
		},
		{
			field : 'opinSpecNum',
			title : '规范编号',
			align : 'left',
			sortable : true,
			width : 80
		},
		{
			field : 'opinOverType',
			title : '违规类型',
			align : 'left',
			sortable : true,
			width : 130
		},
		{
			field : 'opinDeal',
			title : '设计院回复',
			align : 'left',
			sortable : true,
			width : 130
		},
		{
			field : 'fireOption',
			title : '消防单项',
			align : 'left',
			sortable : true,
			width : 130
		},
		{
			field : 'fireOptionSub',
			title : '消防子项',
			align : 'left',
			sortable : true,
			width : 130
		},
		{
			field : 'opinImp',
			title : '重要程度',
			align : 'left',
			sortable : true,
			width : 80
		}
] ]
};

$(document).ready(function() {
	loadType();
	$('#tt').tabs({    
	    border:false,    
	    onSelect:function(title){  
	    	resetMajorTabBtns_BtnStatus();
	    	grid.datagrid('reload');
	    }    
	});  
	resetMajorTabBtns_BtnStatus();
	
	grid = $("#dataGrid").datagrid({
		title : '',
		url : basePath + '/commonCtrl/loadByPage.c?t=pro_feed_sql&s=searchOptionList',
		queryParams : dataGridParams.queryParams,
		rownumbers : true,
		height : 'auto',
		width : 'auto',
		striped : true,
		loadMsg : '正在加载数据，请稍后！',
		pagination : true,
		border : true,
		singleSelect : true,
		pageSize : dataGridParams.pageSize,
		pageList : dataGridParams.pageList,
		columns : dataGridParams.columns,
		toolbar : '#toolbar',
		onBeforeLoad: function (param) {
			var pp = $('#tt').tabs('getSelected');    
			var majorId = pp.panel('options').majorId;  
			param.majorId = majorId;
			param.proId = proId;
	    },
		onDblClickRow: function(rowIndex, rowData){
			var majorApplyResult =  $('#tt').tabs('getSelected').panel('options').majorApplyResult; 
			if(majorApplyResult=='初审毕' || majorApplyResult=='复审毕'){
				editOpin_BtnStatus();
			}
			var url = basePath + "/commonCtrl/loadSingleData.c?t=apply_opinion-sql&s=getObjInfor&id="+rowData.opinId;
			$.get(url,function(data) {
				var singleData = eval('(' + data + ')');
				$('#submitForm').form('load',singleData[0]);
				$("#feedbackOption").attr('disabled',false);
				$("#feedbackOption").val(singleData[0].opinDeal);
			});
		}
	});
	
}); // $(document).ready--end
//消防单项
//var fireOption_Combo;
////消防子项
//var fireOptionSub_Combo;

function loadType(){  
	//单体
	$('#minoId').combobox({
		url: basePath + "/commonCtrl/loadData.c?t=project_check-sql&s=minoList&proId="+proId,
		valueField : 'minoId',
		textField : 'minoName',
		panelHeight:'auto',editable:false
	});
}
//根据当前选中专业审查状态设置所有按钮的启用状态
function resetMajorTabBtns_BtnStatus(){
	var pp = $('#tt').tabs('getSelected');    
	var majorId = pp.panel('options').majorId; 
	var majorApplyResult = pp.panel('options').majorApplyResult; 
	$("#majorId").val(majorId);
	if(majorApplyResult!='初审毕' || majorApplyResult!='复审毕'){
		disableAllBtn();
	}else{
		enableApply_BtnStatus();
	}
}

//设置为编辑状态
function editOpin_BtnStatus(){
//	$('#updateBtn').linkbutton('enable');
	$('#updateBtn').show();//可操作按钮显示
	$('#updateBtnDisabled').hide();//不可操作按钮隐藏
//	$('#cancelUpdateBtn').linkbutton('enable');
//	$('#addNewBtn').linkbutton('disable');
}


//启用审查按钮状态
function enableApply_BtnStatus(){
//	$('#updateBtn').attr('disabled',false);
	$('#updateBtn').show();//可操作按钮显示
	$('#updateBtnDisabled').hide();//不可操作按钮隐藏
	$('#submit_btn').attr('disabled',false);
	
//	$('#submit_btn').linkbutton('enable');
}

//不可用 所有按钮 
function disableAllBtn(){
	$('#updateBtn').hide();//可操作按钮隐藏
	$('#updateBtnDisabled').show();//不可操作按钮显示
	$('#submit_btn').attr('disabled',true);
}
//更新
function updateBtn() {
	if ($('#submitForm').form('validate')) {
		var url = basePath + '/commonCtrl/saveOrUpate.c?t=pro_feed_sql&s=updateApplyOp';
		var pp = $('#tt').tabs('getSelected');    
		var majorId = pp.panel('options').majorId;
		var id = $("#opinId").val();
		if(id == null){
			alert("请选择一条意见回复之后，再点击保存！")
		}else{
		$('#submitForm').form('submit', {
			url : url,
			onSubmit: function(param){    
		        param.t = 'pro_feed_sql' ;    
		        param.s = 'updateApplyOp';
		    },
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.isSuccess) {
					$.messager.show({
						title:'系统提示',
						msg:'数据更新成功！',
						timeout:2000,
						showType:'show',
						style:{
							right:'',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:''
						}
					});
					
//					addNew_BtnStatus();
					//刷新datagrid
					$('#submitForm input').val('');
					$('#submitForm textarea').val('');
					grid.datagrid('reload');
					disableBton();
				} else {
					alert(result.msg);
				}
			}
		});
		}
	}
}
function disableBton(){
//	$('#submit_btn').linkbutton('disabled');
	$('#updateBtn').hide();//可操作按钮隐藏
	$('#updateBtnDisabled').show();//不可操作按钮显示
	$("#feedbackOption").attr('disabled',true);
}

//提交回复
function saveFeedBackMajorSubmit() {
		var pp = $('#tt').tabs('getSelected');    
		var majorId = pp.panel('options').majorId;
		var majorName = pp.panel('options').title;
		var majorApplyResult = pp.panel('options').majorApplyResult;
		var nextMajorFlowStatus;//下一个状态（专业审查状态）
		if(majorApplyResult=='初审毕'){
			nextMajorFlowStatus='1002_1';//专业审查状态-审查已反馈
		}else if(majorApplyResult=='复审毕'){
			nextMajorFlowStatus='1004_1';//专业审查状态-复审已反馈
		}
		var url = basePath + "/oa/feedback/saveFeedBackMajorSubmit.c?majorId="+majorId+"&proId="+proId+"&applyStatus="+nextMajorFlowStatus;
		$.messager.confirm("系统提示", "所审专业【"+majorName+"】“提交回复”后将不可再对其进行编辑，确认要提交吗？",function(r) {
			if (r) {
				$.get(url,function(data) {
					var result = eval('(' + data + ')');
					if (result.isSuccess) {
						$.messager.show({
							title:'系统提示',
							msg:'专业项【'+majorName+'】提交回复成功！',
							timeout:4000,
							showType:'show',
							style:{
								right:'',
								top:document.body.scrollTop+document.documentElement.scrollTop,
								bottom:''
							}
						});
						disableAllBtn();
						$('#tt').tabs('getSelected').panel('options').majorApplyResult='初审合格';
					} else {
						alert(result.msg);
					}
				});
			}
		});
}

