package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.service.boardService;
import com.example.demo.vo.FileVo;
import com.example.demo.vo.boardVo;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/board")
public class BoardController{
	
	private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
	private final boardService boardSvc;
	public BoardController(boardService boardSvc) {
		this.boardSvc = boardSvc;
	}


	/**
	 * 글쓰기
	 * @param bvo
	 * @param fvo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/write")
	@ResponseBody
	public Map<String, String> boardWrite(@RequestBody boardVo bvo, FileVo fvo, HttpServletRequest request, Model model) throws Exception{
    	Map<String, String>result = new HashMap<>();
    	HttpSession session = request.getSession();
    	
    	ObjectMapper mapper = new ObjectMapper();
    	boardSvc.insertBoard(bvo, fvo);
    	result.put("board_seq", bvo.getBoard_seq());
        return result;
    }

	@RequestMapping("/edit/{board_seq}")
	@ResponseBody
	public Map<String, String> boardEdit(boardVo bvo, FileVo fvo, HttpServletRequest request) throws Exception{
		bvo.setUser_id("test");
		Map<String, String> result = new HashMap<>();
		HttpSession session = request.getSession();
		boardSvc.updateBoard(bvo);
		result.put("board_seq", bvo.getBoard_seq());
		return result;
	}

	@PostMapping("/delete")
	@ResponseBody
	public Map<String, String> boardDelete(boardVo bvo) throws Exception{
		Map<String, String> result = new HashMap<>();
		boardSvc.deleteBoard(bvo);
		result.put("message", "s");
		return result;
	}
	
	/**
	 * 파일업로드
	 * @param multiRequest
	 * @param bvo
	 * @param fvo
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/upload/files")
	@ResponseBody
	public Map<String, String> boardPicture(MultipartHttpServletRequest multiRequest, boardVo bvo, FileVo fvo, HttpServletRequest request) throws Exception{
    	Map<String, String>result = new HashMap<>();
    	boardSvc.uploadFile(multiRequest, fvo);
        return result;
    }
	
	/**
	 * 글 리스트
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Map<String, Object> boardList(HttpServletRequest request) throws Exception{
    	Map<String, Object>result = new HashMap<>();
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	
    	list = boardSvc.listBoard();
    	result.put("list", list);
    	
        return result;
    }

	/**
	 * 글 상세
	 * @param board_seq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/view/{board_seq}")
	@ResponseBody
	public Map<String, Object> boardView(@PathVariable String board_seq) throws Exception{
		Map<String, Object> json = new HashMap<>();
		Map<String, Object> post = boardSvc.viewBoard(board_seq);
		List<String> pictures = boardSvc.viewPictureBoard(board_seq);
		json.put("board", post);
		json.put("pictures", pictures);
		return json;
	}

	/**
	 * 사진 상세
	 * @param board_seq
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/view/picture/{board_seq}")
	@ResponseBody
	public Map<String, Object> boardPictureView(@PathVariable String board_seq) throws Exception{
		Map<String, Object> post = boardSvc.viewBoard(board_seq);
		return post;
	}

}
