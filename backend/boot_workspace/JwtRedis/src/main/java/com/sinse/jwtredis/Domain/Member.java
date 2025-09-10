package com.sinse.jwtredis.Domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int member_id;

    private String id;
    private String password;
    private String name;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role_id")
    private Role role;
}
