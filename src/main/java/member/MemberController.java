package member;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MemberController {
	private static final String UPLOAD_PATH = "/upload/member/";
	@Autowired
	private MemberDAO memberDao;
	
	@Autowired
	MemberService memberSvc;

	@RequestMapping({"/memberList.do", "/member2.do"})
	public String memberList(Model model, MemberVO vo) {
		List<MemberVO> list = memberDao.select(vo);
		model.addAttribute("list", list);
		return "memberList";
	}
	
	@RequestMapping("/memberInsertForm.do")
	public String memberInsertForm(Model model, MemberVO vo) {
		return "memberInsertForm";
	}
	
	@RequestMapping("/memberInsert.do")
	public String memberInsert(Model model, MemberVO vo, @RequestParam("file") MultipartFile file, HttpServletRequest req,
			@RequestParam(name="info", required = false) String info) {
				
		// 파일업로드
		//System.out.println(req.getRealPath(UPLOAD_PATH));
		String fileExt = "";
		int i = -1;
		if ((i = file.getOriginalFilename().lastIndexOf(".")) != -1) {
			fileExt = file.getOriginalFilename().substring(i);
		}
		// 파일명 랜덤 생성
		String fileName = new Date().getTime() + fileExt;
		try {
			if (!file.getOriginalFilename().isEmpty()) {
				file.transferTo(new File(req.getRealPath(UPLOAD_PATH), fileName)); // 파일저장
				vo.setFilename(fileName); // 파일명 vo에 set
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}		

		memberDao.insert(vo);
		return "redirect:/memberList.do";
	}
	
	@RequestMapping("/memberLoginPage.do")
	public String memberLoginPage() {		
		return "memberLoginPage";
	}	
	
	@RequestMapping("/memberLogin.do")
	public String memberLogin(MemberVO vo, HttpServletRequest request, HttpSession session) {
		
		MemberVO VO = memberSvc.logincheck(vo);		
		if(VO != null) {
			session = request.getSession();
			session.setAttribute("member", VO);
			return "index";
		}else {
			request.setAttribute("code", "alertMessageBack");
			request.setAttribute("message", "아이디 비밀번호가 틀립니다");
			return "include/alert";
		}		
	}
	
	@RequestMapping("/memberLogout.do")
	public String memberLogout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	@RequestMapping("/myInfo.do")
	public String myInfo() {
		
		return "myInfo";
	}
	
	@RequestMapping("/memberDelete.do")
	public String memberDelete(MemberVO vo, HttpSession session) {
		vo = (MemberVO)session.getAttribute("member");		
		memberSvc.delete(vo);
		session.invalidate();
		return "index";
	}
}
