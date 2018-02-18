package ru.senchukov.ivan.bookstore.dao;

import ru.senchukov.ivan.bookstore.model.Book;

import java.util.List;

public interface BookDao {

    void create(Book book);

    void update(Book book);

    void delete(Book book);

    Book findById(long id);

    List<Book> readBooksPage(int pageNumber);

    int getPagesCount();

    List<Book> readBooksByTitle(String title);
}
