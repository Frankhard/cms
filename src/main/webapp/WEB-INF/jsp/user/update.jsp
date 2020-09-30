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
 
    <div class="x-body">
        <form class="layui-form" method="post" action="${ctx}/user/exUpdate" enctype="multipart/form-data">
        
        <input type="hidden" name="id" value="${obj.id}">
        
        <!-- 	private String name;姓名
	private String code;学号
	private String xy;学院
	private String zy;专业
	private String phone;手机号
	private String password;密码 -->
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="name" required="" value="${obj.name }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>学号
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="no" required="" value="${obj.no }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>学院
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="xy" required="" value="${obj.xy }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>专业
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="zy" required="" value="${obj.zy }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>手机号
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="phone" required="" value="${obj.phone }"lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>密码
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="password" required="" value="${obj.password }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-filter="add" lay-submit="">
                  修改
              </button>
          </div>
      </form>
    </div>
    
  </body>

</html>