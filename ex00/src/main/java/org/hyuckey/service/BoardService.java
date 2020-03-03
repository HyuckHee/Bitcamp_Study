package org.hyuckey.service;

import org.hyuckey.mapper.BoardMapper;

public class BoardService implements BoardServiceImpl {

	private BoardMapper mapper;
	
	@Override
	public String list() {
		return mapper.list();
	}
}
