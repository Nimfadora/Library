package library.controller;

import library.exception.DateFormatException;
import library.model.dto.UserDTO;
import library.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet({"/signup"})
public class RegistrationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String birthday = request.getParameter("birthday");
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword(password);
        userDTO.setLogin(login);
        userDTO.setName(name);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date birthDate = df.parse(birthday);
            userDTO.setBirthday(birthDate);
        } catch (ParseException e) {
            throw new DateFormatException("Invalid date format. Should be yyyy-MM-dd");
        }
        UserDTO user = new UserDTO();
        user.setId(UserServiceimpl.getInstance().createUser(userDTO));
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/login");
    }
}
