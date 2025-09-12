package com.sinse.apiapp.controller;

import com.sinse.apiapp.domain.Board;
import com.sinse.apiapp.model.board.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/*
   글등록      POST     /api/boards
   글목록      GET       /api/boards
   상세보기   GET       /api/boards/{boardId}
   수정하기   PUT       /api/boards/{boardId}
   삭제하기   DELETE /api/boards/{boardId}
 */

@RestController
@RequestMapping("/api")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards")
    public List<Board> getBoards(){
        return boardService.selectAll();
    }

    @PostMapping("/boards")
    public ResponseEntity createBoard(@RequestBody Board board){
        boardService.regist(board);

        return ResponseEntity.ok(Map.of("result","등록 성공"));
    }

    @GetMapping("/boards/{boardId}")
    public Board getBoard(@PathVariable("boardId") int boardId){
        return boardService.select(boardId);
    }

    @PutMapping("/boards/{boardId}")
    public ResponseEntity updateBoard(@PathVariable("boardId") int boardId, @RequestBody Board board){
        boardService.update(board);

        return ResponseEntity.ok(Map.of("result", "수정성공"));
    }

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity deleteBoard(@PathVariable("boardId") int boardId){
        boardService.delete(boardId);
        return ResponseEntity.ok(Map.of("result", "삭제 성공"));
    }


}















