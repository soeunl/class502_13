package org.choongang.member.mapper;

import org.choongang.member.entities.Member;

public interface MemberMapper {
    long exist(String email);
    Member get(String email);
    int register(Member member);
}

// MemberMapper.xml에 정의한 쿼리문 사용을 위해 인터페이스에 추상메서드 형태로 정의함