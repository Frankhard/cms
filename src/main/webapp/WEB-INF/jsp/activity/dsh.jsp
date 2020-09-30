<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="application/json" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, activity-scalable=no" />
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
	        <form class="layui-form layui-col-md12 x-so" method="post" action="${ctx}/activity/dsh">
	        
	           <input type="hidden" name="type" value="2" />
	          <input type="text" name="title"  placeholder="请输入标题" autocomplete="off" class="layui-input" value="${obj.title}">
	          <button class="layui-btn" type="submit" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
	        </form>
	      </div> 
	<!-- private String title;标题
	private String img;封面图
	private String startTime;举办时间
	private String address;地址
	private String budget;申请资金
	private String content;活动内容
	private Integer status;0 新建  1 审核通过 2 审核未通过 -->
      <table class="layui-table">
        <thead>
          <tr>
           <th>社团</th>
           <th>标题</th>
           <th>封面</th>
           <th>举办时间</th>
           <th>地址</th>
           <th>申请资金</th>
		   <th>剩余容量</th>
           <th>状态</th>
           <th>操作</th>
        </thead>
        <tbody>
        
          <c:forEach items="${pagers.datas}" var="data" varStatus="l">
	                  <tr>
	                    <td>${data.team.name}</td>
	                    <td>${data.title}</td>
	                    <td>
	                     <img alt="" style="height: 100px;width: 100px" src="${ctx}${data.img}">
	                    </td>
	                  	<td>${data.startTime}</td>
	                  	<td>${data.address}</td>
	                  	<td>${data.budget}</td>
					  	<td>${data.memberCount}</td>
	                  	<td>
	                   	<c:if test="${data.status == 0}"> 待审核</c:if>
	                   	<c:if test="${data.status == 1}"> 审核通过</c:if>
	                   	<c:if test="${data.status == 2}"> 审核未通过</c:if>
	                  
	                  	</td>
	                    
	                    <td class="td-manage">
	                          <a title="同意"  href="${ctx}/activity/ty?id=${data.id}">
				                同意
				              </a>
				               <a title="驳回"  href="${ctx}/activity/bh?id=${data.id}">
				                驳回
				              </a>
				               <a title="详情"  onclick="x_admin_show('详情','${ctx}/activity/view?id=${data.id}',800,600)" href="javascript:;">
				              详情
				              </a>
                          </td>
	                </tr>
                  </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/activity/dsh" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					        <pg:param name="title" value="${obj.title}" />
					        <pg:param name="type" value="2" />
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
	      		window.location.href =ctx+"/activity/add";
	      	});
	      });
      /*删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              window.location.href =ctx+"/activity/delete?id="+id;
          });
      }
    </script>
  </body>
</html>