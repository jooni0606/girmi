package com.girmi.data.jpa.apis.board;

import com.girmi.constants.CodeConstant;
import com.girmi.data.jpa.models.board.Board;
import com.girmi.data.jpa.models.board.BoardPaging;
import com.girmi.data.jpa.models.board.BoardType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardTypeRepository boardTypeRepository;

    public BoardPaging getBoardList(String brdType, int pageNo, int rowSize) throws Exception {
        Board board = new Board();
        if (StringUtils.isEmpty(brdType)) {
            BoardType type = new BoardType();
            type.setBrdType(brdType);
            board.setBoardType(type);
        }
        board.setUseYn("Y");

        BoardPaging boardPaging = new BoardPaging();
        Example<Board> example = Example.of(board);
        long totalCount = boardRepository.count(example);
        boardPaging.setTotalCount(totalCount);

        Page<Board> boardList = boardRepository.findAll(example, PageRequest.of((pageNo-1), rowSize, Sort.by("brdIdx").descending()));
        boardPaging.setBoardList(boardList.get().toList());

        return boardPaging;

    }

    public Board getBoard(Integer brdIdx) throws Exception {
        return boardRepository.findById(brdIdx).get();
    }

    public ResponseEntity<String> saveBoard(Board board) throws Exception{
        try {
            boardRepository.save(board);
            return new ResponseEntity<>(CodeConstant.RESULT_SUCCESS, HttpStatus.OK);
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>(CodeConstant.RESULT_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> deleteBoard(Integer brdIdx) throws Exception {

        try {
            Board board = boardRepository.findById(brdIdx).get();
            board.setUseYn("N");
            boardRepository.save(board);
            return new ResponseEntity<>(CodeConstant.RESULT_SUCCESS, HttpStatus.OK);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<>(CodeConstant.RESULT_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public List<BoardType> getBoardTypeList() throws Exception {
        Example<BoardType> example = Example.of(new BoardType());
        return boardTypeRepository.findAll(example);
    }

    public ResponseEntity<String> saveBoardType(BoardType boardType) throws Exception {
        try {
            boardTypeRepository.save(boardType);
            return new ResponseEntity<String>(CodeConstant.RESULT_SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<String>(CodeConstant.RESULT_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<String> deleteBoardType(String boardType) throws Exception {
        try {
            boardTypeRepository.deleteById(boardType);
            return new ResponseEntity<String>(CodeConstant.RESULT_SUCCESS, HttpStatus.OK);
        }catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return new ResponseEntity<String>(CodeConstant.RESULT_FAIL, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
