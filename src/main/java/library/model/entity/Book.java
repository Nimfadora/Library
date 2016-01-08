package library.model.entity;

import java.util.LinkedList;
import java.util.List;

public class Book {
    private long id;
    private String author;
    private String title;
    private int count;
    private List<Long> actualUsers;

    public Book() {
    }

    public Book(long id, String author, String title, int count) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.count = count;
        this.actualUsers = new LinkedList<>();
    }

    public List<Long> getActualUsers() {
        return actualUsers;
    }

    public void setActualUsers(List<Long> actualUsers) {
        this.actualUsers = actualUsers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                '}';
    }
}
