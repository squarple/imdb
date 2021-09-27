package by.radzionau.imdb.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = { "/pages/*" },
        initParams = { @WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class PageRedirectSecurityFilter implements Filter {
    private String indexPath;

    public void init(FilterConfig config) {
        indexPath = config.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);

//        if (httpRequest.getSession().getAttribute(RequestAttribute.ROLE) == null) {
//            httpResponse.sendRedirect(httpRequest.getContextPath() + indexPath);
//        }

        chain.doFilter(request, response);
    }
}
