package member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

	@Autowired 
	private SqlSessionTemplate sqlSession;
	
	public List<MemberVO> select(MemberVO vo) {
		return sqlSession.selectList("member.selectMember", vo);
	}
	
	public int insert(MemberVO vo) {
		return sqlSession.insert("member.insertMember", vo);
	}
	
	public MemberVO logincheck(MemberVO vo) {		
		return sqlSession.selectOne("member.logincheck", vo);
	}
	
	public int delete(MemberVO vo) {		
		return sqlSession.delete("member.deleteMember", vo);
	}

}
