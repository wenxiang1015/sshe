<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSHE DEMO</title>
<script type="text/javascript" src="script/jquery.js" ></script>
<script type="text/javascript" src="script/jquery.easyui.min.js" ></script> 
<script type="text/javascript" src="script/locale/easyui-lang-zh_CN.js" ></script> 
<script type="text/javascript" src="script/common.js"></script>
<link rel="stylesheet" href="style/themes/bootstrap/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="style/themes/icon.css" type="text/css"></link>
<script type="text/javascript">

	$(function(){

		$('#index_regDialog').dialog({
			title:'注册',
			//closed:true,
			modal:true,
			buttons:[{
				text:'注册',
				iconCls:'icon-ok',
				handler:function(){
//					$('#index_regForm').submit();
					register();
				}
			}]
		}).dialog('close');
		
/*		$('#index_regForm').form({
		    url:'${pageContext.request.contextPath }/userAction!register.action',    
		    success:function(data){    
//		    	var obj = eval("("+data+")"); //使用原生的js 方法将 json 字符串转为json 对象,不标准的 json 字符串也可以转化(标准的json 属性和值两边加的都是双引号,不推荐使用该方法)
		  		var obj = jQuery.parseJSON(data);
		  		if(obj.success){
		  			$('#index_regDialog').dialog('close');
		  		}
		  		$.messager.show({
		  			title:'我的消息',
		  			msg:obj.msg
		  		});
		    }    
		});
*/		

		//增加提交回车功能,13代表回车
		$('#index_regForm input').keypress(function(e){
			if(e.keyCode=='13'){ 
//				$('#index_regForm').submit();
				register();
			}
		});
		
		$('#index_loginForm').form({
			url:'${pageContext.request.contextPath }/userAction!login.action',
			success:function(data){
				var obj = jQuery.parseJSON(data);
		  		if(obj.success){
		  			$('#index_loginDialog').dialog('close');
		  		}
		  		$.messager.show({
		  			title:'我的消息',
		  			msg:obj.msg
		  		});
			}
		});
		
		$('#index_loginForm input').keypress(function(e){
			if(e.keyCode=='13'){ 
				$('#index_loginForm').submit();
			}
		});
		
	});
	
	function register(){
		if($('#index_regForm').form('validate')){
			$.ajax({
			   type: "POST",
			   url: "${pageContext.request.contextPath }/userAction!register.action",
			   data: $('#index_regForm').serialize(),
	/*		   {
				   username:$('#index_regForm input[name="username"]').val(),
				   password:$('#index_regForm input[name="password"]').val()
			   }, */
			   dataType:"json",
			   success: function(obj){
				   if(obj.success){
			  			$('#index_regDialog').dialog('close');
			  		}
			  		$.messager.show({
			  			title:'我的消息',
			  			msg:obj.msg
			  		});
			   }
			});
		}else{
			$.messager.alert('警告','请按要求填写！'); 
		}
	}
</script>
</head>
<body class="easyui-layout">
		<div data-options="region:'north'" style="height:60px"></div>
		<div data-options="region:'south',split:true" style="height:60px;"></div>
		<div data-options="region:'east',split:true" title="East" style="width:150px;"></div>
		<div data-options="region:'west',split:true" style="width:150px;">
			<div class="easyui-panel" title="菜单" data-options="border:false,fit:true" ></div>
		</div>
		<div data-options="region:'center',title:'Main Title',iconCls:'icon-ok'"></div>
		<!-- $('#index_regForm').form('load',{username:'',password:'',rePwd:''}); 初始化表单用这种方式也可以 -->
		<div id="index_loginDialog" class="easyui-dialog" data-options="title:'登录',closable:false,modal:true,buttons:[{
				text:'注册',
				iconCls:'icon-edit',
				handler:function(){
					$('#index_regForm input').val('');
					$('#index_regDialog').dialog('open');
				}
			},{
				text:'登录',
				iconCls:'icon-help',
				handler:function(){
					$('#index_loginForm').submit();
				}
			}]" >
			<form id="index_loginForm" method="post" >
				<table>
						<tr>
							<th>用户名</th>
							<td><input name="username" class="easyui-textbox" data-options="required:true,iconCls:'icon-man',missingMessage:'用户名为必填项'"  /></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="password" type="password" class="easyui-textbox" data-options="required:true,iconCls:'icon-lock'" /></td>
						</tr>
				</table>
			</form>
		</div>
		
		<div id="index_regDialog" >
			<form id="index_regForm" method="post">
				<table>
					<tr>
						<th>用户名</th>
						<td><input name="username" class="easyui-validatebox" data-options="required:true,missingMessage:'用户名为必填项'" /></td>
					</tr>
					<tr>
						<th>密码</th>
						<td><input name="password" type="password" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<tr>
						<th>重复密码</th>
						<td><input name="rePwd" type="password" class="easyui-validatebox" data-options="required:true,validType:'eqPassword[\'#index_regForm input[name=password]\']'" /></td>
					</tr>
					<tr>
						<th>姓名</th>
						<td><input name="realname" class="easyui-validatebox" data-options="required:true" /></td>
					</tr>
					<!-- gender 值无法 post .. -->
<!-- 					<tr>
						<th>性别</th>
						<td>
							男<input name="gender" type="radio" value="0" checked="checked" />
							女<input name="gender" type="radio" value="1" />
						</td>
					</tr> -->
					<tr>
						<th>年龄</th>
						<td><input name="age" class="easyui-numberbox" data-options="min:0,max:120,precision:0" /></td>
					</tr>
					<tr>
						<th>手机</th>
						<td><input name="phoneNumber" class="easyui-validatebox" data-options="required:true,validType:'phoneNum'" /></td>
					</tr>
					<tr>
						<th>邮箱</th>
						<td><input name="email" class="easyui-validatebox" data-options="required:true,validType:'email'" /></td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>