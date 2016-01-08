package library.dao.in_memory_dao;

import library.dao.BookDAO;
import library.dao.storage.InMemoryStorage;
import library.model.entity.Book;

import java.util.*;

public class IMBookDAO implements BookDAO {
    private static volatile BookDAO dao;

    private IMBookDAO() {
    }

    public static synchronized BookDAO getInstance() {
        if (dao == null)
            synchronized (IMBookDAO.class) {
                if (dao == null)
                    dao = new IMBookDAO();
            }
        return dao;
    }

    @Override
    public synchronized boolean createBook(Book book) {
        Random random = new Random();
        book.setId(random.nextLong());
        return InMemoryStorage.booksStorage.put(book.getId(), book)!=null;
    }

    @Override
    public Book findBook(long id) {
        return InMemoryStorage.booksStorage.get(id);
    }


    @Override
    public synchronized boolean deleteBook(Long id) {
        Boolean res = false;
        if(InMemoryStorage.booksStorage.get(id).getActualUsers().size() == 0)
            res = InMemoryStorage.booksStorage.remove(id) != null;
        return res;
    }

    @Override
    public synchronized boolean takeBook(Long bookId, Long userId) {
        List<Long> books = InMemoryStorage.userToBook.get(userId);
        if(books == null)
            books = new LinkedList<>();
        books.add(bookId);
        Book book = InMemoryStorage.booksStorage.get(bookId);
        book.getActualUsers().add(userId);
        InMemoryStorage.booksStorage.replace(bookId, book);
        return InMemoryStorage.userToBook.put(userId, books)!=null;
    }

    @Override
    public boolean returnBook(Long bookId, Long userId) {
        List<Long> books = InMemoryStorage.userToBook.get(userId);
        books.remove(bookId);
        Book book = InMemoryStorage.booksStorage.get(bookId);
        book.getActualUsers().remove(userId);
        InMemoryStorage.booksStorage.replace(bookId, book);
        return InMemoryStorage.userToBook.replace(userId, books) != null;
    }

    @Override
    public List<Book> getBooks() {
        return (List<Book>) InMemoryStorage.booksStorage.values();
    }
}
