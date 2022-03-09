<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/7
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <link href="css/select.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/jquery.idTabs.min.js"></script>
    <script type="text/javascript" src="js/select-ui.min.js"></script>
    <script type="text/javascript" src="editor/kindeditor.js"></script>
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
<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">类型添加</a></li>
    </ul>
</div>


<form action="BookTypeServlet?method=addType" method="post" >
    <div class="formbody">

        <div id="usual1" class="usual" >

            <div id="tab1" class="tabson"  >

                <ul    class="forminfo">
                    <li><label>类型名称<b>*</b></label><input name="typename" type="text" class="dfinput"   style="width:518px;"/></li>
                    <li><label>类型简介<b>*</b></label><input name="typecontent" type="text" class="dfinput"  style="width:518px;"/></li>
                </ul>
                <input name=""   type="submit" class="loginbtn"    value="添加"  class="loginbtn" />

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
