package org.hyuckey.service;

import java.util.List;

import org.hyuckey.dto.PageDTO;
import org.hyuckey.mapper.BoardMapper;
import org.hyuckey.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j
public class ZBoardServiceImpl implements ZBoardService{
	
	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getPageList(PageDTO dto) {
		// TODO Auto-generated method stub

		log.info("-------------------------------------" + mapper);

		return mapper.getList(dto);
	}

}
