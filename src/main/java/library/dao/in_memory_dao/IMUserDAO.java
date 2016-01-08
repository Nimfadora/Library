package library.dao.in_memory_dao;

import library.dao.UserDAO;
import library.dao.storage.InMemoryStorage;
import library.model.entity.Book;
import library.model.entity.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IMUserDAO implements UserDAO {
    private static UserDAO dao;

    private IMUserDAO() {
    }

    public static synchronized UserDAO getInstance() {
        if (dao == null)
            dao = new IMUserDAO();
        return dao;
    }

    @Override
    public User findUser(User user) {
        return InMemoryStorage.usersStorage.get(user.getId());
    }

    @Override
    public synchronized Long createUser(User user) {
        Random random = new Random();
        user.setId(random.nextLong());
        InMemoryStorage.usersStorage.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public synchronized boolean updateUser(User user) {
        return InMemoryStorage.usersStorage.replace(user.getId(), user) != null;
    }

    @Override
    public List<Book> getBooks(User user) {
        List<Long> booksId = InMemoryStorage.userToBook.get(user.getId());
        List<Book> books = new LinkedList<>();
        booksId.forEach(id -> {
            books.add(InMemoryStorage.booksStorage.get(id));
        });
        return books;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) InMemoryStorage.usersStorage.values();
    }
}
