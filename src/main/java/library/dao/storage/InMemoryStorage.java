package library.dao.storage;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;

import java.io.*;
import java.util.*;

public class InMemoryStorage{
    public static Map<Long, Report> reportsStorage = new HashMap<>();
    public static Map<Long, User> usersStorage = new HashMap<>();
    public static Map<Long, Book> booksStorage = new HashMap<>();
    public static Map<Long, List<Long>> userToBook = new HashMap<>();

    public static void main(String[] args) {
        load();
        List lst = new LinkedList<>(usersStorage.values());
        lst.forEach(item->{
            System.out.println(item.getClass());
        });
    }

    private static void load(){
        usersStorage = deserialize(usersStorage, "src/main/resources/users.json");
        booksStorage = deserialize(booksStorage, "src/main/resources/books.json");
        reportsStorage = deserialize(reportsStorage, "src/main/resources/reports.json");
        userToBook = deserialize(userToBook, "src/main/resources/userToBook.json");
        System.out.println("deserialization successful");
    }
    private static <T> Map<Long, T> deserialize(Map<Long, T> map, String filename){
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            FileInputStream in = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(in);
            map = mapper.readValue(ois, new TypeReference<Map<Long, T>>() {});
            ois.close();
            in.close();
            System.out.println("Deserialized Map data from " + filename);
        }catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
        return map;
    }
}
