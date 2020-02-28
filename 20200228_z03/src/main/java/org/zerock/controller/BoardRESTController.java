package org.zerock.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.dto.PageDTO;
import org.zerock.service.BoardService;
import org.zerock.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//레스트방식은 https 선호
@RestController
@Log4j
@RequestMapping("/data/*")
@AllArgsConstructor
@CrossOrigin
public class BoardRESTController {

	private BoardService service;
	
	@GetMapping("/list")
	public ResponseEntity<List<BoardVO>> getList(PageDTO dto){
	
		List<BoardVO> list = service.getPageList(dto);
		
		return new ResponseEntity(list,HttpStatus.OK);
		
	}
	
	@PostMapping("/new")
	public ResponseEntity<String> register(@RequestBody BoardVO vo){
		
		log.info("vo"+ vo);
		
		service.register(vo);
		
		return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
	}

	@GetMapping("/{bno}")
	public ResponseEntity<BoardVO> get(@PathVariable("bno") Integer bno){
		
		log.info("get..............");
		log.info("bno"+bno);
		return null;
	}
}
