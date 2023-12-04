package com.nhnacademy.shoppingmall.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(initParams = {@WebInitParam(name = "encoding",value = "UTF-8")}, urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {

    private String encodingType;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encodingType = filterConfig.getInitParameter("encoding");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        //todo#8 UTF-8 인코딩, initParams의 encoding parameter value값을 charset 으로 지정합니다.
        //@WebFilter(initParams = {@WebInitParam(name = "encoding",value = "UTF-8")})
        servletResponse.setCharacterEncoding(encodingType);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
