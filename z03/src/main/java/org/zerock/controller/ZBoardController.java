package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.dto.PageDTO;
import org.zerock.dto.PageUtil;
import org.zerock.service.BoardService;
import org.zerock.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class ZBoardController {
	
	private BoardService service;
	
	@GetMapping("/list")
	public void list(@ModelAttribute("PageDTO") PageDTO dto,Model model) {
		log.info("board...list...");
		log.info(dto);
		log.info("¼­ºñ½º"+service);
		model.addAttribute("sample","Hello List");
		model.addAttribute("list",service.getPageList(dto));
		model.addAttribute("pageUtil",new PageUtil(dto,service.getTotal()));
	}
	

	@GetMapping("/register")
	public void registerGET() {
		log.info("board...register....");
		
	}
	@PostMapping("/register")
	public String registerPost() {
		log.info("register....post....");
		return "redirect:/board/list";
	}

}
