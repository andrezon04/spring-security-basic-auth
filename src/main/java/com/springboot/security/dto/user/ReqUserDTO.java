package com.springboot.security.dto.user;

import lombok.Data;

@Data
public class ReqUserDTO {

    private String name;
    private String pass;
    private String email;
}