package com.zx.dao.impl;

import com.zx.beans.Admin;
import com.zx.dao.AdminDao;
import com.zx.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminDaoImpl implements AdminDao {


    @Override
    public Admin login(String adminname, String password) {

        Connection connection=null;
        try {
              connection= JDBCUtil.getConnection();
            String sql="select * from t_admin where adminname=? && password=?";
            PreparedStatement ps= connection.prepareStatement(sql);

            ps.setString(1,adminname);
            ps.setString(2,password);
            ResultSet resultSet=ps.executeQuery();


            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String adminName=resultSet.getString("adminname");
                String passWord=resultSet.getString("password");
                String phone=resultSet.getString("phone");
                int state=resultSet.getInt("state");

                Admin admin=new Admin(id,adminname,password,phone,state);

                return admin;
            }


        } catch (SQLException e) {
           e.printStackTrace();
        }
        finally {

            JDBCUtil.close(connection);
        }

        return null;

    }

    @Override
    public void addAdmin(Admin admin) {

        Connection connection=null;
        try {

            connection= JDBCUtil.getConnection();
            String sql="insert into t_admin values (null,?,?,?,?)";
            PreparedStatement ps= connection.prepareStatement(sql);

            ps.setString(1,admin.getAdminname());  //1代表第一个?
            ps.setString(2,admin.getPassword());//2代表第二个?
            ps.setString(3,admin.getPhone());
            ps.setInt(4,admin.getState());


            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        JDBCUtil.close(connection);

    }

    @Override
    public ArrayList<Admin> getAdmins() {
    ArrayList<Admin> admins=new ArrayList<>();
        Connection connection=null;
        try {

            connection= JDBCUtil.getConnection();
            String sql="select * from t_admin";
            PreparedStatement ps= connection.prepareStatement(sql);

            ResultSet resultSet=ps.executeQuery();
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String adminname=resultSet.getString("adminname");
                String password=resultSet.getString("password");
                String phone=resultSet.getString("phone");
                int state= resultSet.getInt("state");

                admins.add(new Admin(id,adminname,password,phone,state));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(connection);
        }

        return admins;
    }

}
