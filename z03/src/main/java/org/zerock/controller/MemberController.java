package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.service.MemberService;

@Controller
@RequestMapping("/member/*")
public class MemberController {
	
	private MemberService service;
	
	@GetMapping("/list")
	public void list() {
		
	}
	
}
