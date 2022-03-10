package com.zx.servlet;


import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.zx.beans.Book;
import com.zx.beans.BookType;

import com.zx.beans.Page;
import com.zx.dao.BookDao;
import com.zx.dao.BookTypeDao;

import com.zx.dao.impl.BookDaoImpl;
import com.zx.dao.impl.BookTypeDaoImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@WebServlet(name = "BookServlet", urlPatterns = "/BookServlet")
public class BookServlet extends BaseServlet {

    private ServletConfig servletConfig;

    @Override
    public void init(ServletConfig config) throws ServletException {
        this.servletConfig=config;
        super.init(config);
    }
    //去添加页面，需要提前查询图书类型，并返回给前台页面
    public void toAddBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookTypeDao bookTypeDao=new BookTypeDaoImpl();
        List<BookType> bookTypes=bookTypeDao.getTypes();

        request.setAttribute("bookTypes",bookTypes);

        request.getRequestDispatcher("/addBook.jsp").forward(request,response);
    }

    public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*
        * 获取服务器指定文件夹的真实路径
        * request.getRealPath("文件夹名")
        * */

        String path=request.getRealPath("upload");
        System.out.println(path);

        //创建文件上传对象
        SmartUpload su=new SmartUpload();

        //初始化对象
        su.initialize(servletConfig,request,response);

        //限制文件
        su.setMaxFileSize(10*1024*1024);

        //限制允许类型
        su.setAllowedFilesList("jpg,jpeg,png");

        //限制不允许类型

        String filename="";
        try {

                su.setDeniedFilesList("mp3,mp4,html,jsp,java,exe");
            //将文件上传
            su.upload();
            //保存文件
                su.save(path);
               filename=su.getFiles().getFile(0).getFileName();
            System.out.println(filename);

            } catch (Exception e) {
                e.printStackTrace();
            }

        //限制文件大小，格式


        String name= su.getRequest().getParameter("name");
        String price=su.getRequest().getParameter("price");
        String press= su.getRequest().getParameter("press");
        String createtime= su.getRequest().getParameter("createtime");

        String imageurl="upload/"+filename;
        String state= su.getRequest().getParameter("state");  //上架
        String count= su.getRequest().getParameter("count");//库存
        String bookinfo= su.getRequest().getParameter("bookinfo");  //书记简介
        String booktypestate= su.getRequest().getParameter("booktypestate");  //书的类型


        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;

        try {
            date=sf.parse(createtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(date);
        Book book=new Book(name,Double.parseDouble(price),press,date,imageurl,Integer.parseInt(state),Integer.parseInt(count),bookinfo);
       book.setBookType(new BookType(Integer.parseInt(booktypestate)));
        System.out.println(book);
        BookDao bookDao=new BookDaoImpl();
        bookDao.addBook(book);

        getBooks(request,response);
    }
    public void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPage=request.getParameter("currentPage");
        //换页时再启用查询
        String name=request.getParameter("name");
        String state=request.getParameter("state");
        String booktypeid=request.getParameter("booktypeid");

        if(name!=null||state!=null||booktypeid!=null){   //如果选择项被选中，说明有查询操作
            this.search(request,response);
        }else {


            BookDao bookDao = new BookDaoImpl();
            if (currentPage == null) {
                currentPage = "1";
            }
            Page<Book> page = bookDao.getByPage(Integer.parseInt(currentPage), 5);


            BookTypeDao bookTypeDao = new BookTypeDaoImpl();
            List<BookType> bookTypes = bookTypeDao.getTypes();
            request.setAttribute("bookTypes", bookTypes);
            request.setAttribute("page", page);
            //以下两个是为了给与一开始选项
            request.setAttribute("state", "0");
            request.setAttribute("booktypeid", "0");

            request.getRequestDispatcher("/books.jsp").forward(request, response);
        }

    }

    public void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String id=request.getParameter("id");
     BookDao bookDao=new BookDaoImpl();
     Book book=bookDao.getBookById(Integer.parseInt(id));
        System.out.println(book);

        System.out.println(book.getCreatetime());

        request.setAttribute("book",book);

        BookTypeDao bookTypeDao=new BookTypeDaoImpl();
        List<BookType> bookTypes=bookTypeDao.getTypes();

        request.setAttribute("bookTypes",bookTypes);

        request.getRequestDispatcher("/updateBook.jsp").forward(request,response);

    }

    public void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String path=request.getRealPath("upload");
        System.out.println(path);

        //创建文件上传对象
        SmartUpload su=new SmartUpload();

        //初始化对象
        su.initialize(servletConfig,request,response);

        //限制文件
        su.setMaxFileSize(10*1024*1024);

        //限制允许类型
        su.setAllowedFilesList("jpg,jpeg,png");

        //限制不允许类型

        String filename="";
        try {

            su.setDeniedFilesList("mp3,mp4,html,jsp,java,exe");
            //将文件上传
            su.upload();
            //保存文件
            su.save(path);
            System.out.println(su.getFiles().getSize());
            if(su.getFiles().getSize()==0){
                filename=su.getRequest().getParameter("url");
            }else{
                filename="upload/"+su.getFiles().getFile(0).getFileName();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }


        String id=su.getRequest().getParameter("id");
        String name= su.getRequest().getParameter("name");
        String price=su.getRequest().getParameter("price");
        String press= su.getRequest().getParameter("press");
        String createtime= su.getRequest().getParameter("createtime");

        String imageurl=filename;
        String state= su.getRequest().getParameter("state");  //上架
        String count= su.getRequest().getParameter("count");//库存
        String bookinfo= su.getRequest().getParameter("bookinfo");  //书记简介
        String booktypestate= su.getRequest().getParameter("booktypestate");  //书的类型



        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;

        try {
            date=sf.parse(createtime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(date);
        Book book=new Book(Integer.parseInt(id),name,Double.parseDouble(price),press,date,imageurl,Integer.parseInt(state),Integer.parseInt(count),bookinfo);
        book.setBookType(new BookType(Integer.parseInt(booktypestate)));



        BookDao bookDao=new BookDaoImpl();
        bookDao.updateBook(book);

        getBooks(request,response);

    }
    public void deleteBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");

        BookDao bookDao=new BookDaoImpl();
        bookDao.deleteBook(Integer.parseInt(id));

        getBooks(request,response);


    }
    public void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String currentPage=request.getParameter("currentPage");
    String name=request.getParameter("name");
    String state=request.getParameter("state");
        System.out.println(state);
    String booktypeid=request.getParameter("booktypeid");



    if(currentPage==null){
        currentPage="1";
    }



    BookDao bookDao=new BookDaoImpl();
      Page<Book>  page= bookDao.searchByPage(name,Integer.parseInt(state),Integer.parseInt(booktypeid),Integer.parseInt(currentPage),5);



        BookTypeDao bookTypeDao=new BookTypeDaoImpl();
        List<BookType> bookTypes=bookTypeDao.getTypes();




        //传选项的类型
        request.setAttribute("bookTypes",bookTypes);
        //传每页的数据以及页数等
           request.setAttribute("page",page);

           //第一次搜索后将选项的值放到页面，再被getBooks获得，这样可以固定选项值，也可以分页
        request.setAttribute("name",name);
        request.setAttribute("state",state);
        request.setAttribute("booktypeid",booktypeid);

        request.getRequestDispatcher("/books.jsp").forward(request,response);

    }


}
