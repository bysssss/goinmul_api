package com.bysssss.goinmul.api.core.page.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {
	@RequestMapping(value="/", method=RequestMethod.GET, consumes=MediaType.ALL_VALUE, produces=MediaType.TEXT_HTML_VALUE)
	public ModelAndView getIndex() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
}