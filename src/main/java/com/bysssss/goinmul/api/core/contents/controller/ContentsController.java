package com.bysssss.goinmul.api.core.contents.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bysssss.goinmul.api.core.contents.service.ContentsService;
import com.bysssss.goinmul.api.core.contents.spec.ContentsAllGetRequest;
import com.bysssss.goinmul.api.core.contents.spec.ContentsAllGetResponse;
import com.bysssss.goinmul.api.core.contents.spec.ContentsDeleteRequest;
import com.bysssss.goinmul.api.core.contents.spec.ContentsDeleteResponse;
import com.bysssss.goinmul.api.core.contents.spec.ContentsGetRequest;
import com.bysssss.goinmul.api.core.contents.spec.ContentsGetResponse;
import com.bysssss.goinmul.api.core.contents.spec.ContentsPostRequest;
import com.bysssss.goinmul.api.core.contents.spec.ContentsPostResponse;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin(origins="*", methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE}, allowedHeaders={"Content-Type","Authorization"}, allowCredentials="true", maxAge=3600)
@Validated // @PathVariable 검증용 어노테이션
@RequestMapping("/api/v1/contents")
@RestController
public class ContentsController {
	@Autowired
    private ContentsService contentsService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> getContentsAll(
            @Valid ContentsAllGetRequest request) throws GoinmulException {
        log.info("ContentsController getContentsAll() : request={}", request);

        ContentsAllGetResponse response = contentsService.getContentsAll(request);
        return response.ok();
    }

	@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> getContents(
    		@PathVariable(value = "seq") @NotNull @Min(1L) Long seq) throws GoinmulException {
		ContentsGetRequest request = new ContentsGetRequest(seq);
		log.info("ContentsController getContentsAll() : request={}", request);
		
        ContentsGetResponse response = contentsService.getContents(request);
        return response.ok();
    }
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> postContents(
    		@RequestBody @Valid ContentsPostRequest request) throws GoinmulException {
		log.info("ContentsController postContents() : request={}", request);
		
		ContentsPostResponse response = contentsService.postContents(request);
        return response.ok();
    }
	
	@RequestMapping(value = "", method = RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> deleteContents(
    		@RequestBody @Valid ContentsDeleteRequest request) throws GoinmulException {
		log.info("ContentsController deleteContents() : request={}", request);
		
		ContentsDeleteResponse response = contentsService.deleteContents(request);
        return response.ok();
    }
}
