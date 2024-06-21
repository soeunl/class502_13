package org.choongang.file.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/upload/*") // uploads 뒤에 파일명이 나오면 그 파일을 찾아간다
public class FileSearchController extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI().replace(req.getContextPath(), ""); // 현재 접속 주소
        // /upload/파일경로
        System.out.println(uri);

        Pattern pattern = Pattern.compile("^/upload/(.+)");
        Matcher matcher = pattern.matcher(uri); // 위에서 등록한 주소 가져옴
        if (matcher.find()) {
            String fileName = matcher.group(1); // (.+)부분을 의미함

            File file = new File("D:/uploads/"+fileName);
            if(file.exists()) {
                    Path source = file.toPath();
                    String contentType = Files.probeContentType(source);
                    resp.setContentType(contentType);

                try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))){
                    // servletOutputStream
                    OutputStream out = resp.getOutputStream();
                    out.write(bis.readAllBytes());
                }

                return;
            }
        }
        // 파일이 없는 경우 - 404 응답코드
        resp.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
