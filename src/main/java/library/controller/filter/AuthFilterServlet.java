package library.controller.filter;

import library.model.dto.UserDTO;
import library.service.impl.UserServiceimpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/user/*", "/admin/*"})
public class AuthFilterServlet implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {
    }

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        if (user == null){
            ((HttpServletResponse) response).sendRedirect("/login");
            return;
        }
        chain.doFilter(request, response);
    }
}
