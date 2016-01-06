package library.dao;

import library.model.entity.Book;
import library.model.entity.User;

import java.util.List;
import java.util.Map;

public interface UserToBook {
    public List<User> getUsersByBook(long id);
    public List<Book> getBooksByUser(long id);

    public boolean createLink(User user, Book book);

    public boolean deleteLink(User user, Book book);
}
