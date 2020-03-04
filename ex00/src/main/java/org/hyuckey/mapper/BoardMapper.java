package org.hyuckey.mapper;

import java.util.List;

import org.hyuckey.dto.PageDTO;
import org.hyuckey.vo.BoardVO;

public interface BoardMapper {

	public List<BoardVO> getList(PageDTO dto);
	
}
