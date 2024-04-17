package com.kosta.board.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.board.dto.BFile;
import com.kosta.board.dto.Board;


// 자동 커밋이 된다.
@Repository
public class BoardDaoImpl implements BoardDao {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Board> selectBrdList(Integer row) throws Exception {
		return sqlSession.selectList("mapper.board.selectBrdList", row);
	}

	@Override
	public Integer selectBrdCnt() throws Exception {
		return sqlSession.selectOne("mapper.board.selectBrdCnt");
	}

	@Override
	public Board selectBoard(Integer num) throws Exception {
		return sqlSession.selectOne("mapper.board.selectBoard", num);
	}

	@Override
	public void insertBoard(Board board) throws Exception {
		sqlSession.insert("mapper.board.insertBoard", board);
	}

	@Override
	public void insertFile(BFile file) throws Exception {
		sqlSession.insert("mapper.board.insertFile", file);
	}

	@Override
	public void updateBoard(Board board) throws Exception {
		sqlSession.update("mapper.board.updateBoard", board);
	}

	@Override
	public void updateBrdViewCnt(Integer num) throws Exception {
		sqlSession.update("mapper.board.updateBrdViewCnt", num);
	}

	@Override
	public void deleteFile(Integer num) throws Exception {
		sqlSession.delete("mapper.board.deleteFile", num);
	}

}
