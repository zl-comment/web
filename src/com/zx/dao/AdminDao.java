package com.zx.dao;

import com.zx.beans.Admin;

import java.util.ArrayList;

public interface AdminDao {
    Admin login(String adminname, String password);

    void addAdmin(Admin admin);
    ArrayList<Admin> getAdmins();
}
