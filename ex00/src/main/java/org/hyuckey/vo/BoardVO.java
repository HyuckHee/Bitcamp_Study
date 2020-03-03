package org.hyuckey.vo;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {

	private int bno,total;
	private String title,content,writer;
	private Date regdate, updateDate;
	
	public BoardVO() {
		bno = 0;
		total = 0;
		title = "start";
		content = "gg";
		writer = "hyuck";
		
	}
}
