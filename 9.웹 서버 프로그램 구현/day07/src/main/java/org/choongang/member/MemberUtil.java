package org.choongang.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.choongang.member.entities.Member;

public class MemberUtil {

    public static boolean isLogin(HttpServletRequest request) {
        // 로그인 한 상태인지 체크
        return getMember(request) != null; // null이 아니면 로그인 상태로 판단하고 true를 반환
    }

    public static Member getMember(HttpServletRequest request) {
        // 세션을 가지고 오고 회원 정보도 가지고 온다
        HttpSession session = request.getSession(); // HttpSession 객체를 가져옴
        Member member = (Member) session.getAttribute("member"); // "member"라는 이름의 세션 속성 값을 Member 객체로 변환하여 가져옴
        return member;
        // 로그인한 정보를 조회할 수 있는 편의 메서드 정의
    }
}
