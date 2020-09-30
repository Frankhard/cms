<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglibs.jsp"%>
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
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${ctx}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/js/xadmin.js"></script>
    
     <script type="text/javascript" src="${ctx}/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctx}/ueditor/ueditor.all.min.js"></script>
</head>
 <body>

    <div class="x-body  layui-form">
         <form class="layui-form" method="post" layui-formaction="" autocomplete="off">
            <input type="hidden" name="teamId" value="${obj.id}">
          
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>选择成员
              </label>
              <div class="layui-input-inline">
                  <select id="shipping" name="userId" class="valid">
                    <c:forEach items="${list}" var="data">
						<option value="${data.user.id}" ${data.user.id == obj.userId ? "selected" : ""}>${data.user.name}</option>
					</c:forEach>
                  </select>
              </div>
          </div>
          
         <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-lay-submit lay-filter="rm" lay-submit="">
                  确定任命
              </button>
          </div>
        </form>
    </div>

  </body>
<script type="text/javascript">
    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;

        //监听提交
        form.on('submit(rm)', function(data){
            console.log("=======================================================================");
            console.log(data.field);
            $.ajax({
                type : "post",
                url : "${ctx}/team/exRm",
                data : data.field,
                dataType:"json",
                success : function(data) {

                    var errcode = data.errcode;
                    var msgr = data.msg;
                    if (errcode == 200){
                        layer.msg(msgr,{icon:1,time:2000,shade:0.4}, function (){
                            window.location.href = ctx+data.url;
                        });
                    }else{
                        layer.msg(msgr,{icon:5,time:2000,shade:0.4});
                    }
                },
                error : function() {
                    alert("提交失败");
                }
            });
            return false;
        });


    });
</script>
</html>