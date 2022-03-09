package com.zx.servlet;

import com.zx.beans.Admin;
import com.zx.dao.AdminDao;
import com.zx.dao.impl.AdminDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

//解决Servlet只执行doGet或doPost的问题，使得一个Servlet可以执行好多自定义方法；
//在jsp中写action="AdminServlet?method=login"   表示执行login方法

@WebServlet(name = "AdminServlet", urlPatterns = "/AdminServlet")
public class AdminServlet extends BaseServlet {


    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminname=request.getParameter("adminname");
        String password=request.getParameter("password");

        AdminDao adminDao=new AdminDaoImpl();
      Admin  admin=adminDao.login(adminname,password);

        HttpSession session=request.getSession();
    if(admin==null){
        //将错误信息输出给用户
        request.setAttribute("msg","用户名或密码错误");
        request.getRequestDispatcher("login.jsp").forward(request,response);
    //以上两个配套
    }else{
        //想要带参数又想要重定向·
        session.setAttribute("adminname",adminname);
        response.sendRedirect("main.jsp");
    }


    }

    public void addAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String adminname=request.getParameter("adminname");
        String password=request.getParameter("password");
        String phone=request.getParameter("phone");
        String state=request.getParameter("state");

        Admin admin=new Admin(adminname,password,phone,Integer.parseInt(state));


        AdminDao admindao=new AdminDaoImpl();
        admindao.addAdmin(admin);

    }
    public void getAdmins(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AdminDao admindao=new AdminDaoImpl();
       ArrayList<Admin> admins= admindao.getAdmins();

        request.setAttribute("admins",admins);

        request.getRequestDispatcher("/admins.jsp").forward(request,response);
    }



}
