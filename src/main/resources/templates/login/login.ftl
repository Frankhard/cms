<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <link type="text/css" rel="stylesheet" href="${request.contextPath}/ml/css/style.css">
    <link rel="stylesheet" href="${request.contextPath}/css/font.css">
	<link rel="stylesheet" href="${request.contextPath}/css/xadmin.css">
    <script type="text/javascript" src="${request.contextPath}/js/jquery-1.9.1.min.js"></script>
    <script src="${request.contextPath}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${request.contextPath}/js/xadmin.js"></script>
    <title>登录</title>
</head>
<body class="login-bg">
    
    <div class="login layui-form" >
        <div class="message">管理员登录${request.contextPath}</div>
        <div id="darkbannerwrap"></div>
       <form name="form1" method="post" layui-formaction="/login/toLogin.do" autocomplete="off">
          <div class="layui-form-item" style="margin-left: -40px">
		    <label class="layui-form-label">姓名</label>
		    <div class="layui-input-block">
		      <input type="text" name="name" required value="admin"
		       lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		    <div class="layui-form-item" style="margin-left: -40px">
		    <label class="layui-form-label">密码</label>
		    <div class="layui-input-block">
		      <input type="text" name="passWord" required value="111111"
		        lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
            <input value="登录562" lay-submit lay-filter="login" style="width:100%;" type="button">
            <hr class="hr20" >
        </form>
    </div>
    
    <!-- 底部结束 -->
    
</body>

 <script>
     var ctx = "${request.contextPath}";
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
                 url : ctx+"/login/toLogin.do",
                 data : data.field,
                 dataType:"json",
                 success : function(data) {
                   
                	 var res = data.res;
                	 
                	 if (res == 0){
                		 layer.msg('用户名或者密码错误!');
                	 }else{
                		 layer.msg('登陆成功!'); 
                		 window.location.href = "/login/index.do"
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