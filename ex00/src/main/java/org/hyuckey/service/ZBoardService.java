package org.hyuckey.service;

import java.util.List;

import org.hyuckey.dto.PageDTO;
import org.hyuckey.vo.BoardVO;


public interface ZBoardService {
	
	public List<BoardVO> getPageList(PageDTO dto); 

}
