package com.hojin.mywep.dao;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hojin.mywep.dto.BoardVO;
import com.hojin.mywep.dto.Criteria;
import com.hojin.mywep.dto.MemberDto;
import com.hojin.mywep.dto.NoUserDto;

@Repository
public class MywepDaoImpl implements MywepDao{
	
	final String nameSpace = "com.hojin.mywep.dao.MywepDao";
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public MemberDto Login(String id) {
		return sqlSession.selectOne(nameSpace+".login", id);
	}

	@Override
	public void signup(MemberDto member) {
		sqlSession.insert(nameSpace+".signup", member);
	}

	@Override
	public List<BoardVO> getListWithPaging(Criteria criteria) {
		return sqlSession.selectList(nameSpace+".getListWithPaging", criteria);
	}

	@Override
	public int getTotalCount(Criteria criteria) {
		return sqlSession.selectOne(nameSpace+".getTotalCount", criteria);
	}

	@Override
	public void postregistering(BoardVO boardvo) {
		sqlSession.insert(nameSpace+".postregistering", boardvo);
	}

	@Override
	public Long post_no_selectOne() {
		return sqlSession.selectOne(nameSpace+".post_no_selectOne");
	}

	@Override
	public void noUserInsert(NoUserDto nouser) {
		sqlSession.insert(nameSpace+".nouser_insert", nouser);
	}

	@Override
	public BoardVO post_view(Long post_no) {
		return sqlSession.selectOne(nameSpace+".post_view", post_no);
	}

	@Override
	public BoardVO post_modify(Long post_no) {
		return sqlSession.selectOne(nameSpace+".post_modify", post_no);
	}

	@Override
	public String nouserSelectOne(Long post_no) {
		return sqlSession.selectOne(nameSpace+".nouserSelectOne", post_no);
	}

	@Override
	public void post_update(BoardVO boardvo) {
		sqlSession.update(nameSpace+".post_update", boardvo);
	}

	@Override
	public void post_delete(Long post_no) {
		sqlSession.delete(nameSpace+".post_delete", post_no);
	}

	@Override
	public void postregistering2(BoardVO boardvo) {
		sqlSession.insert(nameSpace+".postregistering2", boardvo);
		
	}

	@Override
	public MemberDto profile(String id) {
		return sqlSession.selectOne(nameSpace+".profile", id);
	}

}
