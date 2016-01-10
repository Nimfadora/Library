package library.dao.storage;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.*;

public class InMemoryStorage {
    public static Map<Long, Report> reportsStorage = new HashMap<>();
    public static Map<Long, User> usersStorage = new HashMap<>();
    public static Map<Long, Book> booksStorage = new HashMap<>();
    public static Map<Long, List<Long>> userToBook = new HashMap<>();
    public static final ObjectMapper MAPPER = new ObjectMapper();
    private static ClassLoader classLoader = InMemoryStorage.class.getClassLoader();

    public static void load() {

        usersStorage = deserialize("users.json", resolveType(User.class));
        booksStorage = deserialize("books.json", resolveType(Book.class));
        reportsStorage = deserialize("reports.json", resolveType(Report.class));
        userToBook = deserialize("userToBook.json", resolveListType(Long.class));
        System.out.println("deserialization successful");
    }

    public static void save() {
        serialize(usersStorage, "users.json");
        serialize(booksStorage, "books.json");
        serialize(reportsStorage, "reports.json");
        serialize(userToBook, "userToBook.json");
        System.out.println("serialization successful");
    }

    private static <T> void serialize(Map<Long, T> map, String filename) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            FileOutputStream out = new FileOutputStream(Paths.get(classLoader.getResource(filename).toURI()).toFile());
            ObjectOutputStream oos = new ObjectOutputStream(out);
            mapper.writeValue(oos, map);
            oos.close();
            out.close();
            System.out.printf("Serialized Map data is saved in" + filename);
        } catch (IOException | URISyntaxException | NullPointerException ioe) {
            ioe.printStackTrace();
        }
    }

    private static <T> Map<Long, T> deserialize(String filename, MapType valueType) {
        Map<Long, T> map = null;
        try {
            FileInputStream in = new FileInputStream(Paths.get(classLoader.getResource(filename).toURI()).toFile());
            ObjectInputStream ois = new ObjectInputStream(in);
            map = MAPPER.readValue(ois, valueType);
            ois.close();
            in.close();
            System.out.println("Deserialized Map data from " + filename);
        } catch (IOException | URISyntaxException | NullPointerException ioe) {
            ioe.printStackTrace();
        }
        return map;
    }

    private static <T> MapType resolveType(Class<T> valueType) {
        TypeFactory typeFactory = MAPPER.getTypeFactory();
        return typeFactory.constructMapType(HashMap.class, Long.class, valueType);
    }


    private static <T> MapType resolveListType(Class<T> valueType) {
        TypeFactory typeFactory = MAPPER.getTypeFactory();
        CollectionLikeType collectionLikeType = typeFactory.constructCollectionLikeType(List.class, valueType);
        return typeFactory.constructMapType(HashMap.class, Long.class, collectionLikeType.getRawClass());
    }
}
