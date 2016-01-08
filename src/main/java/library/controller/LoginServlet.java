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

@WebServlet({"/"})
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        System.out.println(login);
        System.out.println(password);
        if (password != null && login != null)
            System.out.println("login ='" + login + '\'' +
                    ", password ='" + password);
        request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
    }

}
