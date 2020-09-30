<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="application/json" />
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
        <form class="layui-form" method="post" action="${ctx}/activity/exUpdate" enctype="multipart/form-data">
         <input type="hidden" name="id" value="${obj.id}">
         <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>封面
              </label>
               <div class="layui-input-inline">
				  <img alt="" src="${ctx}${obj.img}" id="img" style="height: 100px;width: 120px;">
				  <input type="hidden" name="img" value="${obj.img}" id="inputImg" lay-verify="">
              </div>
          </div>

           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="title" value="${obj.title }" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>

            <div class="layui-form-item">
                <label for="username" class="layui-form-label">
                    <span class="x-red">*</span>举办时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="startTime" name="startTime" value="${obj.startTime }" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" readonly="readonly">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="username" class="layui-form-label">
                    <span class="x-red">*</span>结束时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="endTime" name="endtTime" value="${obj.endTime }" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" readonly="readonly">
                </div>
            </div>
          	<!-- private String title;标题
	private String img;封面图
	private String starttime;举办时间
	private String address;地址
	private String budget;申请资金
	private String content;活动内容
	private Integer status;0 新建  1 审核通过 2 审核未通过 -->
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>地址
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="address" required="" value="${obj.address }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>申请资金
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="budget" required="" value="${obj.budget }" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
            <div class="layui-form-item">
                <label for="username" class="layui-form-label">
                    <span class="x-red">*</span>剩余容量
                </label>
                <div class="layui-input-inline">
                    <input type="number" id="username" name="memberCount" value="${obj.memberCount }"  required="" lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
          
          <div class="layui-form-item layui-form-text">
              <label for="desc" class="layui-form-label">
                  内容
              </label>
              <div class="layui-input-block">
                  <textarea style="width: 500px;height: 400px" placeholder="请输入内容" id="desc" name="content" class="layui-textarea">${obj.content}</textarea>
              </div>
          </div>
         
      </form>
    </div>
    
  </body>

<script type="text/javascript">
    $(function(){
        $('input[name="startTime"],input[name="endtime"]').datepicker({
            dateFormat: "yy-mm-dd HH-mm-ss"
        });
    })

layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#startTime' //指定元素
	    	,type: 'datetime'
	  });
	});
	
	
	
layui.use('upload', function(){
  var upload = layui.upload;
   
  //执行实例
  var uploadInst = upload.render({
    elem: '#test1' //绑定元素
    ,url: '${ctx}/upload/' //上传接口
    ,accept:'images',
    field:'upfile'
    
   	,before: function(obj){ 
   		layer.load() //上传loading
           //预读本地文件示例，不支持ie8 
           obj.preview(function(index, file, result){ 
               $('#img').attr('src', result); //图片链接（base64） 
               $('#img').show(); //图片链接（base64） 
               }); 
       } 
    ,done: function(res){
      //上传完毕回调
      layer.closeAll('loading'); //关闭loading
      $("#inputImg").val(res.url);
    }
    ,error: function(){
      //请求异常回调
    }
  });
});

</script>
</html>