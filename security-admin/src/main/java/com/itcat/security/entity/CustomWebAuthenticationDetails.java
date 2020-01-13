package com.itcat.security.entity;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {

    private static final long serialVersionUID = 6975601077710753878L;

    private final String code;

    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        code = request.getParameter("code");
    }

    public String getToken() {
        return code;
    }
}
