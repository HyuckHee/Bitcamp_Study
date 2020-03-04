package org.hyuckey.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageUtil {

	private PageDTO dto;
	private int total, start, end;
	private boolean prev, next;

	public PageUtil(PageDTO dto, int total) {

		this.dto = dto; //요청받은 page데이터를 받아와 실행
		this.total = total; //총데이터를 받아옴

		int pageNum = dto.getPage();
		int tempEnd = (int) (Math.ceil(pageNum / 10.0) * 10);
		this.start = tempEnd - 9 < 0 ? 1 : tempEnd - 9;
		this.prev = this.start != 1;

		// 풀자
		int realEnd = (int) (total / 10.0);

		this.next = realEnd > tempEnd;

		if (realEnd > tempEnd) {
			this.end = tempEnd;

		} else {
			this.end = realEnd + 1;
		}

	}

}