package com.example.Globitel.Globitel.filters;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class UserFilter   {

    List<String> ignorablePaths = Arrays.asList("/login");

    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        //ignore ignorable paths
        String path = req.getRequestURI();
        if (ignorablePaths.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        //do session filter
        if (req.getSession().getAttribute("ACTIVE_USER") == null) {
            res.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid Session");
        }
        chain.doFilter(request, response);
    }

}