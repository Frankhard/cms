<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, notice-scalable=no" />
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
	        <form class="layui-form layui-col-md12 x-so" method="post" action="${ctx}/news/findBySql">
	        
	           <input type="hidden" name="type" value="2" />
	          <input type="text" name="title"  placeholder="请输入标题" autocomplete="off" class="layui-input" value="${obj.title}">
	          <button class="layui-btn" type="submit" lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
	        </form>
	      </div> 
	      <c:if test="${role != 3 }">
		      <xblock>
		       <button class="layui-btn" id="add"><i class="layui-icon"></i>添加</button>
		      </xblock> 
		      </c:if>
      <table class="layui-table">
        <thead>
          <tr>
           <th>标题</th>
           <th>封面</th>
           <th>发布时间</th>
           <c:if test="${role != 3 }">
           <th>操作</th>
           </c:if>
        </thead>
        <tbody>
        
          <c:forEach items="${pagers.datas}" var="data" varStatus="l">
	                  <tr>
	                    <td>
							<a  href="${ctx}/team/view?id=${data.id}">
							${data.title}
							</a>
						</td>
	                    <td>
	                     <img alt="" style="height: 100px;width: 100px" src="${ctx}${data.img}">
	                    </td>
	                    <td>
	                     <fmt:formatDate value="${data.gmt_create}" type="both"/>
	                    </td>
	                    <c:if test="${role != 3 }">
	                    <td class="td-manage">
				              <a title="编辑"  href="${ctx}/news/update?id=${data.id}">
				                <i class="layui-icon">&#xe642;</i>
				              </a>
				              <a title="删除" onclick="member_del(this,${data.id})" href="javascript:;">
				                <i class="layui-icon">&#xe640;</i>
				              </a>
                          </td>
                          </c:if>
	                </tr>
                  </c:forEach>
        </tbody>
      </table>
      <div class="page">
        <div>
          <div class="pagelist">
		        <!-- 分页开始 -->
					      <pg:pager  url="${ctx}/news/findBySql" maxIndexPages="5" items="${pagers.total}"  maxPageItems="15" export="curPage=pageNumber" >
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
	      		window.location.href =ctx+"/news/add";
	      	});
	      });
      /*删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              window.location.href =ctx+"/news/delete?id="+id;
          });
      }
    </script>
  </body>
</html>