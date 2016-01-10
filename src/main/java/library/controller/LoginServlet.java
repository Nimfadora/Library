package library.controller;

import library.model.dto.UserDTO;
import library.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(login);
        userDTO.setPassword(password);
        UserDTO user = UserServiceimpl.getInstance().findUser(userDTO);
        if(user != null) {
            request.getSession().setAttribute("user", user);
            if(user.getRole().equals("admin")) {
                response.sendRedirect("/admin");
            }else {
                response.sendRedirect("/user");
            }
        }
    }

}
