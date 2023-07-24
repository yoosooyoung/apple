package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.FileVo;
import com.example.demo.vo.boardVo;
import com.example.demo.vo.memberVo;

@Mapper
public interface boardDao {

	void insertBoard(boardVo bvo);

	void insertFile(FileVo fvo);
	
	String getBoardSeq();

	List<Map<String, Object>> listBoard();

    void deleteBoard(boardVo bvo);

	void deletePicture(boardVo bvo);

	void updateBoard(boardVo bvo);
}
