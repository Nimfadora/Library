package library.dao.db_dao;

import library.dao.BookDAO;
import library.helper.Closer;
import library.helper.ConnectionFactory;
import library.model.entity.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBBookDAO implements BookDAO {
    private static BookDAO dao;
    private DBBookDAO(){}

    public static synchronized BookDAO getInstance(){
        if(dao == null)
            dao = new DBBookDAO();
        return dao;
    }
    private Connection connection;
    private static final String CREATE_BOOK = "INSERT INTO book VALUES(NULL, ?, ?, ?);";
    private static final String FIND_BOOK = "SELECT * FROM book WHERE id = ?;";
    private static final String UPDATE_BOOK_COUNT = "UPDATE book SET book.count = book.count - ? WHERE id = ? AND book.count > 0;";
    private static final String DELETE_BOOK = "DELETE FROM book WHERE id = ? AND id NOT IN(SELECT book_id FROM usertobook);";
    private static final String GET_BOOKS = "SELECT * FROM book;";
//    private static final String GET_USERS_WITH_BOOK = "SELECT * FROM usertobook WHERE book_id = ?;";
    private static final String TAKE_BOOK = "INSERT INTO usertobook VALUES (?, ?);";
    private static final String RETURN_BOOK = "DELETE FROM usertobook WHERE user_id = ? AND book_id = ?;";

    @Override
    public boolean createBook(Book book) {
        boolean res = false;
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_BOOK);
            statement.setString(1, book.getAuthor());
            statement.setString(2, book.getTitle());
            statement.setInt(3, book.getCount());
            res = statement.execute();
        } catch (SQLException e) {
            System.out.println("Unable to create book with id = "+book.getId());
        }finally {
            Closer.close(connection);
        }
        return res;
    }

    @Override
    public Book findBook(long id) {
        Book book = new Book();
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_BOOK);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            book.setId(rs.getInt(1));
            book.setAuthor(rs.getString(2));
            book.setTitle(rs.getString(3));
            book.setCount(rs.getInt(4));
        }catch (SQLException e){
            System.out.println("Unable to get book with id = "+id);
        }
        return book;
    }

    @Override
    public boolean deleteBook(Long id) {
        Boolean res = false;
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(DELETE_BOOK);
            statement.setLong(1, id);
            int effectedRows = statement.executeUpdate();
            if(effectedRows > 0){
                res = true;
            }
            connection.commit();
        }catch (SQLException e){
            System.out.println("Unable to delete book with id = "+id);
        }finally {
            Closer.close(connection);
        }
        return res;
    }

    @Override
    public boolean takeBook(Long user_id, Long book_id) {
        Boolean res = false;
        try {
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement condition = connection.prepareStatement(UPDATE_BOOK_COUNT);
            condition.setInt(1, 1);
            condition.setLong(2, book_id);
            int effectedRows = condition.executeUpdate();
            if(effectedRows > 0) {
                PreparedStatement statement = connection.prepareStatement(TAKE_BOOK);
                statement.setLong(1, user_id);
                statement.setLong(2, book_id);
                res = statement.execute();
            }
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Unable to give user with id = " + user_id + " book with id = " + book_id);
        }finally {
            Closer.close(connection);
        }
        return res;
    }

    @Override
    public boolean returnBook(Long user_id, Long book_id) {
        Boolean res = false;
        try{
            connection = ConnectionFactory.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement condition = connection.prepareStatement(UPDATE_BOOK_COUNT);
            condition.setInt(1, -1);
            condition.setLong(2, book_id);
            int effectedRows = condition.executeUpdate();
            if(effectedRows > 0) {
                PreparedStatement statement = connection.prepareStatement(RETURN_BOOK);
                statement.setLong(1, user_id);
                statement.setLong(2, book_id);
                res = statement.execute();
            }
        }catch (SQLException e){
            System.out.println("Unable to return book with id = "+book_id+" from user with id = "+user_id);
        }finally {
            Closer.close(connection);
        }
        return res;
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = new LinkedList<Book>();
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_BOOKS);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Book book = new Book();
                book.setId(rs.getInt(1));
                book.setAuthor(rs.getString(2));
                book.setTitle(rs.getString(3));
                book.setCount(rs.getInt(4));
                books.add(book);
            }
        }catch (SQLException e){
            System.out.println("Unable to get all books");
        }
        return books;
    }
}

//    @Override
//    public Book findBook(long id) {
//        Book book = new Book();
//        try {
//            connection = ConnectionFactory.getConnection();
//            PreparedStatement conditionStatement = connection.prepareStatement(GET_USERS_WITH_BOOK);
//            conditionStatement.setLong(1, book.getId());
//            ResultSet rs = conditionStatement.executeQuery();
//            //if r.size=0
//            PreparedStatement statement = connection.prepareStatement(FIND_BOOK);
//            statement.setLong(1, id);
//            rs = statement.executeQuery();
//            rs.next();
//            book.setId(rs.getInt(1));
//            book.setAuthor(rs.getString(2));
//            book.setTitle(rs.getString(3));
//            book.setCount(rs.getInt(4));
//        } catch (SQLException e) {
//            System.out.println("Cannot find book with id = "+id);
//        }finally {
//            Closer.close(connection);
//        }
//        return book;
//    }
