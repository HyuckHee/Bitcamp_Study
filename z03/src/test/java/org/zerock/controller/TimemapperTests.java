package org.zerock.controller;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.dto.PageDTO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.TimeMapper;
import org.zerock.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class TimemapperTests {
	
	@Autowired
	private TimeMapper mapper;

	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testList() {
		PageDTO dto = new PageDTO();
		dto.setPage(1);
		dto.setAmount(30);
		
		List<BoardVO> result = boardMapper.getList(dto);
		
		result.forEach(b -> log.info(b));
	}
	@Test
	public void testTime() {
		log.info(mapper.getNow());
	}
}