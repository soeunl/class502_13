package org.choongang.file.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload2.core.DiskFileItem;
import org.apache.commons.fileupload2.jakarta.servlet6.JakartaServletDiskFileUpload;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

@WebServlet("/file/upload")
public class FileUploadController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 양식
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/file/upload.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 처리
        JakartaServletDiskFileUpload upload = new JakartaServletDiskFileUpload();
        
        //multipart 형식 body 데이터를 일반 파일 데이터로 분리해서 조회 가능한 List 형태로 변환
        List<DiskFileItem> items = upload.parseRequest(req); // 파트가 여러개이다보니까 분리해서 분석
        for (DiskFileItem item : items) {

            if (item.isFormField()) { // 일반 텍스트 형태의 양식 데이터
                // subject, content
                String name = item.getFieldName();
                String value = item.getString(Charset.forName("UTF-8"));
                System.out.printf("name=%s, value=%s%n", name, value);
            } else {
                // 파일 데이터 - file
                String fileName = item.getName(); // 올렸던 파일의 이름을 알 수 있음
                String contentType = item.getContentType(); // 파일의 형식을 알 수 있음
                long size = item.getSize(); // 파일의 용량을 알 수 있음 (바이트 수)

                System.out.printf("파일이름 : %s, 파일 형식 : %s, 파일용량 : %s%n", fileName, contentType, size);

                File file = new File("D:/uploads/" + fileName);
                item.write(file.toPath()); // 메모리에 있는 파일 데이터가 file쪽 경로로 이동 -> 영구 저장
            }
        }
    }
}
