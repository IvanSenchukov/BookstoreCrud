package ru.senchukov.ivan.bookstore.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import ru.senchukov.ivan.bookstore.dao.BookDao;
import ru.senchukov.ivan.bookstore.model.Book;

import java.util.List;

@Transactional(readOnly = false)
public class BookDaoImpl extends HibernateDaoSupport implements BookDao {

    @Autowired
    public BookDaoImpl(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }

    public BookDaoImpl() {
    }

    public void create(Book book) {
        getHibernateTemplate().save(book);
    }

    public void update(Book book) {
        getHibernateTemplate().update(book);
    }

    public void delete(Book book) {
        getHibernateTemplate().delete(book);
    }

    public Book findById(long id) {
        List list = getHibernateTemplate().find("from Book where id=?", id);
        return (Book) list.get(0);
    }

    public List<Book> readBooksPage(int pageNumber) {
        int fromIndex = (pageNumber - 1) * 10;

        Query query = getSessionFactory().openSession().createQuery("from Book");
        query.setFirstResult(fromIndex);
        query.setMaxResults(10);

        List books = query.list();
        return books;
    }

    public int getPagesCount() {
        Criteria criteria = getSessionFactory().openSession().createCriteria(Book.class);
        criteria.setProjection(Projections.rowCount());
        Long pagesCount = (Long)criteria.uniqueResult();

        return (int) (pagesCount / 10 + 1);
    }

    public List<Book> readBooksByTitle(String title) {
        return (List<Book>) getHibernateTemplate().find("from Book where title like ?", "%"+title+"%");
    }
}
