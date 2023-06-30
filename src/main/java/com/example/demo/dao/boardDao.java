package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.FileVo;
import com.example.demo.vo.boardVo;
import com.example.demo.vo.memberVo;

@Mapper
public interface boardDao {

	void insertBoard(boardVo bvo);

	void insertFile(FileVo fvo);
	
	String getBoardSeq();

}
