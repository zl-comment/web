package com.zx.beans;

import java.util.List;

public class BookType {
    private int id;
    private String typename;
    private String typecontent;

    private List<Book>  books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public BookType(int id, String typename, String typecontent) {
        this.id = id;
        this.typename = typename;
        this.typecontent = typecontent;
    }

    public BookType(String typename, String typecontent) {
        this.typename = typename;
        this.typecontent = typecontent;
    }

    public BookType(int id, String typename) {
        this.id = id;
        this.typename = typename;
    }

    public BookType(int id) {
        this.id = id;
    }

    public BookType(String typename) {
        this.typename = typename;
    }





    public BookType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getTypecontent() {
        return typecontent;
    }

    public void setTypecontent(String typecontent) {
        this.typecontent = typecontent;
    }

    @Override
    public String toString() {
        return "BookType{" +
                "id=" + id +
                ", typename='" + typename + '\'' +
                ", typecontent='" + typecontent + '\'' +
                '}';
    }
}

