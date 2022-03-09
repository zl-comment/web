package com.zx.dao;

import com.zx.beans.Book;

import java.util.ArrayList;
import java.util.List;

public interface BookDao {
    void addBook(Book book);
    ArrayList<Book> getBooks();
    Book getBookById(int id);

    void updateBook(Book book);

    void deleteBook(int id);

    List<Book> search(String name,int State,int booktypeid);
}
