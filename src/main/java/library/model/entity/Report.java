package library.model.entity;

import java.util.Date;

public class Report {
    private long id;
    private Book book; //book id
    private User user; //user id
    private Date rent;
    private Date returnDate;

    public Report() {
    }

    public Report(long id, Book book, User user, Date rent, Date returnDate) {
        this.id = id;
        this.book = book;
        this.user = user;
        this.rent = rent;
        this.returnDate = returnDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
