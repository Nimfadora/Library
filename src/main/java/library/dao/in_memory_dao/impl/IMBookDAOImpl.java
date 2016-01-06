package library.dao.in_memory_dao.impl;

import library.dao.BookDAO;
import library.dao.storage.InMemoryStorage;
import library.dao.storage.Storage;
import library.helper.PropertiesReader;
import library.model.entity.Book;
import library.model.entity.User;

import java.util.*;

public class IMBookDAOImpl implements BookDAO {
    private static volatile BookDAO dao;

    private IMBookDAOImpl() {
    }

    public static synchronized BookDAO getInstance() {
        if (dao == null)
            synchronized (IMBookDAOImpl.class) {
                if (dao == null)
                    dao = new IMBookDAOImpl();
            }
        return dao;
    }

    @Override
    public boolean createBook(Book book) {
        Random random = new Random();
        book.setId(random.nextLong());
        InMemoryStorage.booksStorage.put(book.getId(), book);
        return true;
    }

    @Override
    public Book findBook(long id) {

        return InMemoryStorage.booksStorage.get(id);
    }


    @Override
    public boolean deleteBook(Long id) {
        return false;
    }

    @Override
    public boolean takeBook(Long bookId, Long userId) {

        return false;
    }

    @Override
    public boolean returnBook(Long bookId, Long userId) {
        return false;
    }

    public boolean takeBook(long bookId, User user) {
        Book book = InMemoryStorage.booksStorage.get(bookId);
        List<User> userLst = book.getUsers();
        userLst.add(user);
        book.setUsers(userLst);
        return InMemoryStorage.booksStorage.replace(book.getId(), book) != null;
    }

    public boolean returnBook(long bookId, User user) {
        Book book = InMemoryStorage.booksStorage.get(bookId);
        List<User> userLst = book.getUsers();
        userLst.remove(user);
        return InMemoryStorage.booksStorage.replace(book.getId(), book) != null;
    }

    @Override
    public List<Book> getBooks() {
        return (List<Book>) InMemoryStorage.booksStorage.values();
    }
}
