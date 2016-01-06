package library.service;

import library.model.dto.BookDTO;
import library.model.dto.UserDTO;

import java.util.List;

public interface BookService {
    public BookDTO findBookById(Long id);

    public boolean createBook(BookDTO book);
    public boolean deleteBook(Long id);
    public Boolean takeBook(BookDTO book, UserDTO user);
    public Boolean returnBook(BookDTO book, UserDTO user);

    public List<BookDTO> getBooks();

}
