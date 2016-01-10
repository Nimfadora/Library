package library.dao.in_memory_dao;

import library.dao.UserDAO;
import library.dao.storage.InMemoryStorage;
import library.exception.LoginAlreadyExistsException;
import library.exception.UserNotFoundException;
import library.model.entity.Book;
import library.model.entity.User;

import java.util.*;

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
    public User findUserById(User user) {
        return InMemoryStorage.usersStorage.get(user.getId());
    }

    @Override
    public User findUser(User sUser) {
        List<User> users = new LinkedList<>(InMemoryStorage.usersStorage.values());
        User userRes = null;
        for (User user : users) {
            if(user.getLogin().equals(sUser.getLogin()) && user.getPassword().equals(sUser.getPassword())) {
                userRes = new User();
                userRes.setId(user.getId());
                userRes.setRole(user.getRole());
                break;
            }
        }
        if(userRes == null)
            throw new UserNotFoundException("Invalid username or password");
        return userRes;
    }

    @Override
    public synchronized Long createUser(User sUser) {
        List<User> users = new LinkedList<>(InMemoryStorage.usersStorage.values());
        User userRes = null;
        for (User user : users) {
            if(user.getLogin().equals(sUser.getLogin())) {
                throw new LoginAlreadyExistsException("This login already exists");
            }
        }
        Random random = new Random();
        sUser.setId(random.nextLong());
        sUser.setRole("user");
        InMemoryStorage.usersStorage.put(sUser.getId(), sUser);
        return sUser.getId();
    }

    @Override
    public synchronized boolean updateUser(User user) {
        return InMemoryStorage.usersStorage.replace(user.getId(), user) != null;
    }

    @Override
    public List<Book> getBooks(User user) {
        List<Book> books = new LinkedList<>();
        if(InMemoryStorage.userToBook.get(user.getId()) != null) {
            List<Long> booksId = new LinkedList<>(InMemoryStorage.userToBook.get(user.getId()));
            booksId.forEach(id -> {
                books.add(InMemoryStorage.booksStorage.get(id));
            });
        }
        return books;
    }

    @Override
    public List<User> getUsers() {
        return new LinkedList<User>(InMemoryStorage.usersStorage.values());
    }
}
