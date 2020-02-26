package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.dto.PageDTO;
import org.zerock.mapper.BoardMapper;
import org.zerock.vo.BoardVO;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

//스플이의 빈으로 등록되야함
@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;
	
	@Override
	public List<BoardVO> getPageList(PageDTO dto) {
		// TODO Auto-generated method stub
		
		
		log.info("-------------------------------------"+mapper);
		
		return mapper.getList(dto);
	}
	@Override
	public int getTotal() {
		return mapper.getTotal();
	}
	@Override
	public void register(BoardVO vo) {
		// TODO Auto-generated method stub

	}

}
