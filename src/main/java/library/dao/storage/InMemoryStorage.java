package library.dao.storage;

import library.model.entity.Book;
import library.model.entity.Report;
import library.model.entity.User;

import java.util.*;

public class InMemoryStorage{
    public static Map<Long, Report> reportsStorage = new HashMap<>();
    public static Map<Long, User> usersStorage = new HashMap<>();
    public static Map<Long, Book> booksStorage = new HashMap<>();
    public static Map<Long, List<Long>> userToBook = new HashMap<>();
}
