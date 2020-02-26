package org.zerock.mapper;

import java.util.List;

import org.zerock.dto.PageDTO;
import org.zerock.vo.BoardVO;

public interface BoardMapper {

	public List<BoardVO> getList(PageDTO dto);
	
	public int getTotal();
}
