package org.choongang.member.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.choongang.global.exceptions.CommonException;
import org.choongang.member.services.LoginService;
import org.choongang.member.services.MemberServiceProvider;

import java.io.IOException;

import static org.choongang.global.MessageUtil.alertError;
import static org.choongang.global.MessageUtil.go;

// 로그인 기능의 컨트롤러
// doGet 메서드는 로그인 페이지를 보여주고, doPost 메서드는 사용자 입력 정보를 처리하여 로그인 로직을 수행
// 로그인 처리 후 메인 페이지로 이동하거나 에러 상황 시 에러 메시지를 표시함

@WebServlet("/member/login")
public class LoginController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인 페이지를 보여주는 역할
        RequestDispatcher rd = req.getRequestDispatcher
                ("/WEB-INF/templates/member/login.jsp"); // login.jsp 페이지를 가져오기 위한 RequestDispatcher 객체를 생성(버퍼 치환)
        rd.forward(req, resp); // 요청 정보와 응답 정보를 활용해 forward 메서드를 호출하여 페이지를 바꿈
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{ // 로그인 form 데이터를 처리하는 역할
            LoginService service = MemberServiceProvider.getInstance().loginService(); // MemberServiceProvider 클래스를 통해 LoginService 객체를 가져옴
            service.process(req); // LoginService 객체의 process(req) 메서드를 호출하여 로그인 로직을 수행함
            // req 객체에는 클라이언트가 입력한 로그인 정보가 담겨 있음

            go(req.getContextPath() + "/", "parent", resp); // 로그인 처리가 성공하면 go 메서드를 호출하여 메인 페이지로 이동

        } catch (CommonException e) {
            alertError(e, resp); // 예외가 발생하면 alertError 메서드를 호출하여 에러 메시지 띄움
        }
    }
}
