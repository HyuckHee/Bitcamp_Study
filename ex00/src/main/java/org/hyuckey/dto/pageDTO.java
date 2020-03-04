package org.hyuckey.dto;

import lombok.Data;

@Data
public class PageDTO {
	private int page; //페이지번호
	private int amount; //
	
	public PageDTO() {
		page = 1;
		amount = 10;
	}
}
