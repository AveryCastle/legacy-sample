/**
 * 
 */
package sandbox.ibatis;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;

import sample.domain.Member;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

/**
 * @author seongjuhyeon
 *
 */
public class IbatisDao {
	public IbatisDao(){
		createTable();	
	}
	
	private void createTable(){
		SqlMapClient client = null;
		try {
			Reader reader = Resources
			        .getResourceAsReader("sandbox/ibatis/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(reader);
			client.queryForObject("createTable");
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void add(Member member) {
		SqlMapClient client = null;
		try {
			Reader reader = Resources
			        .getResourceAsReader("sandbox/ibatis/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(reader);
			client.insert("add", member);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public Member get(int id) {
		SqlMapClient client = null;
		try {
			Reader reader = Resources
			        .getResourceAsReader("sandbox/ibatis/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(reader);
			Member member = (Member) client.queryForObject("get", id);
			return member;
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
