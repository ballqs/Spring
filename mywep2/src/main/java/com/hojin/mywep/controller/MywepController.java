package com.hojin.mywep.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.hojin.mywep.dao.MywepDao;
import com.hojin.mywep.dto.BoardVO;
import com.hojin.mywep.dto.Criteria;
import com.hojin.mywep.dto.MemberDto;
import com.hojin.mywep.dto.PageDTO;
import com.hojin.mywep.service.MywepService;

@Controller
public class MywepController {
	
	@Autowired
	MywepService mywepService;
	
	@Autowired
	MywepDao mywepDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Criteria criteria, Model model) {
		
		integrated(criteria,model);
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String list() {	
		return "/board/login";
	}
	
	@RequestMapping(value = "/loging", method = RequestMethod.POST)
	public String loging(@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			HttpSession session) {
		
		return mywepService.Login(id,pw,session);
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, SessionStatus status) {
		status.setComplete();
        session.removeAttribute("id");
        session.removeAttribute("admin");
		return "redirect:/";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "/board/singup";
	}
	
	@RequestMapping(value = "/singupComplete", method = RequestMethod.POST)
	public String singupComplete(@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam("name") String name,
			@RequestParam("email") String email,
			@RequestParam("tel") String tel,
			MemberDto member) {
		member.setID(id);
		member.setPW(pw);
		member.setNAME(name);
		member.setEMAIL(email);
		member.setTEL(tel);
		mywepDao.signup(member);
		return "/board/login";
	}
	
	@RequestMapping(value = "/postregister", method = RequestMethod.GET)
	public String postregister() {
		return "/board/register";
	}
	
	@RequestMapping(value = "/postregister", method = RequestMethod.POST)
	public String postregistering(@RequestParam("post_title") String post_title,
			@RequestParam("post_content") String post_content,
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			BoardVO boardvo,
			Model model,
			Criteria criteria,
			HttpSession Session) {
		//password가 아무 값도 참조하지 못했을 경우 회원이라는 증거.
		//테이블의 ID값과 회원의 ID값이 같지 않을 경우? noUser 즉 비회원이다.
		//그렇다면 이것의 구분을 어떻게?...
		//비회원이 1회용 닉네임을 실제하는 회원과 같게 해버리면 그 테이블에도 비밀번호 적용?...
		//그 상황은 충분히 나올만 하다... 둘다 삭제버튼이 주어졌고 삭제시 비밀번호를 입력하게 띄우는 방식은 비회원뿐만...
		//	해답 : 삭제버튼마다 다른 Mapping을 주면 됨
		//게시물 보이게 하는 방식 또한 비회원일때랑 회원일때의 구분을 하기위해 새로운 컬럼 추가?
		boardvo.setPOST_TITLE(post_title);
		boardvo.setPOST_CONTENT(post_content);
		boardvo.setID(id);
		
		mywepService.postregistering(boardvo,password,Session);
		
		integrated(criteria,model);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/boardget", method = RequestMethod.GET)
	public String boardget(@RequestParam("POST_NO") Long post_no,BoardVO boardvo,Model model,
			@ModelAttribute("cri") Criteria cri, HttpSession session) {
		//게시물 번호를 받아와서 지정한 게시물의 내용을 보여줘야 한다.
		boardvo = mywepService.post_view(post_no);
		
		model.addAttribute("board", boardvo);
		
		//게시물 번호를 넘겨야한다.
		//회원이라는 사실을 증명하기 위해 뭐가 필요할까?...
		//게시물 테이블에 하나의 컬럼을 추가해야할듯...
		session.setAttribute("USERCHECK", boardvo.getUSERCHECK());
		
		return "/board/get";
	}
	
	public void integrated(Criteria criteria, Model model) {
		model.addAttribute("list", mywepService.getList(criteria));
		
		int total = mywepService.getTotal(criteria);
		
		model.addAttribute("pageMaker", new PageDTO(criteria, total));
		
		
		/*
		 * model.addAttribute("script_include",
		 * "<script src=\"/resources/vendor/jquery/jquery.min.js\"></script>");
		 */
	}
	
	//회원이 게시물 수정을 눌렀을때의 Mapping
	@RequestMapping(value = "/boardmodify", method = RequestMethod.POST)
	public String boardmodify(@RequestParam("POST_NO") Long post_no,
			@ModelAttribute("cri") Criteria cri,
			Model model,BoardVO boardvo,HttpSession Session) {
		
		Object name = Session.getAttribute("id");
		
		if(name == null) {
			return "redirect:/";
		}else {
			boardvo = mywepService.post_modify(post_no);
			model.addAttribute("board", boardvo);						
			return "/board/modify";
		}
	}
	@RequestMapping(value = {"/boardmodify" , "/boardmodify2"}, method = RequestMethod.GET)
	public String error() {
		return "";
	}
	
	//비회원일때 처리 방식 비밀번호를 받아서 맞는지 확인 해야한다.
	@RequestMapping(value = "/boardmodify2", method = RequestMethod.POST)
	public String boardmodify2(@RequestParam("POST_NO") Long post_no,
			@ModelAttribute("cri") Criteria cri,
			Model model,BoardVO boardvo,
			@RequestParam("NONE_PW") String NONE_PW) {
		//비밀번호 입력값을 받아 왔고 본 테이블의 설정된 비번과 맞는지?
		//맞을 경우 그에 대한 수정할 게시물 내용의 정보를 가져와야함
		String result = mywepService.post_noneuser_modify(post_no,model,boardvo,NONE_PW);
		
		return result;
	}
	
	@RequestMapping(value = "/post_update", method = RequestMethod.POST)
	public String postupdate(@RequestParam("POST_NO") Long post_no,
			@RequestParam("POST_TITLE") String post_title,
			@RequestParam("POST_CONTENT") String post_content,
			@RequestParam("ID") String id,
			BoardVO boardvo,Model model,
			@ModelAttribute("cri") Criteria cri) {
		boardvo.setPOST_NO(post_no);
		boardvo.setPOST_TITLE(post_title);
		boardvo.setPOST_CONTENT(post_content);
		boardvo.setID(id);
		String result = mywepService.post_update(boardvo,model);
		
		return result;
	}
	
	@RequestMapping(value = "/post_remove", method = RequestMethod.POST)
	public String remove(@ModelAttribute("cri") Criteria cri,
			@RequestParam("POST_NO") Long post_no) {
		mywepService.post_delete(post_no);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(HttpSession session,Model model) {
		String id = (String) session.getAttribute("id");
		MemberDto member = mywepService.profile(id);
		model.addAttribute("member", member);
		return "/board/profile";
	}
	
}
