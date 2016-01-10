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

@WebServlet({"/user/takeBook"})
public class TakeBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        BookDTO book = new BookDTO();
        book.setId(id);
        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        BookServiceimpl.getInstance().takeBook(book, user);
//        request.setAttribute("books", BookServiceimpl.getInstance().getBooks());
//        request.setAttribute("userBooks", UserServiceimpl.getInstance().getBooks(user));
//        request.getRequestDispatcher("/pages/userHomePage.jsp").forward(request, response);
        response.sendRedirect("/user");
    }
}
