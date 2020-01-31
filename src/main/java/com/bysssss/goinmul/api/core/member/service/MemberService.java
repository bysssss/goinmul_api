package com.bysssss.goinmul.api.core.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bysssss.goinmul.api.core.member.spec.MemberGetRequest;
import com.bysssss.goinmul.api.core.member.spec.MemberGetResponse;
import com.bysssss.goinmul.api.core.member.spec.MemberPostRequest;
import com.bysssss.goinmul.api.core.member.spec.MemberPostResponse;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql2.member.data.MemberData;
import com.bysssss.goinmul.api.model.mysql2.member.repository.MemberRepository;
import com.bysssss.goinmul.api.spec.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {
	@Autowired
    private MemberRepository memberRepository;
	
	public MemberGetResponse getMember(MemberGetRequest request) throws GoinmulException {
        Long memberSeq = request.getSeq();
        log.info("MemberService getMember() : memberSeq={}", memberSeq);
        
        MemberData member = memberRepository.selectMember(memberSeq);
        if( member == null) {
        	throw new GoinmulException(ResultCode.NOT_FOUND, "");
        }
        
        MemberGetResponse response = new MemberGetResponse(member);
        return response;
    }
	
	public MemberPostResponse postMember(MemberPostRequest request) throws GoinmulException {
		MemberData member = request.takeMember();
        log.info("MemberService postMember() : member={}", member);
        
        Long seq = memberRepository.insertMember(member);
        
        MemberPostResponse response = new MemberPostResponse(seq);
        return response;
    }
}
