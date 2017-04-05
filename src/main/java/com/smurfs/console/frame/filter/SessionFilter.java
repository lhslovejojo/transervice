package com.smurfs.console.frame.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter {
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    Object userName = req.getSession().getAttribute("userName");
    if (userName == null) {
      resp.sendRedirect(req.getContextPath() + "/index.jsp");
    } else {
      chain.doFilter(request, response);
    }
  }

  public void destroy() {
  }
}
