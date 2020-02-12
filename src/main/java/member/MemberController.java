package member;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class MemberController {
	private static final String UPLOAD_PATH = "/upload/test/";
	@Autowired
	private MemberDAO memberDao;

	@RequestMapping({"/member.do", "/member2.do"})
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
	public String memberInsert(Model model, MemberVO vo, @RequestParam("file") MultipartFile file, HttpServletRequest req) {
				
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
		return "redirect:/member.do";
	}
}
