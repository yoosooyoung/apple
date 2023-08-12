package com.example.demo.service;

import com.example.demo.vo.memberVo;

public interface memberService {

	void insertMember(memberVo vo);

	memberVo memberLogin(memberVo member);

	int checkId(memberVo vo);

	void updateMember(memberVo vo);

}
