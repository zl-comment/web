<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/4
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(function(){
            //导航切换
            $(".menuson li").click(function(){
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function(){
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if($ul.is(':visible')){
                    $(this).next('ul').slideUp();
                }else{
                    $(this).next('ul').slideDown();
                }
            });
        })
    </script>


</head>

<body style="background:#f0f9fd;">
<div class="lefttop"><span></span>通讯录</div>

<dl class="leftmenu">

    <dd>
        <div class="title">
            <span><img src="images/leftico01.png" /></span>管理员中心
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="index.jsp" target="rightFrame">首页模版</a><i></i></li>
            <li class="active"><cite></cite><a href="AdminServlet?method=getAdmins" target="rightFrame">管理员列表</a><i></i></li>
            <li><cite></cite><a href="filelist.jsp" target="rightFrame">信息管理</a><i></i></li>
            <li><cite></cite><a href="addadmin.jsp" target="rightFrame">管理员添加</a><i></i></li>
            <li><cite></cite><a href="error.jsp" target="rightFrame">404页面</a><i></i></li>
        </ul>
    </dd>


    <dd>
        <div class="title">
            <span><img src="images/leftico02.png" /></span>图书类型
        </div>
        <ul class="menuson">
            <li><cite></cite><a href="addType.jsp" target="rightFrame">类型添加</a><i></i></li>
            <li><cite></cite><a href="BookTypeServlet?method=getTypes" target="rightFrame">类型列表</a><i></i></li>
        </ul>
    </dd>


    <dd><div class="title"><span><img src="images/leftico03.png" /></span>图书中心</div>
        <ul class="menuson">
            <li><cite></cite><a href="BookServlet?method=toAddBook" target="rightFrame">图书上架</a><i></i></li>
            <li><cite></cite><a href="BookServlet?method=getBooks" target="rightFrame">图书列表</a><i></i></li>

        </ul>
    </dd>


    <dd><div class="title"><span><img src="images/leftico04.png" /></span>日期管理</div>
        <ul class="menuson">
            <li><cite></cite><a href="#" target="rightFrame">自定义</a><i></i></li>
            <li><cite></cite><a href="#" target="rightFrame">常用资料</a><i></i></li>
            <li><cite></cite><a href="#" target="rightFrame">信息列表</a><i></i></li>
            <li><cite></cite><a href="#" target="rightFrame">其他</a><i></i></li>
        </ul>

    </dd>

</dl>

</body>
</html>
