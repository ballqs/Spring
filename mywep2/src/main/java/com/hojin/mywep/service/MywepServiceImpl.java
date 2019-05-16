package com.hojin.mywep.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.hojin.mywep.dao.MywepDao;
import com.hojin.mywep.dto.BoardVO;
import com.hojin.mywep.dto.Criteria;
import com.hojin.mywep.dto.MemberDto;
import com.hojin.mywep.dto.NoUserDto;

@Service
public class MywepServiceImpl implements MywepService{
	
	@Autowired
	MywepDao mywepDao;
	
	@Override
	public String Login(String id, String pw, HttpSession session) {
		MemberDto successCheck = mywepDao.Login(id);
		String value = null;
		
		if(successCheck == null) {
			value = "/board/login";
		}else {
			if(successCheck.getPW().equals(pw)) {//회원의 아이디랑 비밀번호가 같을때 ID 속성 저장
				session.setAttribute("id", id);
				if(successCheck.getADMIN() == 'Y') {					
					session.setAttribute("admin", successCheck.getADMIN());
				}
				value = "redirect:/";
			}else {
				value = "/board/login";
			}
		}
		return value;
	}

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		return mywepDao.getListWithPaging(criteria);
	}

	@Override
	public int getTotal(Criteria criteria) {
		return mywepDao.getTotalCount(criteria);
	}
	
	@Transactional
	@Override
	public void postregistering(BoardVO boardvo, String password,  HttpSession session) {
		//게시물 정보 + 아이디 + 비번
		
		Object id = session.getAttribute("id");
		
		if(id == null) {
			//noUser방식!
			mywepDao.postregistering(boardvo);//UserCheck를 여기서 구분할 것			
		}else {
			//유저 방식
			mywepDao.postregistering2(boardvo);			
		}
		
		
		//게시물 입력하는 것 => 가지고 있는 값 : 제목,내용,아이디
		
		
		if(!password.equals(null)) {			
			Long no = mywepDao.post_no_selectOne();
			//noUser(비회원 테이블)에 정보를 넣기 위해 게시물 번호를 가져와야 한다.
			
			NoUserDto nouser = new NoUserDto();
			
			nouser.setPOST_NO(no);
			nouser.setNONE_PW(password);
			
			mywepDao.noUserInsert(nouser);
		}
		
	}

	@Override
	public BoardVO post_view(Long post_no) {
		return mywepDao.post_view(post_no);
	}

	@Override
	public BoardVO post_modify(Long post_no) {
		return mywepDao.post_modify(post_no);
	}
	
	@Transactional
	@Override
	public String post_noneuser_modify(Long post_no, Model model, BoardVO boardvo, String nONE_PW) {
		String pw = mywepDao.nouserSelectOne(post_no);
		boardvo = mywepDao.post_modify(post_no);
		model.addAttribute("board", boardvo);
		if(pw.equals(nONE_PW)) {			
			return "/board/modify";
		}else {
			return "/board/get";
		}
	}
	
	@Transactional
	@Override
	public String post_update(BoardVO boardvo, Model model) {
		mywepDao.post_update(boardvo);
		boardvo = mywepDao.post_modify(boardvo.getPOST_NO());
		model.addAttribute("board", boardvo);
		return "/board/get";
	}

	@Override
	public void post_delete(Long post_no) {
		mywepDao.post_delete(post_no);
	}

	@Override
	public MemberDto profile(String id) {
		return mywepDao.profile(id);
	}


}
