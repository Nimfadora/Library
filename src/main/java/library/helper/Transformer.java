package library.helper;

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
        User user = new User();
        try {
            user.setId(userDTO.getId());
            user.setLogin(userDTO.getLogin());
            user.setPassword(userDTO.getPassword());
            user.setName(userDTO.getName());
            user.setBirthday(userDTO.getBirthday());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return user;
    }

    public static UserDTO transformUser(User user) {
        UserDTO userDTO = new UserDTO();
        try {
            userDTO.setId(user.getId());
            userDTO.setLogin(user.getLogin());
            userDTO.setPassword(user.getPassword());
            userDTO.setName(user.getName());
            userDTO.setBirthday(user.getBirthday());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return userDTO;
    }

    public static Book transformBookDTO(BookDTO bookDTO) {
        List<Long> usersId = new LinkedList<>();
        List<Long> usersDTOId = bookDTO.getActualUsers();
        if (usersDTOId.size() > 0)
            usersDTOId.forEach(usersId::add);
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setCount(bookDTO.getCount());
        book.setActualUsers(usersId);
        return book;
    }

    public static BookDTO transformBook(Book book){
        List<Long> usersDTOId = new LinkedList<>();
        List<Long> usersId = book.getActualUsers();
        if (usersId.size() > 0)
            usersId.forEach(usersDTOId::add);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setCount(book.getCount());
        bookDTO.setActualUsers(usersDTOId);
        return bookDTO;
    }

    public static ReportDTO transformReport(Report report) {
        ReportDTO reportDTO = new ReportDTO();
        reportDTO.setId(report.getId());
        reportDTO.setBook(Transformer.transformBook(report.getBook()));
        reportDTO.setUser(Transformer.transformUser(report.getUser()));
        try {
            reportDTO.setRent(report.getRent());
            reportDTO.setReturnDate(report.getReturnDate());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return reportDTO;
    }

    public static Report transformReportDTO(ReportDTO reportDTO) {
        Report report = new Report();
        report.setId(reportDTO.getId());
        report.setBook(Transformer.transformBookDTO(reportDTO.getBook()));
        report.setUser(Transformer.transformUserDTO(reportDTO.getUser()));
        report.setRent(reportDTO.getRent());
        report.setReturnDate(reportDTO.getReturnDate());
        return report;
    }


}
