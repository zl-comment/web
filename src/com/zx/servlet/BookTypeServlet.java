package com.zx.servlet;

import com.zx.beans.BookType;
import com.zx.dao.BookTypeDao;
import com.zx.dao.impl.BookTypeDaoImpl;
import org.omg.CORBA.ARG_IN;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BookTypeServlet", urlPatterns = "/BookTypeServlet")
public class BookTypeServlet extends BaseServlet {

    public void addType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   String typename=request.getParameter("typename");
   String typecontent=request.getParameter("typecontent");

        System.out.println(typename);
        System.out.println(typecontent);
   BookType bookType=new BookType(typename,typecontent);
        System.out.println(bookType);
        BookTypeDao bookTypeDao=new BookTypeDaoImpl();

        bookTypeDao.addType(bookType);

           getTypes(request,response);


    }

    public void getTypes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookTypeDao bookTypeDao=new BookTypeDaoImpl();
     ArrayList<BookType> bookTypes= bookTypeDao.getTypes();


     request.setAttribute("bookTypes",bookTypes);
     request.getRequestDispatcher("/types.jsp").forward(request,response);



    }
}
