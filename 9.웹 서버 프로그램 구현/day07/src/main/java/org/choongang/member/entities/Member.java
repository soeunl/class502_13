package org.choongang.member.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {
    private long userNo;
    private String email;
    private String password;
    private String userName;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
}
// 데이터베이스 테이블과 변수 매핑하기 위함