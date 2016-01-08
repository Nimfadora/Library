package library.dao.db_dao;

import library.dao.ReportDAO;
import library.helper.Closer;
import library.helper.ConnectionFactory;
import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DBReportDAO implements ReportDAO {
    private static final String CREATE_REPORT = "INSERT INTO report (book_id, user_id, rent) VALUES(?, ?, CURRENT_DATE);";
    private static final String UPDATE_REPORT = "UPDATE report SET report.return = CURRENT_DATE WHERE user_id = ? AND book_id = ?;";
    private static final String GET_REPORTS = "SELECT report.*, user.name, user.login, book.author, book.title FROM report, user, book WHERE report.book_id = book.id AND report.user_id = user.id;";
    private Connection connection;

    private static ReportDAO dao;
    private DBReportDAO(){}

    public static synchronized ReportDAO getInstance(){
        if(dao == null)
            dao = new DBReportDAO();
        return dao;
    }

    @Override
    public boolean createReport(Report report) {
        boolean res = false;
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(CREATE_REPORT);
            statement.setLong(1, report.getBook().getId());
            statement.setLong(2, report.getUser().getId());
            res = statement.execute();
        } catch (SQLException e) {
            System.out.println("Unable to create report with id = " + report.getId());
        }finally {
            Closer.close(connection);
        }
        return res;
    }


    @Override
    public boolean updateReport(Report report) {
        Boolean res = false;
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_REPORT);
            statement.setLong(1, report.getUser().getId());
            statement.setLong(2, report.getBook().getId());
            res = statement.execute();
        } catch (SQLException e) {
            System.out.println("Cannot update report with id = "+report.getId());
        }finally {
            Closer.close(connection);
        }
        return res;
    }

    @Override
    public List<Report> getReports(){
        List<Report> reports = new LinkedList<>();
        try {
            connection = ConnectionFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_REPORTS);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt(1));
                User user = new User();
                Book book = new Book();
                book.setId(rs.getLong(2));
                user.setId(rs.getLong(3));
                report.setRent(rs.getDate(4));
                report.setReturnDate(rs.getDate(5));
                user.setName(rs.getString(6));
                user.setLogin(rs.getString(7));
                book.setAuthor(rs.getString(8));
                book.setTitle(rs.getString(9));
                report.setBook(book);
                report.setUser(user);
                reports.add(report);
            }
        } catch (SQLException e) {
            System.out.println("Cannot find reports");
        }finally {
            Closer.close(connection);
        }
        return reports;
    }

}
