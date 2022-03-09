package com.zx.beans;

import java.util.Date;

public class Book {
    private int id;
    private String name;
    private double price;
    private String press;
    private Date createtime;
    private  String imageurl;//图片路径
    private int state;   //1 上架 2 下架  3 热销 4 广告
    private int count;//库存
    private String bookinfo;

   private BookType bookType;

    public Book() {
    }

    public Book(int id, String name, double price, String press, Date createtime, String imageurl, int state, int count, String bookinfo) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.press = press;
        this.createtime = createtime;
        this.imageurl = imageurl;
        this.state = state;
        this.count = count;
        this.bookinfo = bookinfo;
    }

    public Book(String name, double price, String press, Date createtime, String imageurl, int state, int count, String bookinfo) {
        this.name = name;
        this.price = price;
        this.press = press;
        this.createtime = createtime;
        this.imageurl = imageurl;
        this.state = state;
        this.count = count;
        this.bookinfo = bookinfo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getBookinfo() {
        return bookinfo;
    }

    public void setBookinfo(String bookinfo) {
        this.bookinfo = bookinfo;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", press='" + press + '\'' +
                ", createtime=" + createtime +
                ", imageurl='" + imageurl + '\'' +
                ", state=" + state +
                ", count=" + count +
                ", bookinfo='" + bookinfo + '\'' +
                ", bookType=" + bookType +
                '}';
    }
}


