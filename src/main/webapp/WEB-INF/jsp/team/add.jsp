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
        <form class="layui-form" method="post" layui-formaction="" autocomplete="off">
       
         <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>LOGO
              </label>
              <div class="layui-input-inline">
                  <button type="button" class="layui-btn" id="test1">
					  <i class="layui-icon">&#xe67c;</i>上传图片
				  </button>
				  <img alt="" src="" id="img" style="height: 100px;width: 120px;display: none">
				  <input type="hidden" name="img" value="" id="inputImg" lay-verify="required">
              </div>
          </div>
           <!-- private String img;
	private String type;隶属于
	private String content; 简介
	private String name;社团名称
	private Integer userId;社长id
	private int ministerCount;部长容量-->
          
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>社团名称
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="username" name="name" required="" lay-verify="required"
                  autocomplete="off" class="layui-input">
              </div>
          </div>
          
           <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>隶属于
              </label>
              <div class="layui-input-inline">
                  <select id="shipping" name="type" class="valid">
                    <option value="校级">校级</option>
                    <option value="院级">院级</option>
                  </select>
              </div>
          </div>

            <div class="layui-form-item">
                <label for="username" class="layui-form-label">
                    <span class="x-red">*</span>部长容量
                </label>
                <div class="layui-input-inline">
                    <input type="number" id="username" name="ministerCount" min="0" required="" lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
          
          <div class="layui-form-item layui-form-text">
              <label for="desc" class="layui-form-label">
                  简介
              </label>
              <div class="layui-input-block">
                  <textarea style="width: 500px;height: 400px" placeholder="请输入内容" id="desc" name="content" class="layui-textarea"></textarea>
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
                url : "${ctx}/team/exAdd",
                data : data.field,
                dataType:"json",
                success : function(data) {

                    var errcode = data.errcode;
                    var msgr = data.msg;
                    if (errcode == 200){
                        layer.msg(msgr,{icon:1,time:3000,shade:0.4}, function (){
                            window.location.href = ctx+data.url;
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

    layui.use('form', function(){
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#cbTime' //指定元素
        });
    });
    layui.use('laydate', function(){
	  var laydate = layui.laydate;

	  //执行一个laydate实例
	  laydate.render({
	    elem: '#cbTime' //指定元素
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