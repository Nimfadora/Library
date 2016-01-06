package library.dao;


import library.model.entity.Book;
import library.model.entity.User;

import java.util.List;
import java.util.Set;

public interface BookDAO {

    public boolean createBook(Book book);

    public Book findBook(long id);

    public boolean deleteBook(Long id);

    public boolean takeBook(Long bookId, Long userId );

    public boolean returnBook(Long bookId, Long userId);

    public List<Book> getBooks();
}
