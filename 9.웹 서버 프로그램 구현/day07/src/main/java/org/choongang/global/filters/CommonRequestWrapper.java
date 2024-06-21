package org.choongang.global.filters;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.choongang.member.MemberUtil;
import org.choongang.member.entities.Member;

public class CommonRequestWrapper extends HttpServletRequestWrapper {
    // filter에서 넘어오는 값을 바로 사용하기 위해 ServletRequest req를 매개변수로 사용
    public CommonRequestWrapper(ServletRequest req) {
        super((HttpServletRequest) req);
        // 상속받은 HttpServletRequestWrapper 생성자의 매개변수가 HttpServletRequest이므로 형변환을 함
        HttpServletRequest request = (HttpServletRequest) req;

        // 로그인 상태, 로그인 회원 정보 전역 유지
        boolean isLogin = MemberUtil.isLogin(request);
        Member member = MemberUtil.getMember(request);

        request.setAttribute("isLogin", isLogin);
        request.setAttribute("loggedMember", member);
    }
}
