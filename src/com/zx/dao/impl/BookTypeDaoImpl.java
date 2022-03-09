package com.zx.dao.impl;



import com.zx.beans.BookType;
import com.zx.dao.BookTypeDao;
import com.zx.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookTypeDaoImpl implements BookTypeDao {
    @Override
    public void addType(BookType bookType) {
        Connection connection=null;
        try {
            connection= JDBCUtil.getConnection();
            String sql="insert into t_booktype values (null,?,?)";
            PreparedStatement ps= connection.prepareStatement(sql);

            ps.setString(1,bookType.getTypename());  //1代表第一个?
            ps.setString(2,bookType.getTypecontent());  //1代表第一个?

            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.close(connection);

    }

    @Override
    public ArrayList<BookType> getTypes() {
        ArrayList<BookType> bookTypes=new ArrayList<>();
        Connection connection=null;
        try {

            connection= JDBCUtil.getConnection();
            String sql="select * from t_booktype";
            PreparedStatement ps= connection.prepareStatement(sql);

            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String typename=resultSet.getString("typename");
                String typecontent=resultSet.getString("typecontent");


                bookTypes.add(new BookType(id,typename,typecontent));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(connection);
        }
        return bookTypes;
    }
    @Override
    public BookType getTypeById(int id) {
        Connection connection=null;
        try {
            connection= JDBCUtil.getConnection();
            String sql="select *from t_booktype  where id=?";
            PreparedStatement ps= connection.prepareStatement(sql);
            ps.setInt(1,id);

            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){

                String typename=resultSet.getString("typename");
                String typecontent=resultSet.getString("typecontent");

            BookType bookType=new BookType(id,typename,typecontent);

            return bookType;


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(connection);
        }

        return null;
    }




}
