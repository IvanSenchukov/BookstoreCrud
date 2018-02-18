package ru.senchukov.ivan.bookstore.bo.impl;

import ru.senchukov.ivan.bookstore.bo.BookBO;
import ru.senchukov.ivan.bookstore.dao.BookDao;
import ru.senchukov.ivan.bookstore.model.Book;

import java.util.List;

public class BookBoImpl implements BookBO {
    BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    //todo Implement all this methods
    public void create(Book book) {
        bookDao.create(book);
    }

    public void update(Book book) {
        book.setReadAlready(false);
        bookDao.update(book);
    }

    public void delete(Book book) {
        bookDao.delete(book);
    }

    public Book findById(long id) {
        return bookDao.findById(id);
    }

    /**
     * Вычитывает данные о книгах для конкретной страницы пэйджинга.
     * Если страница пэйджинга больше максимального числа страниц - возвращаются данные о последней странице
     *
     * @param pageNumber
     * @return
     */
    public List<Book> readBooksPage(int pageNumber) {
        int pagesCount = bookDao.getPagesCount();

        if (pageNumber > pagesCount) {
            pageNumber = pagesCount;
        }

        return bookDao.readBooksPage(pageNumber);
    }

    public int getPagesCount(){
        return bookDao.getPagesCount();
    };

    public void markAsRead(long id) {
        Book book = bookDao.findById(id);

        if (book.isReadAlready()) return;

        book.setReadAlready(true);
        bookDao.update(book);
    }

    public List<Book> readBooksByTitle(String title) {
        return bookDao.readBooksByTitle(title);
    }
}
