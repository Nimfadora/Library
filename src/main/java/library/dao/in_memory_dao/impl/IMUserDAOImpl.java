package library.dao.in_memory_dao.impl;

import library.dao.UserDAO;
import library.dao.storage.InMemoryStorage;
import library.dao.storage.Storage;
import library.helper.PropertiesReader;
import library.model.entity.Book;
import library.model.entity.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class IMUserDAOImpl implements UserDAO {
    private static UserDAO dao;

    private IMUserDAOImpl() {
    }

    public static synchronized UserDAO getInstance() {
        if (dao == null)
            dao = new IMUserDAOImpl();
        return dao;
    }

    @Override
    public User findUser(User user) {
        return InMemoryStorage.usersStorage.get(user.getId());
    }

    @Override
    public Long createUser(User user) {
        Random random = new Random();
        user.setId(random.nextLong());
        InMemoryStorage.usersStorage.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public boolean updateUser(User user) {
        return InMemoryStorage.usersStorage.replace(user.getId(), user) != null;
    }

    @Override
    public List<Book> getBooks(User user) {
        return null;
    }

    @Override
    public List<User> getUsers() {
        return (List<User>) InMemoryStorage.usersStorage.values();
    }
}
