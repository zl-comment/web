<%--
  Created by IntelliJ IDEA.
  User: zhang
  Date: 2022/3/4
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
            $(".click").click(function(){
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function(){
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function(){
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function(){
                $(".tip").fadeOut(100);
            });

        });
    </script>
    <script type="application/javascript">
        function  de() {

            confirm('您确定删除吗？')

        }


    </script>


</head>


<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">数据表</a></li>
        <li><a href="#">基本内容</a></li>
    </ul>
</div>

<div class="rightinfo">

    <div class="tools">
        <form action="BookServlet?method=search" method="post"  >
        <input name="name" type="text" class="dfinput"  value="${name}"  placeholder="请输入图书名称...."   style=" width: 200px"/>



            <select class="select1" name="state">

                <c:if test="${state==0}">
                    <option value="0" selected class="selected">==请选择状态==</option>
                    <option value="1" >上架</option>
                    <option value="2" >下架</option>
                    <option value="3" >热销</option>
                </c:if>

                <c:if test="${state==1}">
                    <option value="0">==请选择状态==</option>
                    <option value="1" selected class="selected">上架</option>
                    <option value="2" >下架</option>
                    <option value="3" >热销</option>
                </c:if>
                <c:if test="${state==2}">
                    <option value="0">==请选择状态==</option>
                    <option value="1" >上架</option>
                    <option value="2" selected class="selected">下架</option>
                    <option value="3" >热销</option>
                </c:if>
                <c:if test="${state==3}">
                    <option value="0">==请选择状态==</option>
                    <option value="1" >上架</option>
                    <option value="2" >下架</option>
                    <option value="3" selected class="selected">热销</option>
                </c:if>
            </select>


                    <select class="select1" name="booktypeid">
                        <option value="0">==请选择类型==</option>
                        <c:forEach items="${bookTypes}"  var="bookType">
                            <c:set var="flag" value="false"> </c:set>

                            <c:if test="${booktypeid==bookType.id}">
                                <c:set var="flag" value="ture"> </c:set>
                                <option value="${bookType.id}" selected class="selected">${bookType.typename}</option>
                            </c:if>

                            <c:if test="${booktypeid!=bookType.id&&flag==false}">
                                <option value="${bookType.id}" >${bookType.typename}</option>
                            </c:if>
                        </c:forEach>
                    </select>





       <input  name=""   class="loginbtn" type="submit"  value="搜索"/>

    <ul class="toolbar">
        <li class="click"><span><img src="images/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/t02.png" /></span>修改</li>
        <li><span><img src="images/t03.png" /></span>删除</li>
        <li><span><img src="images/ico06.png" /></span>搜索</li>
    </ul>
        </form>


        <ul class="toolbar1">
            <li><span><img src="images/t05.png" /></span>设置</li>
        </ul>

    </div>


    <table class="tablelist">
        <thead>
        <tr>
            <th><input name="" type="checkbox" value="" checked="checked"/></th>
            <th>编号<i class="sort"><img src="images/px.gif" /></i></th>
            <th>图书图片</th>
            <th>图书名称</th>
            <th>图书价格</th>
            <th>图书出版社</th>
            <th>图书上架时间</th>
            <th>图书状态</th>
            <th>图书类型</th>
            <th>图书库存</th>
            <th>图书简介</th>
            <th></th>
        </tr>
        </thead>

        <%--每页的图书--%>
        <tbody>
        <c:forEach items="${page.datas}" var="book"  varStatus="num">
            <tr>
                <td><input name="" type="checkbox" value="" /></td>
                <td>${num.index+1}</td>
                <td><img src="${book.imageurl}" alt="" width="100px"/></td>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.press}</td>
                <td>${book.createtime}</td>

                <td>
                    <c:if test="${book.state==1}" >
                        上架
                    </c:if>
                    <c:if test="${book.state==2}" >
                        下架
                    </c:if>
                    <c:if test="${book.state==3}" >
                        热销
                    </c:if>
                </td>
                <td>${book.bookType.typename}</td>
                <td>${book.count}</td>

                <td><a href="BookServlet?method=getBookById&&id=${book.id}" class="tablelink">查看</a>
                    <a href="BookServlet?method=deleteBookById&&id=${book.id}" class="tablelink" onclick="de()"> 删除</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>


    <div class="pagin">
        <div class="message">共<i class="blue">${page.pageCount}</i>条记录，当前显示第&nbsp;<i class="blue">${page.currentPage}</i>页</div>
        <ul class="paginList">


            <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>





            <c:forEach var="num" begin="1"  end="${page.pageCount}" >



                <li class="paginItem"><a href="BookServlet?method=getBooks&&currentPage=${num}&&state=${state}&&booktypeid=${booktypeid}&&name=${name}">${num}</a></li>



            </c:forEach>








           <%-- <li class="paginItem current"><a href="javascript:;">2</a></li>--%>
            <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
        </ul>
    </div>





    <div class="tip">
        <div class="tiptop"><span>提示信息</span><a></a></div>

        <div class="tipinfo">
            <span><img src="images/ticon.png" /></span>
            <div class="tipright">
                <p>是否确认对信息的修改 ?</p>
                <cite >如果是请点击确定按钮 ，否则请点取消。</cite>
            </div>
        </div>

        <div class="tipbtn">
            <input name="" type="button"  class="sure" value="确定" />&nbsp;
            <input name="" type="button"  class="cancel" value="取消" />
        </div>

    </div>




</div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>
