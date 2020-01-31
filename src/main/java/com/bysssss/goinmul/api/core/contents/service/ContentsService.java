package com.bysssss.goinmul.api.core.contents.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bysssss.goinmul.api.common.util.DateUtil;
import com.bysssss.goinmul.api.core.contents.spec.ContentsAllGetRequest;
import com.bysssss.goinmul.api.core.contents.spec.ContentsAllGetResponse;
import com.bysssss.goinmul.api.core.contents.spec.ContentsDeleteRequest;
import com.bysssss.goinmul.api.core.contents.spec.ContentsDeleteResponse;
import com.bysssss.goinmul.api.core.contents.spec.ContentsGetRequest;
import com.bysssss.goinmul.api.core.contents.spec.ContentsGetResponse;
import com.bysssss.goinmul.api.core.contents.spec.ContentsPostRequest;
import com.bysssss.goinmul.api.core.contents.spec.ContentsPostResponse;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql.contents.data.ContentsData;
import com.bysssss.goinmul.api.model.mysql.contents.repository.ContentsRepository;
import com.bysssss.goinmul.api.spec.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContentsService {
	@Autowired
    private ContentsRepository contentsRepository;
	
	public ContentsAllGetResponse getContentsAll(ContentsAllGetRequest request) throws GoinmulException {
        // TODO : fromAt ~ toAt
		Date statusAt = request.takeHourlyStatusAt();
        log.info("ContentsService getContentsAll() : statusAt={}", DateUtil.toString(statusAt));
        
        List<ContentsData> contentsList = contentsRepository.selectContentsListAll();
        
        ContentsAllGetResponse response = new ContentsAllGetResponse(contentsList);
        return response;
    }
	
	public ContentsGetResponse getContents(ContentsGetRequest request) throws GoinmulException {
        Long contentsSeq = request.getSeq();
        log.info("ContentsService getContents() : contentsSeq={}", contentsSeq);
        
        ContentsData contents = contentsRepository.selectContents(contentsSeq);
        if( contents == null) {
        	throw new GoinmulException(ResultCode.NOT_FOUND, "");
        }
        
        ContentsGetResponse response = new ContentsGetResponse(contents);
        return response;
    }
	
	public ContentsPostResponse postContents(ContentsPostRequest request) throws GoinmulException {
		ContentsData contents = request.takeContents();
        log.info("ContentsService postContents() : contents={}", contents);
        
        Long seq = contentsRepository.insertContents(contents);
        
        ContentsPostResponse response = new ContentsPostResponse(seq);
        return response;
    }
	
	public ContentsDeleteResponse deleteContents(ContentsDeleteRequest request) throws GoinmulException {
		Long contentsSeq = request.getSeq();
        log.info("ContentsService deleteContents() : contentsSeq={}", contentsSeq);
        
        Integer count = contentsRepository.deleteContents(contentsSeq);
        
        ContentsDeleteResponse response = new ContentsDeleteResponse(count);
        return response;
    }
}
