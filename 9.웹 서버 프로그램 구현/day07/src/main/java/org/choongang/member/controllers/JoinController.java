package org.choongang.member.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.choongang.global.exceptions.CommonException;
import org.choongang.member.services.JoinService;
import org.choongang.member.services.MemberServiceProvider;

import java.io.IOException;

import static org.choongang.global.MessageUtil.alertError;
import static org.choongang.global.MessageUtil.go;

@WebServlet("/member/join")
public class JoinController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 양식
        RequestDispatcher rd = req.getRequestDispatcher
                ("/WEB-INF/templates/member/join.jsp");
        rd.forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // 서비스를 넣어서 처리
        try {JoinService service = MemberServiceProvider.getInstance().joinService();
        service.process(req); // 요청데이터가 req에 들어 있다

            go(req.getContextPath() + "/member/login", "parent", resp);
            //resp.sendRediret(req.getContextPath() + "/member/login");
        } catch (CommonException e) {
            alertError(e, resp);
        }
    }
}
