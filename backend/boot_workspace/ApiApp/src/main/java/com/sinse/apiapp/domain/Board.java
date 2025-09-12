package com.sinse.apiapp.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="board_id")
    private int boardId;

    private String title;
    private String writer;
    private String content;

    @Column(name="regdate", insertable = false, updatable = false
    , columnDefinition = "timestamp default current_timestamp")
    private String regdate;
    private int hit;
}
