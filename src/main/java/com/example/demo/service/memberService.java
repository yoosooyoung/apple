package com.example.demo.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.vo.memberVo;

public interface memberService {

	void insertMember(MultipartHttpServletRequest multiRequest, memberVo vo) throws Exception;

	memberVo memberLogin(memberVo member);

	int checkId(memberVo vo);

	void updateMember(memberVo vo);

}
