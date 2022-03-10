package com.zx.dao.impl;

import com.zx.beans.Book;
import com.zx.beans.BookType;
import com.zx.beans.Page;
import com.zx.dao.BookDao;
import com.zx.dao.BookTypeDao;
import com.zx.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {

    @Override
    public void addBook(Book book) {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "insert into t_book values (null,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setDouble(2, book.getPrice());
            ps.setString(3, book.getPress());

            java.util.Date createtime = book.getCreatetime();  //book提供的util的date
            //转换为  sql的date
            java.sql.Date date = new java.sql.Date(createtime.getTime());
            ps.setDate(4, date);
            ps.setString(5, book.getImageurl());
            ps.setInt(6, book.getState());
            ps.setInt(7, book.getCount());
            ps.setString(8, book.getBookinfo());
            ps.setInt(9, book.getBookType().getId());


            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.close(connection);

    }

    @Override
    public int getBooksCount() {

        Connection connection = null;
        try {

            connection = JDBCUtil.getConnection();
            String sql = "select   COUNT(id)  num from t_book ";
            PreparedStatement ps = connection.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){

                return  resultSet.getInt("num");

            }




        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }

        return 0;
    }

    @Override
    public Book getBookById(int id) {

        Connection connection = null;
        try {

            connection = JDBCUtil.getConnection();
            String sql = "SELECT b.id ,b.`name`,b.price,b.press,b.createtime,b.imageurl,b.state,b.count,b.bookinfo,b.tid,t.typename FROM t_book b,t_booktype t where t.id=b.tid and b.id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                String press = resultSet.getString("press");
                Date createtime = resultSet.getDate("createtime"); //数据库提供的sql的date

                java.util.Date date = new java.util.Date(createtime.getTime());
                String imageurl = resultSet.getString("imageurl");
                int state = resultSet.getInt("state");
                int count = resultSet.getInt("count");
                String bookinfo = resultSet.getString("bookinfo");
                String typename = resultSet.getString("typename");
                int tid = resultSet.getInt("tid");

                Book book = new Book(id, name, price, press, createtime, imageurl, state, count, bookinfo);
                book.setBookType(new BookType(tid, typename));

                return book;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }
        return null;
    }

    @Override
    public void updateBook(Book book) {

        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "update t_book set name=?,price=?,press=?,createtime=?,imageurl=?,state=?,count=?,bookinfo=?,tid=? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, book.getName());
            ps.setDouble(2, book.getPrice());
            ps.setString(3, book.getPress());

            java.util.Date createtime = book.getCreatetime();  //book提供的util的date
            //转换为  sql的date
            java.sql.Date date = new java.sql.Date(createtime.getTime());
            ps.setDate(4, date);
            ps.setString(5, book.getImageurl());
            ps.setInt(6, book.getState());
            ps.setInt(7, book.getCount());
            ps.setString(8, book.getBookinfo());
            ps.setInt(9, book.getBookType().getId());
            ps.setInt(10, book.getId());


            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.close(connection);

    }

    @Override
    public void deleteBook(int id) {

        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            String sql = "delete from t_book where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.close(connection);

    }

    @Override
    public int searchCount(String name, int State, int booktypeid) {
        Connection connection = null;
        List<Book> books = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            StringBuffer sql = new StringBuffer("SELECT count(id) num from t_book where 1=1");
            if (!"".equals(name)) {
                sql.append(" and name like '%" + name + "%' ");
            }
            if (State != 0) {
                sql.append(" and state =" + State);
            }
            if (booktypeid != 0) {
                sql.append(" and tid =" + booktypeid);
            }




            PreparedStatement ps = connection.prepareStatement(sql.toString());

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                return resultSet.getInt("num");

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }
        return 0;
    }

    @Override
    public Page<Book> getByPage(int currentPage, int pageSize) {
        ArrayList<Book> books = new ArrayList<>();
        Page<Book> page = new Page<>();
        Connection connection = null;
        try {

            connection = JDBCUtil.getConnection();
            String sql = "select  *from t_book  LIMIT ?,?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                String press = resultSet.getString("press");
                Date createtime = resultSet.getDate("createtime"); //数据库提供的sql的date

                java.util.Date date = new java.util.Date(createtime.getTime());
                String imageurl = resultSet.getString("imageurl");
                int state = resultSet.getInt("state");
                int count = resultSet.getInt("count");
                String bookinfo = resultSet.getString("bookinfo");
                int tid = resultSet.getInt("tid");
                BookTypeDao bookTypeDao = new BookTypeDaoImpl();


                Book book = new Book(id, name, price, press, createtime, imageurl, state, count, bookinfo);
                book.setBookType(bookTypeDao.getTypeById(tid));

                books.add(book);
            }

            page.setDatas(books);
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            int dataCount = this.getBooksCount();


            page.setDataCount(dataCount);
            page.setPageCount(dataCount % pageSize == 0 ? (dataCount / pageSize) : ((dataCount / pageSize) + 1));

            return page;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }

        return null;
    }

    @Override
    public Page<Book> searchByPage(String name, int State, int booktypeid, int currentPage, int pageSize) {
        Connection connection = null;
        Page<Book> page=new Page<>();
        List<Book> books = new ArrayList<>();

        try {
            connection = JDBCUtil.getConnection();
            StringBuffer sql = new StringBuffer("SELECT * from t_book where 1=1");
            if (!"".equals(name)) {
                sql.append(" and name like '%" + name + "%' ");
            }
            if (State != 0) {
                sql.append(" and state =" + State);
            }
            if (booktypeid != 0) {
                sql.append(" and tid =" + booktypeid);
            }
              sql.append(" limit ?,?");





            PreparedStatement ps = connection.prepareStatement(sql.toString());

            ps.setInt(1, (currentPage - 1) * pageSize);
            ps.setInt(2, pageSize);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String realname = resultSet.getString("name");
                Double price = resultSet.getDouble("price");
                String press = resultSet.getString("press");
                Date createtime = resultSet.getDate("createtime"); //数据库提供的sql的date

                java.util.Date date = new java.util.Date(createtime.getTime());
                String imageurl = resultSet.getString("imageurl");
                int state = resultSet.getInt("state");
                int count = resultSet.getInt("count");
                String bookinfo = resultSet.getString("bookinfo");
                int tid = resultSet.getInt("tid");


                BookTypeDao bookTypeDao = new BookTypeDaoImpl();


                Book book = new Book(id, realname, price, press, createtime, imageurl, state, count, bookinfo);
                book.setBookType(bookTypeDao.getTypeById(tid));

                books.add(book);
            }

            page.setDatas(books);
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            int dataCount = this.searchCount(name,State,booktypeid);


            page.setDataCount(dataCount);



            page.setPageCount(dataCount%pageSize == 0 ? (dataCount / pageSize) : ((dataCount / pageSize) + 1));

            return page;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.close(connection);
        }
       return null;

    }
}
