package library.controller;

import library.model.dto.BookDTO;
import library.model.dto.UserDTO;
import library.service.impl.BookServiceimpl;
import library.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet({"/home"})
public class UserHomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", BookServiceimpl.getInstance().getBooks());
        HttpSession session = request.getSession();
        UserDTO user = new UserDTO();
        user.setId((Long)session.getAttribute("id"));
        request.setAttribute("userBooks", UserServiceimpl.getInstance().getBooks(user));
        request.getRequestDispatcher("/pages/userHomePage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");
        String count = request.getParameter("count");
        if (author != null && title != null && count != null)
            System.out.println("author='" + author + '\'' +
                    ", title='" + title + '\'' +
                    ", count=" + count);
        BookDTO bookDTO = new BookDTO(100, author, title, Integer.valueOf(count != null ? count : "0") );
        BookServiceimpl.getInstance().createBook(bookDTO);
        request.setAttribute("books", BookServiceimpl.getInstance().getBooks());
        request.getRequestDispatcher("/pages/userHomePage.jsp").forward(request, response);
    }
}