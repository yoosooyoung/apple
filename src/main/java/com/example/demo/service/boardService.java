package com.example.demo.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.vo.FileVo;
import com.example.demo.vo.boardVo;

public interface boardService {

	void uploadFile(MultipartHttpServletRequest multiRequest, FileVo fvo) throws Exception;

	void insertBoard(boardVo bvo, FileVo fvo) throws Exception;

}
