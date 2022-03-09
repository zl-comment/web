<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/7
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>图书添加</title>
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
    <script type="text/javascript">
        //实现预览功能
        function preview() {
            //获取文件框的第一个文件,因为文件有可能上传多个文件,咱这里是一个文件
            var file = document.getElementById("img").files[0];
            var file1 = document.getElementById("image");
            file1.style.width = "100px";
            file1.style.height = "100px";
            //获取img对象
            var img = document.getElementById("image");
            //建一条文件流来读取图片
            var reader = new FileReader();
            //根据url将文件添加的流中
            reader.readAsDataURL(file);
            //实现onload接口
            reader.onload = function(e) {
                //获取文件在流中url
                url = reader.result;
                //将url赋值给img的src属性
                img.src = url;
            };
        }
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

<form action="BookServlet?method=updateBook"  method="post" enctype="multipart/form-data">
    <div class="formbody">

        <div id="usual1" class="usual" >

            <div id="tab1" class="tabson"  >

                <input type="hidden"  name="id"  value="${book.id}"/>
                <input type="hidden"  name="url"  value="${book.imageurl}"/>
                <ul  class="forminfo">
                    <li><label>图书名称<b>*</b></label><input name="name" type="text" class="dfinput" value="${book.name}"  style="width:518px;"/></li>
                    <li><label>图书价格<b>*</b></label><input name="price" type="text" class="dfinput" value="${book.price}" style="width:518px;"/></li>
                    <li><label>图书出版社<b>*</b></label><input name="press" type="text" class="dfinput" value="${book.press}"  style="width:518px;"/></li>
                    <li><label>图书上架时间<b>*</b></label><input name="createtime"  type="date" value="${book.createtime}" class="dfinput"  style="width:518px;"/></li>
                    <li><label>图书图片<b>*</b></label><input name="imageurl" type="file" class="dfinput" id="img"  onchange="preview()" style="width:300px;"/><img  id="image"   src="${book.imageurl}"  width="100px"/></li>
                    <li>
                        <label>图书状态<b>*</b></label>
                        <div class="vocation">
                            <select class="select1" name="state">
                                <c:if test="${book.state==1}">
                                    <option value="1" class="selected">上架</option>
                                    <option value="2" >下架</option>
                                    <option value="3" >热销</option>
                                </c:if>
                                <c:if test="${book.state==2}">
                                    <option value="1" >上架</option>
                                    <option value="2" class="selected">下架</option>
                                    <option value="3" >热销</option>
                                </c:if>
                                <c:if test="${book.state==3}">
                                    <option value="1" >上架</option>
                                    <option value="2" >下架</option>
                                    <option value="3" class="selected">热销</option>
                                </c:if>

                            </select>
                        </div>
                    </li>
                    <li>
                        <label>图书类型<b>*</b></label>
                        <div class="vocation">
                            <select class="select1" name="booktypestate">
                                <option value=""></option>

                                <c:forEach items="${bookTypes}"  var="bookType">
                                    <c:set var="flag" value="false"> </c:set>

                                    <c:if test="${book.bookType.id==bookType.id}">
                                        <c:set var="flag" value="ture"> </c:set>
                                            <option value="${bookType.id}" selected class="selected">${bookType.typename}</option>
                                    </c:if>

                                    <c:if test="${book.bookType.id!=bookType.id&&flag==false}">
                                        <option value="${bookType.id}" >${bookType.typename}</option>
                                    </c:if>
                                </c:forEach>

                            </select>
                        </div>
                    </li>



                    <li><label>图书库存<b>*</b></label><input name="count" type="text"   value="${book.count}" class="dfinput"   style="width:518px;"/></li>

                    <li><label>图书简介<b>*</b></label>


                        <textarea id="content7" name="bookinfo" style="width:700px;height:250px;visibility:hidden;">${book.bookinfo}</textarea>

                    </li>

                </ul>
                <input name=""   type="submit" class="loginbtn"   value="修改" class="loginbtn" />
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
