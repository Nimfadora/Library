package library.controller.admin;

import library.model.dto.BookDTO;
import library.service.BookService;
import library.service.impl.BookServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin/book"})
public class BookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", BookServiceimpl.getInstance().getBooks());
        request.getRequestDispatcher("/pages/adminBook.jsp").forward(request, response);
    }
}
