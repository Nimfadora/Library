package library.model.dto;

import java.util.Date;

public class ReportDTO{
    private long id;
    private BookDTO book;
    private UserDTO user;
    private Date rent;
    private Date returnDate;

    public ReportDTO() {
    }

    public ReportDTO(BookDTO book, UserDTO user) {
        this.book = book;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
        this.book = book;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Date getRent() {
        return rent;
    }

    public void setRent(Date rent) {
        this.rent = rent;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
