package org.zerock.dto;

import lombok.Data;

@Data
public class PageDTO {
	private int page; //��������ȣ
	private int amount; //
	
	public PageDTO() {
		page = 1;
		amount = 10;
	}
}
