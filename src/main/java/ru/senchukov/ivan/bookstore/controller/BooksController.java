package ru.senchukov.ivan.bookstore.controller;

import net.sf.json.JSONArray;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.senchukov.ivan.bookstore.bo.BookBO;
import ru.senchukov.ivan.bookstore.model.Book;

import java.util.List;

@Controller
public class BooksController {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView printWelcome(ModelMap model) {
        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        List<Book> books = bookBo.readBooksPage(1);
        int pagesCount = bookBo.getPagesCount();
        int currentPage = 1;

        ModelAndView modelAndView = new ModelAndView("booksTable");
        modelAndView.addObject("books", JSONArray.fromObject(books));
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("currentPage", currentPage);

        return modelAndView;
    }

    @RequestMapping(value = "/showPage/{pageNumber:.+}", method = RequestMethod.GET)
    public ModelAndView showPage(@PathVariable("pageNumber") Integer pageNumber) {

        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        int pagesCount = bookBo.getPagesCount();
        int currentPage = pageNumber;
        List<Book> books = bookBo.readBooksPage(currentPage);

        ModelAndView modelAndView = new ModelAndView("booksTable");
        modelAndView.addObject("books", JSONArray.fromObject(books));
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("currentPage", currentPage);

        return modelAndView;
    }

    @RequestMapping(value = "/previewBook/{bookId:.+}", method = RequestMethod.GET)
    public ModelAndView previewBook(@PathVariable("bookId") long id) {

        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        Book bookToPreview = bookBo.findById(id);

        ModelAndView modelAndView = new ModelAndView("readBook");
        modelAndView.addObject("book", bookToPreview);

        return modelAndView;
    }

    @RequestMapping(value = "/readTheBook/{bookId:.+}", method = RequestMethod.GET)
    public ModelAndView readTheBook(@PathVariable("bookId") long id) {

        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        bookBo.markAsRead(id);
        Book bookToPreview = bookBo.findById(id);

        ModelAndView modelAndView = new ModelAndView("readBook");
        modelAndView.addObject("book", bookToPreview);

        return modelAndView;
    }

    @RequestMapping(value = "/openUpdateForm/{bookId:.+}", method = RequestMethod.GET)
    public ModelAndView openUpdateForm(@PathVariable("bookId") long id, Model model) {

        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        Book bookToPreview = bookBo.findById(id);

        model.addAttribute("bookForm", bookToPreview);

        ModelAndView modelAndView = new ModelAndView("updateBook");
        modelAndView.addObject("book", bookToPreview);

        return modelAndView;
    }

    @RequestMapping(value="/updateBook", method = RequestMethod.POST)
    public ModelAndView updateBook(@ModelAttribute("bookForm") Book book) {

        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        bookBo.update(book);

        Book bookToPreview = bookBo.findById(book.getId());

        ModelAndView modelAndView = new ModelAndView("readBook");
        modelAndView.addObject("book", bookToPreview);

        return modelAndView;
    }

    @RequestMapping(value = "/openCreateForm", method = RequestMethod.GET)
    public String openUpdateForm(Model model) {

        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        Book book = new Book();
        model.addAttribute("bookForm", book);

        return "createBook";
    }

    @RequestMapping(value="/createBook", method = RequestMethod.POST)
    public ModelAndView createBook(@ModelAttribute("bookForm") Book book) {

        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        bookBo.create(book);

        List<Book> books = bookBo.readBooksPage(1);
        int pagesCount = bookBo.getPagesCount();
        int currentPage = 1;

        ModelAndView modelAndView = new ModelAndView("booksTable");
        modelAndView.addObject("books", JSONArray.fromObject(books));
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("currentPage", currentPage);

        return modelAndView;
    }

    @RequestMapping(value = "/deleteBook/{bookId:.+}", method = RequestMethod.GET)
    public ModelAndView deleteBook(@PathVariable("bookId") long id) {

        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        Book bookToDelete = bookBo.findById(id);
        bookBo.delete(bookToDelete);

        List<Book> books = bookBo.readBooksPage(1);
        int pagesCount = bookBo.getPagesCount();
        int currentPage = 1;

        ModelAndView modelAndView = new ModelAndView("booksTable");
        modelAndView.addObject("books", JSONArray.fromObject(books));
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("currentPage", currentPage);

        return modelAndView;
    }

    @RequestMapping(value = "/filterByTitle", method = RequestMethod.GET)
    public ModelAndView printWelcome(@RequestParam("title") String title) {
        BookBO bookBo = (BookBO)applicationContext.getBean("bookBo");

        int pagesCount;
        int currentPage;

        List<Book> books = bookBo.readBooksByTitle(title);
        if(books.size() > 0) {
            pagesCount = 1;
            currentPage = 1;
        } else {
            books = bookBo.readBooksPage(1);
            pagesCount = bookBo.getPagesCount();
            currentPage = 1;
        }


        ModelAndView modelAndView = new ModelAndView("booksTable");
        modelAndView.addObject("books", JSONArray.fromObject(books));
        modelAndView.addObject("pagesCount", pagesCount);
        modelAndView.addObject("currentPage", currentPage);

        return modelAndView;
    }
}