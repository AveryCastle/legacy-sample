/**
 * 
 */
package sandbox.jdbc;

import sample.domain.Member;

/**
 * @author seongjuhyeon
 *
 */
public class JdbcDaoTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JdbcDao jdbcDao = new JdbcDao();
		
		Member member = new Member();
		member.setId(4);
		member.setName("kimonyou");
		jdbcDao.add(member);
		
		Member member2 = jdbcDao.get(member.getId());
		member2.display();
	}
	
}
