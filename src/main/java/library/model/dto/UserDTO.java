package library.model.dto;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class UserDTO{
    public long id;
    public String name;
    public String login;
    public String password;
    private Date birthday;
    private List<BookDTO> books;

    public UserDTO() {
    }

    public UserDTO(long id, String name, String login, String password, Date birthday) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
        this.books = new LinkedList<BookDTO>();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", login='" + login+
                "', birthday=" + birthday+
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public void addBook(BookDTO book) {
        if(book!=null)
            this.books.add(book);
    }
}
