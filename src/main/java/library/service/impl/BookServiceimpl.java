package library.service.impl;

import library.dao.BookDAO;
import library.dao.in_memory_dao.IMBookDAO;
import library.helper.PropertiesReader;
import library.model.dto.BookDTO;
import library.model.dto.ReportDTO;
import library.model.dto.UserDTO;
import library.model.entity.Book;
import library.service.BookService;
import library.service.ReportService;
import library.helper.Transformer;

import java.util.LinkedList;
import java.util.List;

public class BookServiceimpl implements BookService {
    private static BookService service;
    private static BookDAO dao = PropertiesReader.getBookDao();
    private static ReportService reportService = ReportServiceimpl.getInstance();
    private BookServiceimpl(){}

    public static synchronized BookService getInstance(){
            if (service == null)
                service = new BookServiceimpl();
            return service;
    }

    @Override
    public BookDTO findBookById(Long id) {
        Book book = dao.findBook(id);
        return Transformer.transformBook(book);
    }

    @Override
    public boolean createBook(BookDTO bookDTO) {
        return dao.createBook(Transformer.transformBookDTO(bookDTO));
    }

    @Override
    public boolean deleteBook(Long id) {
        return dao.deleteBook(id);
    }

    @Override
    public List<BookDTO> getBooks(){
        List<BookDTO> booksDTO = new LinkedList<>();
        dao.getBooks().forEach(entity -> booksDTO.add(Transformer.transformBook(entity)));
        return booksDTO;
    }

    @Override
    public Boolean takeBook(BookDTO book, UserDTO user){
        Boolean res = false;
        if(dao.takeBook(user.getId(), book.getId())) {
            ReportDTO reportDTO = new ReportDTO(book, user);
            res = reportService.createReport(reportDTO);
        }
        return res;
    }

    @Override
    public Boolean returnBook(BookDTO book, UserDTO user){
        Boolean res = false;
        if(dao.returnBook(user.getId(), book.getId())){
            ReportDTO reportDTO = new ReportDTO(book, user);
            res = reportService.updateReport(reportDTO);
        }
        return res;
    }


}
