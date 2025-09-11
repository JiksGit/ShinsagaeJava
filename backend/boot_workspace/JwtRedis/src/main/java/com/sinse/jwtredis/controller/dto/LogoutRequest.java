package com.sinse.jwtredis.controller.dto;

import lombok.Data;

// DTO는 요청에 대한 정보를 담기 위함이므로, 파라미터를 맞게 정의
@Data
public class LogoutRequest {

    private String accessToken;
    private String deviceId;

}
