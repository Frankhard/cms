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

    <link rel="stylesheet" type="text/css" href="${ctx}/datejs/jQuery-Timepicker-Addon/demos.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/datejs/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.css">
    <script type="text/javascript" src="${ctx}/datejs/jQuery-Timepicker-Addon/jquery-ui-timepicker-addon.js"></script>
    <script type="text/javascript" src="${ctx}/datejs/js/jquery.ui.datepicker-zh-CN.js.js"></script>
    <script type="text/javascript" src="${ctx}/datejs/js/jquery-ui-timepicker-zh-CN.js"></script>

    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${ctx}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/js/xadmin.js"></script>
    
     <script type="text/javascript" src="${ctx}/ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="${ctx}/ueditor/ueditor.all.min.js"></script>
</head>
 <body>
		
    <div class="x-body">
        <form class="layui-form" method="post" layui-formaction="" autocomplete="off">
        
         <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>封面
              </label>
              <div class="layui-input-inline">
                  <button type="button" class="layui-btn" id="test1">
					  <i class="layui-icon">&#xe67c;</i>上传图片
				  </button>
				  <img alt="" src="" id="img" style="height: 100px;width: 120px;display: none">
				  <input type="hidden" name="img" value="" id="inputImg" lay-verify="required">
              </div>
          </div>

           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="title" name="title" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>举办时间
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="startTime" name="startTime" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" readonly="readonly">
              </div>
          </div>
            <div class="layui-form-item">
                <label for="username" class="layui-form-label">
                    <span class="x-red">*</span>结束时间
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="endTime" name="endTime" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" readonly="readonly">
                </div>
            </div>
          	<!-- private String title;标题
	private String img;封面图
	private String startTime;举办时间
	private String address;地址
	private String budget;申请资金
	private String content;活动内容
	private Integer status;0 新建  1 审核通过 2 审核未通过 -->
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>地址
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="address" name="address" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>申请资金
              </label>
              <div class="layui-input-inline">
                  <input type="number" id="budget" name="budget" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
            <div class="layui-form-item">
                <label for="username" class="layui-form-label">
                    <span class="x-red">*</span>剩余容量
                </label>
                <div class="layui-input-inline">
                    <input type="number" id="memberCount" name="memberCount" required="" lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
          
          <div class="layui-form-item layui-form-text">
              <label for="desc" class="layui-form-label">
                  内容
              </label>
              <div class="layui-input-block">
                  <textarea style="width: 500px;height: 400px" placeholder="请输入内容" id="content" name="content" class="layui-textarea"></textarea>
              </div>
          </div>
         
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <button  class="layui-btn" lay-submit lay-filter="add" lay-submit="">
                  增加
              </button>
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

    layui.use(['form','layer'], function(){
        $ = layui.jquery;
        var form = layui.form
            ,layer = layui.layer;

        //监听提交
        form.on('submit(add)', function(data){
            console.log("=======================================================================");
            console.log(data.field);
            $.ajax({
                type : "post",
                contentType: "application/json",
                url : "${ctx}/activity/exAdd",
                data : JSON.stringify({title:$('#title').val(),
                    img:$('#inputImg').val(),
                    title:$('#title').val(),
                    startTime:$('#startTime').val(),
                    endTime:$('#endTime').val(),
                    address:$('#address').val(),
                    budget:$('#budget').val(),
                    memberCount:$('#memberCount').val(),
                    content:$('#content').val(),
                }),
                dataType:"json",
                success : function(data) {

                    var errcode = data.errcode;
                    var msgr = data.msg;
                    var url=ctx+data.url;
                    if (errcode == 200){
                        layer.msg(msgr,{icon:1,time:3000,shade:0.4}, function (){
                            window.location.href = url;
                        });
                    }else{
                        layer.msg(msgr,{icon:5,time:3000,shade:0.4});
                    }
                },
                error : function() {
                    alert("提交失败");
                }
            });
            return false;
        });


    });

layui.use('laydate', function(){
	  var laydate = layui.laydate;
	  
	  //执行一个laydate实例
	  laydate.render({
	    elem: '#startTime' //指定元素
	    	,type: 'datetime'
	  });
    laydate.render({
        elem: '#endTime' //指定元素
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
      localStorage.setItem("inputImgUrl",res.url)
    }
    ,error: function(){
      //请求异常回调
    }
  });
});

</script>
</html>