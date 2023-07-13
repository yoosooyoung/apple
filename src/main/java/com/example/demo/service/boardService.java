package com.example.demo.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.vo.FileVo;
import com.example.demo.vo.boardVo;

public interface boardService {

	void uploadFile(MultipartHttpServletRequest multiRequest, FileVo fvo) throws Exception;

	void insertBoard(boardVo bvo, FileVo fvo) throws Exception;

	void deleteBoard(boardVo bvo) throws Exception;

	List<Map<String, Object>> listBoard();

}
