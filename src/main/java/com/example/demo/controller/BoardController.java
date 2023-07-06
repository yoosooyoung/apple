package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.service.boardService;
import com.example.demo.vo.FileVo;
import com.example.demo.vo.boardVo;

@RestController
@RequestMapping("/api/board")
public class BoardController{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	@Autowired
    private boardService boardSvc;
	
	@RequestMapping("/write")
	@ResponseBody
	public Map<String, String> boardWrite(boardVo bvo, FileVo fvo, HttpServletRequest request) throws Exception{
    	Map<String, String>result = new HashMap<>();
    	HttpSession session = request.getSession();
    	bvo.setUser_id("test");
    	boardSvc.insertBoard(bvo, fvo);
    	result.put("board_seq", bvo.getBoard_seq());
        return result;
    }
	@RequestMapping("/upload/files")
	@ResponseBody
	public Map<String, String> boardPicture(MultipartHttpServletRequest multiRequest, boardVo bvo, FileVo fvo, HttpServletRequest request) throws Exception{
    	Map<String, String>result = new HashMap<>();
    	boardSvc.uploadFile(multiRequest, fvo);
        return result;
    }
	
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> boardList(HttpServletRequest request) throws Exception{
    	Map<String, Object>result = new HashMap<>();
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	
    	list = boardSvc.listBoard();
    	result.put("list", list);
    	
        return result;
    }

}
