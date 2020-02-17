package member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDao;
	
	public MemberVO logincheck(MemberVO vo) {		
		return memberDao.logincheck(vo);
	}
	
	public int delete(MemberVO vo) {		
		return memberDao.delete(vo);
	}
}
