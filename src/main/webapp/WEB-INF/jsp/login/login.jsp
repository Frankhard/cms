<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="../../../common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>登录</title>  
     <link type="text/css" rel="stylesheet" href="${ctx}/resource/ml/css/style.css">
     
    <link rel="stylesheet" href="${ctx}/css/font.css">
	<link rel="stylesheet" href="${ctx}/css/xadmin.css">
    <script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/js/xadmin.js"></script>
</head>
<body class="login-bg">
    
    <div class="login layui-form" >
        <div class="message">管理员登录</div>
        <div id="darkbannerwrap"></div>
        
       <form name="form1" method="post" layui-formaction="${ctx}/login/toLogin" autocomplete="off">
       
          <div class="layui-form-item" style="margin-left: -40px">
		    <label class="layui-form-label">账号</label>
		    <div class="layui-input-block">
		      <input type="text" name="name" required value=""
		       lay-verify="required" placeholder="请输入账号" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		    <div class="layui-form-item" style="margin-left: -40px">
		    <label class="layui-form-label">密码</label>
		    <div class="layui-input-block">
		      <input type="password" name="password" required value=""
		        lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		   <div class="layui-form-item" style="margin-left: -40px">
		    <label class="layui-form-label">角色</label>
		    <div class="layui-input-block">
		      <select name="role" lay-verify="required">
		        <option value="0">管理员</option>
		        <option value="1">社长</option>
				<option value="2">部长</option>
		      </select>
		    </div>
		  </div>
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="button">
            <hr class="hr20" >
        </form>
    </div>
    
    <!-- 底部结束 -->
    
</body>

 <script>
        layui.use(['form','layer'], function(){
            $ = layui.jquery;
          var form = layui.form
          ,layer = layui.layer;

          //监听提交
          form.on('submit(login)', function(data){
			 console.log("=======================================================================");
             console.log(data.field);
             $.ajax({ 
                 type : "post",
                 url : "${ctx}/login/toLogin",
                 data : data.field,
                 dataType:"json",
                 success : function(data) {
                   
                	 var res = data.res;
                	 
                	 if (res == 1){
                		 layer.msg('登陆成功!'); 
                		 window.location.href = ctx+"/login/index"
                	 }else{
                		 layer.msg(data.msg);
                	 }
                 },
                 error : function() {
                     alert("提交失败");
                 }
             });
          });
          
          
        });
    </script>
</html>