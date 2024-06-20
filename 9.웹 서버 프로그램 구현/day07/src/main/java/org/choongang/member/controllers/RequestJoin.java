package org.choongang.member.controllers;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestJoin { // DTO
    private String email;
    private String password;
    private String confirmPassword;
    private String userName;
    private boolean termsAgree;
}
