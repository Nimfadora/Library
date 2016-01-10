package library.model.dto;

import library.model.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BookDTO {
    private long id;
    private String author;
    private String title;
    private int count;
    private List<Long> actualUsers = new LinkedList<>();

    public BookDTO(){}
    public BookDTO(long id, String author, String title, int count) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.count = count;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Long> getActualUsers() {
        return actualUsers;
    }

    public void setActualUsers(List<Long> actualUsers) {
        this.actualUsers = actualUsers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getCount() {
        return count;
    }


    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                '}';
    }
}
