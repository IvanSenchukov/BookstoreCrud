package ru.senchukov.ivan.bookstore.bo;

import ru.senchukov.ivan.bookstore.model.Book;

import java.util.List;

public interface BookBO {

    void create(Book book);

    void update(Book book);

    void delete(Book book);

    Book findById(long id);

    List<Book> readBooksPage(int pageNumber);

    int getPagesCount();

    void markAsRead(long id);

    List<Book> readBooksByTitle(String title);
}
