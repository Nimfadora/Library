package library.dao.storage;

import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;

import java.util.*;

public class InMemoryStorage{
    public static Map<Long, Report> reportsStorage = new HashMap<>();
    public static Map<Long, User> usersStorage = new HashMap<>();
    public static Map<Long, Book> booksStorage = new HashMap<>();
    public static Map<Long, List<Long>> booksToUser = new HashMap<>();

    private static volatile InMemoryStorage storage;
    private InMemoryStorage(){}
    public static InMemoryStorage getInstance(){
        if(storage == null)
            synchronized (InMemoryStorage.class){
                if(storage == null)
                    storage = new InMemoryStorage();
            }
        return storage;
    }

    public boolean createBook(Book book) {
        booksStorage.put(book.getId(), book);
        return true;
    }

    public Book findBook(long id) {
        return booksStorage.get(id);
    }

    public boolean updateBook(Book book) {
        return booksStorage.replace(book.getId(), book)!=null;
    }

    public boolean deleteBook(Long id) {
        return booksStorage.remove(id)!=null;
    }

    public List<Book> getBooks() {
        return null;
    }


    public List<Book> getBooks(List<Long> indexes) {
        List<Book> users = new LinkedList<>();
        if(indexes != null)
            indexes.forEach((key)->{
                if(key!=null)
                    users.add(booksStorage.get(key));
            });
        return users;
    }

    public boolean createReport(Report report) {
        return reportsStorage.put(report.getId(), report)!=null;
    }

    public Report findReport(long id) {
        return reportsStorage.get(id);
    }

    public boolean updateReport(Report report) {
        return reportsStorage.replace(report.getId(), report)!=null;
    }

    public boolean deleteReport(Report report) {
        return reportsStorage.remove(report.getId())!=null;
    }

    public List<Report> getReports() {
        return null;
    }


    public List<Report> getReports(List<Long> indexes) {
        List<Report> reports = new LinkedList<>();
        if(indexes != null)
            indexes.forEach((key)->{
                if(key!=null)
                    reports.add(reportsStorage.get(key));
            });
        return reports;
    }

    public boolean createUser(User user) {
        return usersStorage.put(user.getId(), user)!= null;
    }

    public User findUser(long id) {
        return (User) usersStorage.get(id);
    }

    public boolean updateUser(User user) {
        return usersStorage.replace(user.getId(), user)!=null;
    }

    public boolean deleteUser(User user) {
        return usersStorage.remove(user.getId())!=null;
    }

    public List<User> getUsers() {
        return null;
    }


    public List<User> getUsers(List<Long> indexes) {
        List<User> users = new LinkedList<>();
        if(indexes != null)
            indexes.forEach((key)->{
                if(key!=null)
                    users.add(usersStorage.get(key));
            });
        return users;
    }

    public List<User> getUsersByBook(long id) {
        List<Long> users = new ArrayList<>();
        booksToUser.forEach((user, books) -> {
            books.forEach(book -> {
                if (book == id) {
                    users.add(user); //may be some mistake if user CAN take more than one book of one type
                }

            });
        });
        return getUsers(users);
    }

    public List<Book> getBooksByUser(long id) {
        return getBooks(booksToUser.get(id));
    }

    public boolean createLink(User user, Book book) {
        List<Long> books = booksToUser.get(user.getId())== null ? new LinkedList<>():booksToUser.get(user.getId());
        books.add(book.getId());


        //якась фигня
//      usersStorage.get(user.getId()).addBook(book);
        User oldUser = usersStorage.get(user.getId());
        oldUser.addBook(book);
        updateUser(oldUser);

        Book oldBook = booksStorage.get(book.getId());
//        oldBook.addUser(user);
        updateBook(oldBook);

        return booksToUser.replace(user.getId(), books)!=null;//вот тут не работает
    }

    public boolean deleteLink(User user, Book book) {
        List<Long> books = booksToUser.get(user.getId())== null ? new LinkedList<>():booksToUser.get(user.getId());
        books.remove(book.getId());

        User oldUser = usersStorage.get(user.getId());
        oldUser.deleteBook(book);
        updateUser(oldUser);

        Book oldBook = booksStorage.get(book.getId());
//        oldBook.deleteUser(user);
        updateBook(oldBook);

        return booksToUser.replace(user.getId(), books)!=null;
    }
}
