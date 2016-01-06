package library.dao.storage;

import library.helper.Closer;
import library.helper.ConnectionFactory;
import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBStorage{

    private static final String CREATE_USER = "INSERT INTO user VALUES(NULL, ?, ?, ?, ?);";
    private static final String FIND_USER = "SELECT * FROM user WHERE login = ? AND password = ?;";
    private static final String UPDATE_USER = "UPDATE user SET VALUES(?, ?, ?, ?, ?) WHERE id = ?;";
    private static final String DELETE_USER = "DELETE FROM user WHERE id = ?;";
    private static final String GET_USERS = "SELECT * FROM user;";

    private static final String GET_BOOKS_IN_USER = "SELECT book.id, book.author, book.title FROM book, usertobook, user WHERE user.login = ? AND usertobook.book_id = book.id AND user.id = usertobook.user_id;";


    private Connection connection;


    public Long createUser(User user) {
        Long id = null;
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setDate(4, new Date(user.getBirthday().getTime()));
            id = (long) statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Unable to create book with id = " + user.getId());
        }finally {
            Closer.close(connection);
        }
        return id;
    }

    public User findUser(User user) {
        User getUser = new User();
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                getUser.setId(rs.getLong(1));
                getUser.setName(rs.getString(2));
                getUser.setLogin(rs.getString(3));
                getUser.setPassword(rs.getString(4));
                getUser.setBirthday(rs.getDate(5));
            }
        } catch (SQLException e) {
            System.out.println("Unable to create book with id = " + user.getId());
        }finally {
            Closer.close(connection);
        }
        return user;
    }

    public boolean updateUser(User user) {
        return false;
    }

    public boolean deleteUser(User user) {
        return false;
    }

    public List<User> getUsers() {
        return null;
    }

    public List<User> getUsersByBook(Long id) {
        return null;
    }

    public List<Book> getBooksByUser(Long id) {
        List<Book> books = new LinkedList<>();
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BOOKS_IN_USER);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getLong(1));
                book.setAuthor(rs.getString(2));
                book.setTitle(rs.getString(3));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Unable to create book with id = " + id);
        }finally {
            Closer.close(connection);
        }
        return books;
    }


}
