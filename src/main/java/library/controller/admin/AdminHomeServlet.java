package library.controller.admin;

import library.service.impl.BookServiceimpl;
import library.service.impl.ReportServiceimpl;
import library.service.impl.UserServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({"/admin"})
public class AdminHomeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("books", BookServiceimpl.getInstance().getBooks());
        request.setAttribute("reports", ReportServiceimpl.getInstance().getReports());
        request.setAttribute("users", UserServiceimpl.getInstance().getUsers());
        request.getRequestDispatcher("/pages/adminHomePage.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);
    }
}
