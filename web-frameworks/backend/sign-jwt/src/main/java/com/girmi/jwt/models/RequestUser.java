package com.girmi.jwt.models;

import lombok.Data;

import java.util.List;

@Data
public class RequestUser {
    private String userId;
    private String userPw;
}
