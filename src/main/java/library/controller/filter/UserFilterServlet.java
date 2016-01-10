package library.controller.filter;

import library.model.dto.UserDTO;
import library.service.impl.UserServiceimpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/user/*"})
public class UserFilterServlet implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        UserDTO user = (UserDTO) req.getSession().getAttribute("user");
        UserDTO userCheck = UserServiceimpl.getInstance().findUserById(user);
        if(userCheck.getRole().equals("admin")){
            ((HttpServletResponse) response).sendRedirect("/admin");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
