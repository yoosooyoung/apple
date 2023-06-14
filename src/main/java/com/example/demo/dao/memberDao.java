package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.memberVo;

@Mapper
public interface memberDao {

	void insertMember(memberVo vo);

	memberVo memberLogin(memberVo vo);

	int checkId(memberVo vo);

}
