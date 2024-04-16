package com.kosta.bank.dao;

import com.kosta.bank.dto.Member;

public interface MemberDao {
	void insertMember(Member mem) throws Exception;
	Member selectMember(String id) throws Exception;
}
