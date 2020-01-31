package com.bysssss.goinmul.api.model.mysql2.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bysssss.goinmul.api.model.mysql2.member.data.MemberData;

@Mapper
public interface MemberMapper {
	MemberData selectMember(@Param("seq")Long seq) throws Exception;
	MemberData selectMemberById(@Param("memberId")String memberId) throws Exception;
	MemberData selectMemberByIdPw(@Param("memberId")String memberId, @Param("memberPw")String memberPw) throws Exception;
	
	int insertMember(@Param("data")MemberData data) throws Exception;
}
