package com.hojin.mywep.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.hojin.mywep.dto.BoardVO;
import com.hojin.mywep.dto.Criteria;
import com.hojin.mywep.dto.MemberDto;

public interface MywepService {

	String Login(String id, String pw, HttpSession session);

	List<BoardVO> getList(Criteria criteria);

	int getTotal(Criteria criteria);

	void postregistering(BoardVO boardvo, String password, HttpSession session);

	BoardVO post_view(Long post_no);

	BoardVO post_modify(Long post_no);

	String post_noneuser_modify(Long post_no, Model model, BoardVO boardvo, String nONE_PW);

	String post_update(BoardVO boardvo, Model model);

	void post_delete(Long post_no);

	MemberDto profile(String id);

}
