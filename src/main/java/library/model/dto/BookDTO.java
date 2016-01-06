package library.model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class BookDTO {
    private long id;
    private String author;
    private String title;
    private int count;
    List<UserDTO> users;
    List<ReportDTO> reports;

    public BookDTO(){}
    public BookDTO(long id, String author, String title, int count) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.count = count;
        this.users = new LinkedList<UserDTO>();
        this.reports = new LinkedList<ReportDTO>();
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

    public List<ReportDTO> getReports() {
        return reports;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setReports(List<ReportDTO> reports) {
        this.reports = reports;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", users=" + users +
                ", reports=" + reports +
                '}';
    }
}
