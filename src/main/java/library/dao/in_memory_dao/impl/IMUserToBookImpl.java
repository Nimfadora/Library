package library.dao.in_memory_dao.impl;

import library.dao.BookDAO;
import library.dao.UserDAO;
import library.dao.UserToBook;
import library.model.entity.Book;
import library.model.entity.User;

import java.util.*;

public class IMUserToBookImpl implements UserToBook {
    private static Map<Long, List<Long>> booksToUser = new HashMap<>();
    @Override
    public List<User> getUsersByBook(long id) {
        List<Long> users = new ArrayList<>();
        booksToUser.forEach((user, books) -> {
            books.forEach(book -> {
                if (book == id) {
                    users.add(user); //may be some mistake if user CAN take more than one book of one type
                }

            });
        });
        UserDAO userDAO = IMUserDAOImpl.getInstance();
        return userDAO.getUsers(users);
    }

    @Override
    public List<Book> getBooksByUser(long id) {
//        BookDAO bookDAO = DBBookDAOImpl.getInstance();
//        return bookDAO.getBooks(booksToUser.get(id));
        return null;
    }

    @Override
    public boolean createLink(User user, Book book) {
        List<Long> books = booksToUser.get(user.getId())== null ? new LinkedList<>():booksToUser.get(user.getId());
        books.add(book.getId());

        UserDAO ud = IMUserDAOImpl.getInstance();
        ud.addBook(user.getId(), book);
        BookDAO bd = IMBookDAOImpl.getInstance();
//        bd.addUser(book.getId(), user);

        return booksToUser.replace(user.getId(), books)!=null;//вот тут не работает
    }

    @Override
    public boolean deleteLink(User user, Book book) {
        List<Long> books = booksToUser.get(user.getId())== null ? new LinkedList<>():booksToUser.get(user.getId());
        books.remove(book.getId());

        UserDAO ud = IMUserDAOImpl.getInstance();
        ud.deleteBook(user.getId(), book);
        BookDAO bd = IMBookDAOImpl.getInstance();
//        bd.deleteUser(book.getId(), user);

        return booksToUser.replace(user.getId(), books)!=null;
    }
}
