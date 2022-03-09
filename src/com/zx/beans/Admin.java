package com.zx.beans;

public class Admin {
    private int id;
    private String adminname;
    private String password;
    private String phone;
    private int state;//1 超级 2普通

    public Admin(int id, String adminname, String password, String phone, int state) {
        this.id = id;
        this.adminname = adminname;
        this.password = password;
        this.phone = phone;
        this.state = state;
    }

    public Admin(String adminname, String password, String phone, int state) {
        this.adminname = adminname;
        this.password = password;
        this.phone = phone;
        this.state = state;
    }

    public Admin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", adminname='" + adminname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", state=" + state +
                '}';
    }
}
