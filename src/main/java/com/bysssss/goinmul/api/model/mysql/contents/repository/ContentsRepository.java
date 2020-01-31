package com.bysssss.goinmul.api.model.mysql.contents.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql.contents.data.ContentsData;
import com.bysssss.goinmul.api.model.mysql.contents.mapper.ContentsMapper;
import com.bysssss.goinmul.api.spec.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ContentsRepository {
	@Autowired
	private ContentsMapper contentsMapper;
	
	public List<ContentsData> selectContentsListAll() throws GoinmulException {
		List<ContentsData> data = null;
		try {
			data = contentsMapper.selectContentsListAll();
		} catch (Exception e) {
			log.error("ContentsRepository selectContentsListAll() : e={}", e.getMessage());
			throw new GoinmulException(ResultCode.DB_EXCEPTION, e.getMessage());
		}
		
		return data;
	}
	
	public ContentsData selectContents(Long seq) throws GoinmulException {
		ContentsData data = null;
		try {
			data = contentsMapper.selectContents(seq);
		} catch (Exception e) {
			log.error("ContentsRepository selectContents() : e={}", e.getMessage());
			throw new GoinmulException(ResultCode.DB_EXCEPTION, e.getMessage());
		}
		
		return data;
	}
	
	public Long insertContents(ContentsData data) throws GoinmulException {
		Long seq=0L;
		try {
			contentsMapper.insertContents(data);
			seq = data.getSeq();
		} catch (Exception e) {
			log.error("ContentsRepository insertContents() : e={}", e.getMessage());
			throw new GoinmulException(ResultCode.DB_EXCEPTION, e.getMessage());
		}
		
		return seq;
	}
	
	public int deleteContents(Long seq) throws GoinmulException {
		int count=0;
		try {
			count = contentsMapper.deleteContents(seq);
		} catch (Exception e) {
			log.error("ContentsRepository deleteContents() : e={}", e.getMessage());
			throw new GoinmulException(ResultCode.DB_EXCEPTION, e.getMessage());
		}
		
		return count;
	}
}
