/**
 * 
 */
package sample.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sample.domain.Member;

@Repository
public class MemberDaoMybatis implements MemberDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public void add(Member member) {
		// TODO Auto-generated method stub
		member.setId((int) System.currentTimeMillis());
		sqlSession.insert("sample.member.MemberDao.add", member);
	}
	
	@Override
	public void update(Member member) {
		// TODO Auto-generated method stub
		sqlSession.update("sample.member.MemberDao.update", member);
	}
	
	@Override
	public Member get(int id) {
		// TODO Auto-generated method stub
		return (Member) sqlSession.selectOne("sample.member.MemberDao.get", id);
	}
	
	@Override
	public List<Member> list() {
		// TODO Auto-generated method stub
		return sqlSession.selectList("sample.member.MemberDao.list");
	}
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		sqlSession.delete("sample.member.MemberDao.delete", id);
	}

}
