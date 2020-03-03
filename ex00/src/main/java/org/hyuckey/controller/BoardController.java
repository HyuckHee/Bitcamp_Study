package org.hyuckey.controller;

import java.util.List;

import org.hyuckey.service.BoardService;
import org.hyuckey.vo.BoardVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(@ModelAttribute("BoardVO") BoardVO vo) {
		log.info("/board/list");
		
		
		
	}
}
