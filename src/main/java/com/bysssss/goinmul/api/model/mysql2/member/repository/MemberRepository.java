package com.bysssss.goinmul.api.model.mysql2.member.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql2.member.data.MemberData;
import com.bysssss.goinmul.api.model.mysql2.member.mapper.MemberMapper;
import com.bysssss.goinmul.api.spec.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class MemberRepository {
	@Autowired
	private MemberMapper memberMapper;
	
	public MemberData selectMember(Long seq) throws GoinmulException {
		MemberData data = null;
		try {
			data = memberMapper.selectMember(seq);
		} catch (Exception e) {
			log.error("MemberRepository selectMember() : e={}", e.getMessage());
			throw new GoinmulException(ResultCode.DB_EXCEPTION, e.getMessage());
		}
		
		return data;
	}
	
	public MemberData selectMemberById(String memberId) throws GoinmulException {
		MemberData data = null;
		try {
			data = memberMapper.selectMemberById(memberId);
		} catch (Exception e) {
			log.error("MemberRepository selectMemberById() : e={}", e.getMessage());
			throw new GoinmulException(ResultCode.DB_EXCEPTION, e.getMessage());
		}
		
		return data;
	}
	
	public MemberData selectMemberByIdPw(String memberId, String memberPw) throws GoinmulException {
		MemberData data = null;
		try {
			data = memberMapper.selectMemberByIdPw(memberId, memberPw);
		} catch (Exception e) {
			log.error("MemberRepository selectMemberByIdPw() : e={}", e.getMessage());
			throw new GoinmulException(ResultCode.DB_EXCEPTION, e.getMessage());
		}
		
		return data;
	}
	
	public Long insertMember(MemberData data) throws GoinmulException {
		Long seq=0L;
		try {
			memberMapper.insertMember(data);
			seq = data.getSeq();
		} catch (Exception e) {
			log.error("MemberRepository insertMember() : e={}", e.getMessage());
			throw new GoinmulException(ResultCode.DB_EXCEPTION, e.getMessage());
		}
		
		return seq;
	}
}
