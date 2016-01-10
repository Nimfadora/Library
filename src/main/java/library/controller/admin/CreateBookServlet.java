package library.controller.admin;


import library.model.dto.BookDTO;
import library.service.impl.BookServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin/book/create"})
public class CreateBookServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String count = request.getParameter("count");
        BookDTO bookDTO = new BookDTO(100, author, title, Integer.valueOf(count != null ? count : "0") );
        BookServiceimpl.getInstance().createBook(bookDTO);
        response.sendRedirect("/admin/book");
    }
}
