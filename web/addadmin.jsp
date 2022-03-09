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
    <title>添加管理员</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>

    <script type="text/javascript">
        KE.show({
            id : 'content7',
            cssPath : './index.css'
        });
    </script>

    <script type="text/javascript">
        $(document).ready(function(e) {
            $(".select1").uedSelect({
                width : 345
            });
            $(".select2").uedSelect({
                width : 167
            });
            $(".select3").uedSelect({
                width : 100
            });
        });
    </script>
</head>

<body >

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">管理员添加</a></li>
    </ul>
</div>

<form action="AdminServlet?method=addAdmin" method="post" >
<div class="formbody">

    <div id="usual1" class="usual" >

        <div id="tab1" class="tabson"  >

            <ul    class="forminfo">
                <li><label>管理员名称<b>*</b></label><input name="adminname" type="text" class="dfinput"   style="width:518px;"/></li>
                <li><label>密码<b>*</b></label><input name="password" type="text" class="dfinput"  style="width:518px;"/></li>
                <li><label>确认密码<b>*</b></label><input name="password1" type="text" class="dfinput"   style="width:518px;"/></li>
                <li><label>联系方式<b>*</b></label><input name="phone" type="text" class="dfinput"   style="width:518px;"/></li>
                <li><label>角色<b>*</b></label>
                <input name="state"   type="radio" value="1"/>超级管理员
                <input name="state"   type="radio" value="2"/>普通管理员
                </li>
            </ul>
            <input name=""   type="submit" class="loginbtn"    value="添加"
                   class="loginbtn" />
        </div>
    </div>
</div>


</form>

    <script type="text/javascript">
        $("#usual1 ul").idTabs();
    </script>

    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
    </script>






</body>

</html>
