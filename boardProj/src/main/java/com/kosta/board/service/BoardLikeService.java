package com.kosta.board.service;

public interface BoardLikeService {
	boolean boardLike(String memberId, Integer boardNum) throws Exception;
	boolean togleBrdLike(String memberId, Integer boardNum) throws Exception;
}
