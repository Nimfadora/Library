package library.transform;

import library.model.dto.BookDTO;
import library.model.dto.ReportDTO;
import library.model.dto.UserDTO;
import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;

import java.util.LinkedList;
import java.util.List;

public class Transformer {
    public static User transformUserDTO(UserDTO userDTO) {
        List<Book> books = new LinkedList<>();
        try{
            List<BookDTO> booksDTO = userDTO.getBooks();
            if (booksDTO.size() > 0)
                booksDTO.forEach(bookDTO -> {
                    books.add(transformBookDTO(bookDTO));
                });
        }catch (NullPointerException e){
//            System.out.println("Book list is empty");
        }
        User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getLogin(), userDTO.getPassword(), userDTO.getBirthday());
        user.setBooks(books);
        return user;
    }

    public static UserDTO transformUser(User user) {
        List<BookDTO> booksDTO = new LinkedList<>();
        UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getLogin(), user.getPassword(), user.getBirthday());
        try{
            List<Book> books = user.getBooks();
            if (books.size() > 0) {
                books.forEach(book -> {
                    booksDTO.add(transformBook(book));
                });
            }
            userDTO.setBooks(booksDTO);
        }catch (NullPointerException e){
//            System.out.println("Book list is empty");
        }
        return userDTO;
    }

    public static Book transformBookDTO(BookDTO bookDTO) {
        List<User> users = new LinkedList<>();
        try{
            List<UserDTO> usersDTO = bookDTO.getUsers();
            if (usersDTO.size() > 0)
                usersDTO.forEach(userDTO -> {
                    users.add(transformUserDTO(userDTO));
                });
        }catch (NullPointerException e){
//            System.out.println("User list is empty");
        }
        List<Report> reports = new LinkedList<>();
        try {
            if (bookDTO.getReports() != null) {
                List<ReportDTO> reportsDTO = bookDTO.getReports();
                if (reportsDTO.size() > 0)
                    reportsDTO.forEach(reportDTO -> {
                        reports.add(transformReportDTO(reportDTO));
                    });
            }
        }catch (NullPointerException e){
//            System.out.println("Report list s empty");
        }
        Book book = new Book(bookDTO.getId(), bookDTO.getAuthor(), bookDTO.getTitle(), bookDTO.getCount());
        book.setUsers(users);
        book.setReports(reports);
        return book;
    }

    public static BookDTO transformBook(Book book){
        List<UserDTO> usersDTO = new LinkedList<>();
        try {
            List<User> users = book.getUsers();
            if (users.size() > 0)
                users.forEach(user -> {
                    usersDTO.add(transformUser(user));
                });
        }catch (NullPointerException e){
//            System.out.println("User list is empty");
        }
        List<ReportDTO> reportsDTO = new LinkedList<>();
        try {
            List<Report> reports = book.getReports();
            if (reports.size() > 0)
                reports.forEach(report -> {
                    reportsDTO.add(transformReport(report));
                });
        }catch (NullPointerException e){
//            System.out.println("Report list is empty");
        }
        BookDTO bookDTO = new BookDTO(book.getId(), book.getAuthor(), book.getTitle(), book.getCount());
        bookDTO.setReports(reportsDTO);
        bookDTO.setUsers(usersDTO);
        return bookDTO;
    }

    public static ReportDTO transformReport(Report report) {
        return new ReportDTO(report.getId(), transformBook(report.getBook()), transformUser(report.getUser()),report.getRent(), report.getReturnDate());
    }

    public static Report transformReportDTO(ReportDTO reportDTO) {
        return new Report(reportDTO.getId(), transformBookDTO(reportDTO.getBook()), transformUserDTO(reportDTO.getUser()), reportDTO.getRent(), reportDTO.getReturnDate());
    }


}
