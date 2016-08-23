$(function() {
	loadBuildProjectType();
	loadBuildProMinType();
	loadBuildProStatType();
});
function loadBuildProjectType(){  
	//项目类型
	$('#proType').combobox({
		data : auto_js_codes_imp['xmlxs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
	//工程等级
	$('#proGrad').combobox({
		data : auto_js_codes_imp['pro_grades_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
	//工程性质
	$('#proProperty').combobox({
		data : auto_js_codes_imp['pro_propertys_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
	//工程规模
	$('#proScale').combobox({
		data : auto_js_codes_imp['pro_scales_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
	//审查类别
	$('#examineType').combobox({
		data : auto_js_codes_imp['apply_types_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
	//重点项目
	$('#majorPro').combobox({
		data : auto_js_codes_imp['zdxms_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
	//资质等级
	$('#kcdwGrad').combobox({
		data : auto_js_codes_imp['zzdjs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
	//资质等级
	$('#sjdwGrad').combobox({
		data : auto_js_codes_imp['zzdjs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
	//资质等级
	$('#scjgGrad').combobox({
		data : auto_js_codes_imp['sczzdjs_js'],
		valueField : 'code_value',
		textField : 'code_name',
		panelHeight:'auto'
	});
}
//保存
function save() {
	if ($('#submitForm').form('validate')) {
		var url = basePath + '/oa/baseinfor/updateBuildProjectSave.c';
		$('#submitForm').form('submit', {
			url : url,
			success : function(result) {
				var result = eval('(' + result + ')');
				if (result.isSuccess) {
					alert(result.msg);
					//回调父页面查询方法，刷新datagrid
					getParentWindow().search();
					//关闭本弹出窗口
					Dialog.close();
				} else {
					alert(result.msg);
				}
			}
		});
	}
}


function selectBuildunit(){
	var url = basePath+"/oa/baseinfor/selectBuildunitList.c";
	openDialog(500,300,"选择",url);
}
function selectBuildunit_callBack(obj){
	$("#buildUnit").val(obj.name);
}


function selectKcdwunit(){
	var url = basePath+"/oa/baseinfor/selectKcdwunitList.c";
	openDialog(800,300,"选择",url);
}
function selectKcdwunit_callBack(obj){
	$("#kcdw").val(obj.name);
	$("#kcdwCertno").val(obj.certNo);
	$('#kcdwGrad').combobox('setValue',obj.qualifyGradeCode);
}

function selectSjdwunit(){
	var url = basePath+"/oa/baseinfor/selectSjdwunitList.c";
	openDialog(800,300,"选择",url);
}
function selectSjdwunit_callBack(obj){
	$("#sjdw").val(obj.name);
	$("#sjdwCertno").val(obj.certNo);
	$('#sjdwGrad').combobox('setValue',obj.qualifyGradeCode);
}


function selectScdwunit(){
	var url = basePath+"/oa/baseinfor/selectScdwunitList.c";
	openDialog(800,300,"选择",url);
}
function selectScdwunit_callBack(obj){
	$("#scjg").val(obj.name);
	$("#scjgCertno").val(obj.certNo);
	$("#scjgPhone").val(obj.phone);
	$("#scjgFax").val(obj.fax);
	$('#scjgGrad').combobox('setValue',obj.qualifyGradeCode);
}