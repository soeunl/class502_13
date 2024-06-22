package org.choongang.file.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;

@WebServlet("/file/download")
public class FileDownloadController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        File file = new File("D:uploads/1.png"); // File형의 file이라는 객체 생성

        String contentType = Files.probeContentType(file.toPath()); // 파일의 경로를 매개변수로 받아 파일 타입을 확인
        // 윈도우즈에서 한글 깨짐 문제 발생하면 인코딩 UTF-8 -> CPC949, EUC-KR, ISO8859_1
        String fileName = new String(file.getName().getBytes(), "ISO8859_1");

        resp.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        resp.setContentType(contentType);
        resp.setHeader("Cache-Control", "must-revalidate"); // 브라우저를 캐싱하지 않고 매번 다시 갱신한다
        resp.setIntHeader("Expires", 0);
        resp.setContentLengthLong(file.length());

//        OutputStream out = resp.getOutputStream();
//        PrintWriter out = resp.getWriter();
//        out.print("ABC");
//        out.print("DEF");

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            OutputStream out = resp.getOutputStream();
            out.write(bis.readAllBytes());
        }
    }
}
