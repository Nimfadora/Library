package library.dao;

import library.model.entity.Book;
import library.model.entity.User;

import java.util.List;

public interface UserDAO {

    public User findUserById(User user);

    public User findUser(User user);

    public Long createUser(User user);

    public boolean updateUser(User user);

    public List<Book> getBooks(User user);

    public List<User> getUsers();

}
