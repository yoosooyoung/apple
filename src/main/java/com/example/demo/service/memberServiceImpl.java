package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.memberDao;
import com.example.demo.vo.memberVo;

@Service
public class memberServiceImpl implements memberService {
	
	@Autowired
	memberDao memberDao;
	
	@Override
	public void insertMember(memberVo vo) {
		memberDao.insertMember(vo);
	}
	
	@Override
	public memberVo memberLogin(memberVo vo) {
		return memberDao.memberLogin(vo);
	}
	
	@Override
	public int checkId(memberVo vo) {
		return memberDao.checkId(vo);
	}

	@Override
	public void updateMember(memberVo vo) {
		memberDao.updateMember(vo);
	}
}
