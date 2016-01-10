package library.controller.user;

import library.model.dto.BookDTO;
import library.model.dto.UserDTO;
import library.service.impl.BookServiceimpl;
import library.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/user/returnBook"})
public class ReturnBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        BookDTO book = new BookDTO();
        book.setId(id);
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        BookServiceimpl.getInstance().returnBook(book, user);
        response.sendRedirect("/user");
    }
}
