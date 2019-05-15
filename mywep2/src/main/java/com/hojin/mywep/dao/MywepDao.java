package com.hojin.mywep.dao;

import java.util.List;

import com.hojin.mywep.dto.BoardVO;
import com.hojin.mywep.dto.Criteria;
import com.hojin.mywep.dto.MemberDto;
import com.hojin.mywep.dto.NoUserDto;

public interface MywepDao {

	MemberDto Login(String id);

	void signup(MemberDto member);

	List<BoardVO> getListWithPaging(Criteria criteria);

	int getTotalCount(Criteria criteria);

	void postregistering(BoardVO boardvo);

	Long post_no_selectOne();

	void noUserInsert(NoUserDto nouser);

	BoardVO post_view(Long post_no);

	BoardVO post_modify(Long post_no);

	String nouserSelectOne(Long post_no);

}
