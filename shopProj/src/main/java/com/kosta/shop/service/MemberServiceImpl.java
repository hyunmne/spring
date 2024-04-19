package com.kosta.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.shop.dao.MemberDao;
import com.kosta.shop.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memDao;
	
	@Override
	public void signUp(Member member) throws Exception {
		memDao.insertMember(member);
	}

	@Override
	public Boolean idCheck(String userid) throws Exception {
		Integer cnt = memDao.idCheck(userid);
		return cnt==1;
	}

	@Override
	public Member login(String userid, String passwd) throws Exception {
		Member member = memDao.selectMember(userid);
		if(member==null) return null;
		if(!member.getPasswd().equals(passwd.trim())) return null;
		return member;
	}

	@Override
	public void modifyMember(Member mem) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
