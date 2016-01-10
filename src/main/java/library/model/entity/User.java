package library.model.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {
    private long id;
    private String name;
    private String login;
    private String password;
    private Date birthday;
    private String role;

    public User() {
    }

    public User(long id, String name, String login, String password, Date birthday, String role) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", role='" + role + '\'' +
                '}';
    }
}
