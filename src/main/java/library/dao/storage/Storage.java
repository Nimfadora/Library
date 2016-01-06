package library.dao.storage;

import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;
import java.util.List;

public interface Storage {
    public boolean createBook(Book book);
    public Book findBook(long id);
    public boolean updateBook(Book book);
    public boolean deleteBook(Long id);
    public List<Book> getBooks();

    public boolean createReport(Report report);
    public Report findReport(long id);
    public boolean updateReport(Report report);
    public boolean deleteReport(Report report);
    public List<Report> getReports();

    public Long createUser(User user);
    public User findUser(User user);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public List<User> getUsers();

    public List<User> getUsersByBook(Long id);
    public List<Book> getBooksByUser(Long id);
    public boolean createLink(Long user, Long book);
    public boolean deleteLink(Long user, Long book);
}
