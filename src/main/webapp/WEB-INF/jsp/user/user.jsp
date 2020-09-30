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
    <link rel="stylesheet" href="${ctx}/css/font.css">
	<link rel="stylesheet" href="${ctx}/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${ctx}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/js/xadmin.js"></script>
</head>
<body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a>
      </span>
      <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
	    <div class="layui-row">
	
	        <form class="layui-form layui-col-md12 x-so" method="post" action="${ctx}/user/findBySql">
	          <input type="text" name="no"  placeholder="请输入学号" autocomplete="off" class="layui-input" value="${obj.no}">
	          <input type="text" name="name"  placeholder="请输入姓名" autocomplete="off" class="layui-input" value="${obj.name}">
	          <button class="layui-btn" type="submit" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
	        </form>
	      </div> 
      
      <table class="layui-table">
        <thead>
<!-- 	private String name;姓名
	private String no;学号
	private String xy;学院
	private String zy;专业
	private String phone;手机号
	private String password;密码 -->
          <tr>
           <th>学号</th>
           <th>姓名</th>
           <th>学院</th>
           <th>专业</th>
           <th>手机号</th>
        </thead>
        <tbody>
        
          <c:forEach items="${pagers.datas}" var="data" varStatus="l">
	                  <tr>
	                    <td>${data.no}</td>
	                    <td>${data.name}</td>
	                    <td>${data.xy}</td>
	                    <td>${data.zy}</td>
	                    <td>${data.phone}</td>
	                </tr>
                  </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/user/findBySql" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					        <pg:param name="name" value="${obj.name}" />
					         <pg:param name="no" value="${obj.no}" />
							<pg:last>  
								共${pagers.total}记录,共${pageNumber}页,  
							</pg:last>  
								当前第${curPage}页 
					        <pg:first>  
					    		<a href="${pageUrl}">首页</a>  
							</pg:first>  
							<pg:prev>  
					    		<a href="${pageUrl}">上一页</a>  
							</pg:prev>  
					       	<pg:pages>  
					        	<c:choose>  
					            	<c:when test="${curPage eq pageNumber}">  
					                	<font color="red">[${pageNumber }]</font>  
					            	</c:when>  
					            	<c:otherwise>  
					               		<a href="${pageUrl}">${pageNumber}</a>  
					            	</c:otherwise>  
					        	</c:choose>  
					    	</pg:pages>
					             
					        <pg:next>  
					    		<a href="${pageUrl}">下一页</a>  
							</pg:next>  
							<pg:last>  
								<c:choose>  
					            	<c:when test="${curPage eq pageNumber}">  
					                	<font color="red">尾页</font>  
					            	</c:when>  
					            	<c:otherwise>  
					               		<a href="${pageUrl}">尾页</a>  
					            	</c:otherwise>  
					        	</c:choose> 
					    		  
							</pg:last>
						</pg:pager>
					</div>
        </div>
      </div>

    </div>
    <script>
	    $(function(){
	      	$("#add").click(function(){
	      		window.location.href =ctx+"/user/add";
	      	});
	      });
      /*删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              window.location.href =ctx+"/user/delete?id="+id;
          });
      }
    </script>
  </body>
</html>