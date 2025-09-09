package com.sinse.jwtredis.Domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int role_id;

    private String role_name;
}
