package org.choongang.global.exceptions;

import jakarta.servlet.http.HttpServletResponse;

public class CommonException extends RuntimeException{

    private int status; // 응답코드도 같이 더해짐

    public CommonException(String message) {
        this(message, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }

    public CommonException(String message, int status) {
        super(message);
        this.status = status; // 응답코드
    }

    public int getStatus() {
        return status;
    }
}
