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
			  <th width="200">申请理由</th>
            <th>状态</th>
             <th>申请时间</th>
           <th>操作</th>
        </thead>
        <tbody>
        
          <c:forEach items="${pagers.datas}" var="data" varStatus="l">
	                  <tr>
	                    <td>${data.user.no}</td>
	                    <td>${data.user.name}</td>
	                    <td>${data.user.xy}</td>
	                    <td>${data.user.zy}</td>
	                    <td>${data.user.phone}</td>
						  <td width="200">${data.reason}</td>
	                     <td>
	                   <c:if test="${data.status == 0}"> 待审核</c:if>
	                   <c:if test="${data.status == 1}"> 审核通过</c:if>
	                   <c:if test="${data.status == 2}"> 审核未通过</c:if>
	                  
	                  </td>
	                    <td><fmt:formatDate value="${data.gmt_create}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	                    
	                    <td class="td-manage">
	                     <c:if test="${data.status == 0}"> 
				              <a title=""  href="${ctx}/apply/ty?id=${data.id}">
				              同意
				              </a>
				              <a title=""  href="${ctx}/apply/bh?id=${data.id}">
				              驳回
				              </a>
	                     
	                     </c:if>
	                     
                          </td>
	                </tr>
                  </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/apply/findBySql" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
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