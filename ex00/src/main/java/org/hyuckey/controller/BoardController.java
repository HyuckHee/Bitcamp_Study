package org.hyuckey.controller;

import java.util.List;

import org.hyuckey.dto.PageDTO;
import org.hyuckey.service.ZBoardService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/board/*")
public class BoardController {
	
	private ZBoardService service;
	
	@GetMapping("/list")
	public void list(@ModelAttribute("PageDTO") PageDTO dto,Model model) {
		log.info("board...list...");
		log.info(dto);
		log.info("서비스"+service);
		model.addAttribute("sample","Hello List");
		model.addAttribute("list",service.getPageList(dto));
	}
}
