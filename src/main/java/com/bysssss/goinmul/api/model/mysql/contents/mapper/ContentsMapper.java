package com.bysssss.goinmul.api.model.mysql.contents.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bysssss.goinmul.api.model.mysql.contents.data.ContentsData;

@Mapper
public interface ContentsMapper {
	List<ContentsData> selectContentsListAll( ) throws Exception;
	ContentsData selectContents(@Param("seq")Long seq) throws Exception;
	
	int insertContents(@Param("data")ContentsData data) throws Exception;
	
	int deleteContents(@Param("seq")Long seq) throws Exception;
}
