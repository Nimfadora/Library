package library;

import library.model.dto.BookDTO;
import library.model.dto.UserDTO;
import library.model.entity.Book;
import library.service.BookService;
import library.service.ReportService;
import library.service.UserService;
import library.service.impl.BookServiceimpl;
import library.service.impl.ReportServiceimpl;
import library.service.impl.UserServiceimpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        BookDTO book = new BookDTO(10, "voland", "horror", 3);
        BookService service = BookServiceimpl.getInstance();
        service.createBook(book);
        System.out.println(service.findBookById(book.getId()));
        service.deleteBook((long) 9);
//        UserDTO userDTO = new UserDTO(1, "Vasya", "theKing", "xxx", "12/13/97");
//        BookDTO bookDTO = new BookDTO(1, "Pushkin", "tales", 5);
//        UserService userService = new UserServiceimpl();
//        BookService bookService = new BookServiceimpl();
//
//        bookService.createBook(bookDTO);
//        userService.createUser(userDTO);
//
//        userService.addBookToUser(userDTO, bookDTO);
//        UserDTO userById = userService.findUser(1);
//        List<BookDTO> books = userService.getAllUserBooks(userDTO);
//        for(BookDTO book : books){
//            System.out.println(book);
//        }
//        System.out.println(userById);


    }
}