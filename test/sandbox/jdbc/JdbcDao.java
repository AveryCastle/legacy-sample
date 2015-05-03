/**
 * 
 */
package sandbox.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import sample.domain.Member;

/**
 * @author seongjuhyeon
 *
 */
public class JdbcDao {
	public JdbcDao() {
		createTable();
	}
	
	public void createTable() {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			// It is occurred when HSQL library is not set to classpath.
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Connection conn = null;
		Statement stmt = null;
		StringBuilder sqlCreate = new StringBuilder();
		sqlCreate.append("CREATE TABLE IF NOT EXISTS member( ");
		sqlCreate.append(" id INTEGER PRIMARY KEY,           ");
		sqlCreate.append(" name VARCHAR(20) NOT NULL,        ");
		sqlCreate.append(" joined DATE                       ");
		sqlCreate.append(" );                                ");
		
		try {
			conn = DriverManager
			        .getConnection("jdbc:hsqldb:memberdb", "SA", "");
			stmt = conn.createStatement();
			boolean result = stmt.execute(sqlCreate.toString());
			System.out.println("result = " + result);
		} catch (SQLException e) {
			// TODO: handle exception
			// It is occurred when JDBC code or SQL has some problems.
			throw new RuntimeException(e);
		} finally {
			// TODO: handle exception
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					throw new RuntimeException(e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					throw new RuntimeException(e2);
				}
			}
		}
	}
	
	public void add(Member member) {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			// It is occurred when HSQL library is not set to classpath.
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		Calendar calGenerate = Calendar.getInstance();
		long insertDate;
		
		try {
			conn = DriverManager
			        .getConnection("jdbc:hsqldb:memberdb", "SA", "");
			pstmt = conn
			        .prepareStatement("INSERT INTO member (id, name, joined) values (?, ?, ?)");
			pstmt.setInt(1, member.getId());
			pstmt.setString(2, member.getName());
			insertDate = calGenerate.getTimeInMillis();
			pstmt.setDate(3, new java.sql.Date(insertDate));
			int rowCount = pstmt.executeUpdate();
			System.out
			        .println("rowCount = " + rowCount + ", insertDate = " + insertDate);
		} catch (SQLException e) {
			// It is occurred when JDBC code or SQL has some problems.
			throw new RuntimeException(e);
		} finally {
			// TODO: handle exception
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e2) {
					// TODO: handle exception
					throw new RuntimeException(e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					throw new RuntimeException(e2);
				}
			}
		}
	}
	
	public Member get(int id) {
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			// It is occurred when HSQL library is not set to classpath.
			throw new RuntimeException(e);
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager
			        .getConnection("jdbc:hsqldb:memberdb", "SA", "");
			pstmt = conn.prepareStatement("select * from member where id = ?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			Member member = null;
			while (rs.next()) {
				member = new Member();
				member.setId(id);
				member.setName(rs.getString("name"));
				member.setJoined(rs.getDate("joined"));
			}
			return member;
		} catch (SQLException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
					throw new RuntimeException(e2);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e2) {
					// TODO: handle exception
					throw new RuntimeException(e2);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
					throw new RuntimeException(e2);
				}
			}
		}
	}
}
