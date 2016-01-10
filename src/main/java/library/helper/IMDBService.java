package library.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import library.dao.storage.InMemoryStorage;
import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class IMDBService {
    private static Map<Long, Book> booksStorage = InMemoryStorage.booksStorage;
    private static Map<Long, User> userStorage = InMemoryStorage.usersStorage;
    private static Map<Long, Report> reportStorage = InMemoryStorage.reportsStorage;
    private static Map<Long, List<Long>> userToBook = InMemoryStorage.userToBook;

    private static Book b1;
    private static Book b2;
    private static Book b3;
    private static Book b4;
    private static Book b5;
    private static User u1;
    private static User u2;
    private static User u3;
    private static User u4;



    public static void main(String[] args) {
        setUsers();
        setBooks();
        setReports();
        setUserToBook();
        serialize(userStorage, "src/main/resources/users.json");
        serialize(booksStorage, "src/main/resources/books.json");
        serialize(reportStorage, "src/main/resources/reports.json");
        serialize(userToBook, "src/main/resources/userToBook.json");
        System.out.println("serialization successful");
    }
    private static <T> void serialize(Map<Long, T> map, String filename){
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            FileOutputStream out = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(out);
            mapper.writeValue(oos, map);
            oos.close();
            out.close();
            System.out.printf("Serialized Map data is saved in"+filename);
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private static void setBooks(){
        List<Long> actualUsers = new LinkedList<>();
        actualUsers.add(u2.getId());
        actualUsers.add(u3.getId());
        actualUsers.add(u4.getId());
        b1 = new Book(1l, "Azimov", "Old man and the sea", 10, new LinkedList<Long>());
        b2 = new Book(2l, "Pushkin", "Tales", 11,actualUsers);
        b3 = new Book(3l, "Gogol", "Viy", 10, actualUsers);
        b4 = new Book(4l, "Turgenev", "Mumu", 10, new LinkedList<>());
        b5 = new Book(5l, "Tolstoy", "Karenina", 10, new LinkedList<>());

        booksStorage.put(b1.getId(), b1);
        booksStorage.put(b2.getId(), b2);
        booksStorage.put(b3.getId(), b3);
        booksStorage.put(b4.getId(), b4);
        booksStorage.put(b5.getId(), b5);
    }
    private static void setUsers(){
        u1 = new User(1l, "Admin", "admin", "root", new Date(), "admin");
        u2 = new User(2l, "John", "john", "john777", new Date(), "user");
        u3 = new User(3l, "Jane", "jane", "jane777", new Date(), "user");
        u4 = new User(4l, "Nimfadora", "tonks", "nimfa2000", new Date(), "user");
        userStorage.put(u1.getId(), u1);
        userStorage.put(u2.getId(), u2);
        userStorage.put(u3.getId(), u3);
        userStorage.put(u4.getId(), u4);
    }
    private static void setReports(){
        reportStorage.put(1l, new Report(1l, b2, u2, new Date(), null));
        reportStorage.put(2l, new Report(2l, b2, u3, new Date(), null));
        reportStorage.put(3l, new Report(3l, b2, u4, new Date(), null));
        reportStorage.put(4l, new Report(4l, b3, u2, new Date(), null));
        reportStorage.put(5l, new Report(5l, b3, u3, new Date(), null));
        reportStorage.put(6l, new Report(6l, b3, u4, new Date(), null));
        reportStorage.put(7l, new Report(7l, b4, u2, new Date(), new Date()));
        reportStorage.put(8l, new Report(8l, b5, u3, new Date(), new Date()));

    }
    private static void setUserToBook(){
        List<Long> books = new LinkedList<>();
        books.add(b2.getId());
        books.add(b3.getId());
        userToBook.put(u2.getId(), books);
        userToBook.put(u3.getId(), books);
        userToBook.put(u4.getId(), books);
    }
}
