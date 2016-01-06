package library.controller;

import library.model.dto.BookDTO;
import library.service.BookService;
import library.service.impl.BookServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/book"})
public class BookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", BookServiceimpl.getInstance().getBooks());
        request.getRequestDispatcher("/pages/book.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String smth;
        String count = request.getParameter("count");
        if (author != null && title != null && count != null)
            System.out.println("author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    ", count=" + count);
        BookDTO bookDTO = new BookDTO(100, author, title, Integer.valueOf(count != null ? count : null) );
        BookServiceimpl.getInstance().createBook(bookDTO);
        request.setAttribute("books", BookServiceimpl.getInstance().getBooks());
        request.getRequestDispatcher("/pages/book.jsp").forward(request, response);
    }
}
