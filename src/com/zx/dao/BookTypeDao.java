package com.zx.dao;

import com.zx.beans.Book;
import com.zx.beans.BookType;

import java.util.ArrayList;

public interface BookTypeDao {
    void addType(BookType bookType);

    ArrayList<BookType>  getTypes();
          BookType getTypeById(int id);




}
