package library.controller.admin;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import library.exception.DeleteBookException;
import library.model.dto.BookDTO;
import library.service.impl.BookServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet({"/admin/book/delete"})
public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        BookDTO book = mapper.readValue(request.getReader(), BookDTO.class);
        BookServiceimpl.getInstance().deleteBook(book.getId());
        response.setContentType("application/json; charset=utf-8");
        mapper.writeValue(response.getWriter(), "{}");
    }
}
