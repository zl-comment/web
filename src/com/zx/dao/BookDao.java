package com.zx.dao;

import com.zx.beans.Book;
import com.zx.beans.Page;

import java.util.ArrayList;
import java.util.List;

public interface BookDao {
    void addBook(Book book);

    int getBooksCount();

    Book getBookById(int id);

    void updateBook(Book book);

    void deleteBook(int id);

    int searchCount(String name,int State,int booktypeid);

    Page<Book> getByPage(int currentPage , int pageSize);

    Page<Book> searchByPage(String name, int State, int booktypeid, int currentPage, int pageSize);
}
