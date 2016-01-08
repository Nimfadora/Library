package library.dao.db_dao;

import library.dao.UserDAO;
import library.helper.Closer;
import library.helper.ConnectionFactory;
import library.model.entity.Book;
import library.model.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBUserDAO implements UserDAO {
    private static final String CREATE_USER = "INSERT INTO user VALUES(NULL, ?, ?, ?, ?);";
    private static final String FIND_USER = "SELECT * FROM user WHERE login = ? AND password = ?;";
    private static final String UPDATE_USER = "UPDATE user SET name = ?, password = ? WHERE id = ?;";
    private static final String GET_USERS = "SELECT * FROM user;";
    private static final String GET_BOOKS_IN_USER = "SELECT book.id, book.author, book.title FROM book, usertobook, user WHERE user.login = ? AND usertobook.book_id = book.id AND user.id = usertobook.user_id;";

    private static UserDAO dao;
    private DBUserDAO(){}

    public static synchronized UserDAO getInstance(){
        if(dao == null)
            dao = new DBUserDAO();
        return dao;
    }
    private Connection connection;

    @Override
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
            System.out.println("Unable to find user with id = " + user.getId());
        }finally {
            Closer.close(connection);
        }
        return user;
    }

    @Override
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

    @Override
    public boolean updateUser(User user) {
        Boolean res = false;
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getName());
            statement.setString(3, user.getPassword());
            res = statement.execute();
        } catch (SQLException e) {
            System.out.println("Unable to update user with id = " + user.getId());
        }finally {
            Closer.close(connection);
        }
        return res;
    }

    @Override
    public List<Book> getBooks(User user) {
        List<Book> books = new LinkedList<>();
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BOOKS_IN_USER);
            statement.setLong(1, user.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getLong(1));
                book.setAuthor(rs.getString(2));
                book.setTitle(rs.getString(3));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Unable to get books taken by user with id = " + user.getId());
        }finally {
            Closer.close(connection);
        }
        return books;
    }


    @Override
    public List<User> getUsers() {
        List<User> users = new LinkedList<>();
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_USERS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLogin(rs.getString(3));
                user.setBirthday(rs.getDate(5));
            }
        } catch (SQLException e) {
            System.out.println("Unable to get users");
        }finally {
            Closer.close(connection);
        }
        return users;
    }
}
