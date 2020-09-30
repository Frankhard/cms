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
    <script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
    <script src="${ctx}/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/js/xadmin.js"></script>
    
    
</head>
<body>
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo"><a href="">后台管理系统</a></div>
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        <ul class="layui-nav right" lay-filter="">
          <li class="layui-nav-item">
            <a href="javascript:;">${realname}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="${ctx}/login/tuichu">退出</a></dd>
            </dl>
          </li>
        </ul>
        
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
           <c:if test="${role == 1 }">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>管理员管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/manage/findBySql">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>管理员列表</cite>
                        </a>
                    </li >
                </ul>
            </li>
           </c:if>
           
           <!-- 管理员 -->
            <c:if test="${role == 1 or  role == 2}">
              <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>新闻管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/news/findBySql">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>新闻列表</cite>
                        </a>
                    </li >
                </ul>
            </li>

              <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>日志管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/loginLog/findBySql">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>日志列表</cite>
                        </a>
                    </li >
                </ul>
            </li>

               <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>社团管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/team/findBySql">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>社团列表</cite>
                        </a>
                    </li >
                </ul>
            </li>


               <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>活动管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/activity/dsh">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>申请列表</cite>
                        </a>
                    </li >
                     <li>
                        <a _href="${ctx}/activity/ysh">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>已审核列表</cite>
                        </a>
                    </li >
                </ul>
            </li>
             <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>申请退社管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/apply/tss">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>申请退社列表</cite>
                        </a>
                    </li >
                </ul>
            </li>
             <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>申请入社管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/apply/findBySqladmin">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>申请入社列表</cite>
                        </a>
                    </li >
                </ul>
            </li>

            
            </c:if>
            
               <!-- 社团  -->
              <c:if test="${role == 3}">
                  <li>
		                <a href="javascript:;">
		                    <i class="iconfont">&#xe723;</i>
		                    <cite>活动管理</cite>
		                    <i class="iconfont nav_right">&#xe697;</i>
		                </a>
		                <ul class="sub-menu">
		                    <li>
		                        <a _href="${ctx}/activity/findBySql">
		                            <i class="iconfont">&#xe6a7;</i>
		                            <cite>活动列表</cite>
		                        </a>
		                    </li >
		                </ul>
		            </li>
		            
		            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>新闻列表</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/news/findBySql">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>新闻列表</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
             <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>社团列表</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/team/findBySql">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>社团列表</cite>
                        </a>
                    </li >
                </ul>
            </li>
		      
		           <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>社团成员</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/user/findBySql">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>成员列表</cite>
                        </a>
                    </li >
                </ul>
            </li>   
            
                <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>申请入社管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/apply/findBySql">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>申请入社列表</cite>
                        </a>
                    </li >
                </ul>
            </li>
		      
              </c:if>

        
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                  <iframe src='${ctx}/login/wel' frameborder="0" scrolling="yes" class="x-iframe"></iframe>

            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer">
        <div class="copyright">Copyright ©2019 All Rights Reserved</div>  
    </div>
    <!-- 底部结束 -->
    
</body>
</html>