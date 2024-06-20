package org.choongang.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.choongang.member.entities.Member;

public class MemberUtil {
    public static boolean isLogin(HttpServletRequest request) {

        return getMember(request) != null;
    }

    public static Member getMember(HttpServletRequest request) {
        // 세션을 가지고 오고 회원 정보도 가지고 온다
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        return member;
        // 로그인한 정보를 조회할 수 있는 편의 메서드 정의
    }
}
