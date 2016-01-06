package library.dao.in_memory_dao.impl;

import library.dao.UserDAO;
import library.dao.storage.Storage;
import library.helper.PropertiesReader;
import library.model.entity.Book;
import library.model.entity.User;

import java.util.LinkedList;
import java.util.List;

public class IMUserDAOImpl implements UserDAO {
    private static Storage usersStorage = PropertiesReader.isInMemory();
    private static UserDAO dao;
    private IMUserDAOImpl(){}

    public static synchronized UserDAO getInstance(){
        if(dao == null)
            dao = new IMUserDAOImpl();
        return dao;
    }

    @Override
    public boolean createUser(User user) {
        return false; //usersStorage.createUser(user);
    }

    @Override
    public User findUser(long id) {
        return null; //(User) usersStorage.findUser(id);
    }

    @Override
    public boolean updateUser(User user) {
        return usersStorage.updateUser(user);
    }

    @Override
    public boolean deleteUser(User user) {
        return usersStorage.deleteUser(user);
    }

    @Override
    public boolean addBook(long userId, Book book) {
//        User user = usersStorage.findUser(userId);
//        user.addBook(book);
        return false; //usersStorage.updateUser(user);
    }

    @Override
    public boolean deleteBook(long userId, Book book) {
//        User user = usersStorage.findUser(userId);
//        user.deleteBook(book);
        return false; //usersStorage.updateUser(user);
    }

    @Override
    public List<User> getUsers(List<Long> indexes) {
        List<User> users = new LinkedList<>();
//        if(indexes != null)
//            indexes.forEach((key)->{
//                if(key!=null)
//                    users.add(usersStorage.findUser(key));
//            });
        return users;
    }

}
