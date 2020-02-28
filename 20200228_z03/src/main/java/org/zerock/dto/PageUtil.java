package org.zerock.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageUtil {

	private PageDTO dto;
	private int total, start, end;
	private boolean prev, next;

	public PageUtil(PageDTO dto, int total) {

		this.dto = dto;
		this.total = total;

		int pageNum = dto.getPage();
		int tempEnd = (int) (Math.ceil(pageNum / 10.0) * 10);
		this.start = tempEnd - 9 < 0 ? 1 : tempEnd - 9;
		this.prev = this.start != 1;

		// Ç®ÀÚ
		int realEnd = (int) (total / 10.0);

		this.next = realEnd > tempEnd;

		if (realEnd > tempEnd) {
			this.end = tempEnd;

		} else {
			this.end = realEnd + 1;
		}

	}

}