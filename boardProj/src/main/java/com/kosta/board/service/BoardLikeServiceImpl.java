package com.kosta.board.service;

import com.kosta.board.dao.BoardLikeDao;
import com.kosta.board.dao.BoardLikeDaoImpl;

public class BoardLikeServiceImpl implements BoardLikeService {
	private BoardLikeDao brdLikeDao = new BoardLikeDaoImpl();
	
	@Override
	public boolean boardLike(String memberId, Integer boardNum) throws Exception {
		Integer num = brdLikeDao.selectBrdLike(memberId, boardNum);
		if(num==null) return false;
		return true;
	}

	@Override
	public boolean togleBrdLike(String memberId, Integer boardNum) throws Exception {
		boolean isBoardLike = boardLike(memberId, boardNum);
		
		if (isBoardLike) {
			brdLikeDao.deleteBrdLike(memberId, boardNum);
			return false;
		} else {
			brdLikeDao.insertBrdLike(memberId, boardNum);
			return true;
		}
	}

}
