package com.sinse.stompchat.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Data
public class ChatRoom {

    private String roomId;
    private String roomName;
    private Set<String> users = new HashSet<>();
}
