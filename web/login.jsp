<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/4
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>欢迎登录后台管理系统</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="js/jquery.js"></script>
    <script src="js/cloud.js" type="text/javascript"></script>

    <script language="javascript">
        $(function() {
            $('.loginbox').css({
                'position' : 'absolute',
                'left' : ($(window).width() - 692) / 2
            });
            $(window).resize(function() {
                $('.loginbox').css({
                    'position' : 'absolute',
                    'left' : ($(window).width() - 692) / 2
                });
            });
        });

    </script>

</head>

<body
        style="background-color:#1c77ac; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



<div id="mainBody">
    <div id="cloud1" class="cloud"></div>
    <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
    <span>欢迎登录后台管理界面平台</span>
    <ul>
        <li><a href="#">回首页</a>
        </li>
        <li><a href="#">帮助</a>
        </li>
        <li><a href="#">关于</a>
        </li>
    </ul>
</div>
<%--AdminServlet?method=login   表示所得数据交给AdminServlet执行login方法--%>
<form action="AdminServlet?method=login" method="post" id="loginform">
    <div class="loginbody">

        <span class="systemlogo"></span>

        <div class="loginbox">

            <ul>
                <li><input name="adminname" type="text" class="loginuser" value="点击输入您的帐号"
                           onclick="JavaScript:this.value=''" id="loginuser"/>
                </li>
                <li><input name="password" type="text" class="loginpwd" value="点击输入您帐号的密码"
                           onclick="JavaScript:this.value=''" id="loginpassword"/>
                </li>
                <%--<li><input name="" type="text" class="loginyzm" value="点击输入右侧的验证码"
                           onclick="JavaScript:this.value=''" id="loginyzm"/>
                </li>--%>
                <li><input name="" type="submit" class="loginbtn" value="登录"
                           class="loginbtn" />  <label><input name=""
                                                              type="checkbox" value="" checked="checked" />记住密码</label><label><a
                        href="#">忘记密码？</a>

                    <span>${msg}</span>

                </label>
                </li>
            </ul>


        </div>

    </div>
</form>



<div class="loginbm">
    版权所有 2016 <a href="#">思软创客</a>
</div>


</body>

</html>
