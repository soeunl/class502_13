package org.choongang.global.filters;

// 모든 페이지에 공통적으로 구현될 수 있는 필터

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

@WebFilter("/*") //모든 요청에 유입되는 필터가 되기 위해 /*를 넣음
public class CommonFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { // 매개변수 이름 request, response, chain으로 변경
        // 모든 곳에 적용될 수 있는 필터를 만든다
        chain.doFilter(new CommonRequestWrapper(request), response);
    }
}
