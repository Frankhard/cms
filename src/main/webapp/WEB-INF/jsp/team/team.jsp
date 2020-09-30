<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, team-scalable=no" />
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
	        <form class="layui-form layui-col-md12 x-so" method="post" action="${ctx}/team/findBySql">
	        
	           <input type="hidden" name="type" value="2" />
	          <input type="text" name="name"  placeholder="请输入名称" autocomplete="off" class="layui-input" value="${obj.name}">
	          <button class="layui-btn" type="submit" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
	        </form>
	      </div> 
	      <c:if test="${role == 1 }">
      <xblock>
       <button class="layui-btn" id="add"><i class="layui-icon"></i>添加</button>
      </xblock> 
      </c:if>
	<!-- private String img;
	private String type;隶属于
	private String content; 简介
	private String name;社团名称
	private Integer userId;社长id -->
      <table class="layui-table">
        <thead>
          <tr>
           <th>名称</th>
           <th>LOGO</th>
           <th>隶属于</th>
           <th>当前社长</th>
			<th>部长剩余容量</th>
           <th>社团人数</th>
           <th>操作</th>
        </thead>
        <tbody>

		<c:if test="${role == 1 }">
			<c:forEach items="${pagers.datas}" var="data" varStatus="l">
				<tr>
					<td>${data.name}</td>
					<td>
						<img alt="" style="height: 100px;width: 100px" src="${ctx}${data.img}">
					</td>
					<td>${data.type}</td>
					<td>${data.user.name}</td>
					<td>${data.ministerCount}</td>
					<td>${data.num}</td>
						<td class="td-manage">
							<a title="编辑"  href="${ctx}/team/update?id=${data.id}">
								<i class="layui-icon">&#xe642;</i>
							</a>
							<a title="删除" onclick="member_del(this,${data.id})" href="javascript:;">
								<i class="layui-icon">&#xe640;</i>
							</a>
							<a title="社长任命" href="${ctx}/team/rm?id=${data.id}">
								社长任命
							</a>
						</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${role == 2 }">
			<c:forEach items="${pagers.datas}" var="data" varStatus="l">
				<tr>
					<td>${data.name}</td>
					<td>
						<img alt="" style="height: 100px;width: 100px" src="${ctx}${data.img}">
					</td>
					<td>${data.type}</td>
					<td>${data.user.name}</td>
					<td>${data.ministerCount}</td>
					<td>${data.num}</td>
						<td class="td-manage">
							<c:if test="${data.id == teamId}">
								<a title="当前">
									当前
								</a>
							</c:if>
							<c:if test="${data.id != teamId}">
								<a title="切换"  href="${ctx}/team/changeTeamId?teamId=${data.id}">
									切换
								</a>
							</c:if>
							<a title="编辑"  href="${ctx}/team/update?id=${data.id}">
								<i class="layui-icon">&#xe642;</i>
							</a>
							<a title="部长任命" href="${ctx}/team/rm?id=${data.id}">
								部长任命
							</a>
						</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${role == 3 }">
			<c:forEach items="${pagers.datas}" var="data" varStatus="l">
				<tr>
					<td>${data.name}</td>
					<td>
						<img alt="" style="height: 100px;width: 100px" src="${ctx}${data.img}">
					</td>
					<td>${data.type}</td>
					<td>${data.user.name}</td>
					<td>${data.ministerCount}</td>
					<td>${data.num}</td>
						<td class="td-manage">
							<c:if test="${data.id == teamId}">
								<a title="当前">
									当前
								</a>
							</c:if>
							<c:if test="${data.id != teamId}">
								<a title="切换"  href="${ctx}/team/changeTeamId?teamId=${data.id}">
									切换
								</a>
							</c:if>
						</td>

				</tr>
			</c:forEach>
		</c:if>

        </tbody>
      </table>
      <div class="page">
        <div>
          <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/team/findBySql" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
					        <pg:param name="name" value="${obj.name}" />
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
	      		window.location.href =ctx+"/team/add";
	      	});
	      });
      /*删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              window.location.href =ctx+"/team/delete?id="+id;
          });
      }
    </script>
  </body>
</html>