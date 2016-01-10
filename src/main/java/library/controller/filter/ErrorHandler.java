package library.controller.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import library.exception.*;
import library.helper.PropertiesReader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;

@WebFilter({"/*"})
public class ErrorHandler implements Filter{
    private static ObjectMapper mapper;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        PropertiesReader.generateSources();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse servletResponse = (HttpServletResponse)response;
        try {
            chain.doFilter(request, response);
        }catch(DeleteBookException e){
            mapper = new ObjectMapper();
            HttpException exception = new HttpException();
            exception.setMessage(e.getMessage());
            servletResponse.setContentType("application/json; charset=utf-8");
            servletResponse.setStatus(400);
            mapper.writeValue(servletResponse.getWriter(), exception);
        }catch(UserNotFoundException e){
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }catch (DateFormatException | LoginAlreadyExistsException e){
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/pages/signup.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
        PropertiesReader.saveChanges();
    }
}
