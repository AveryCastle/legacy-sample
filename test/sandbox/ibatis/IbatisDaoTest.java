/**
 * 
 */
package sandbox.ibatis;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import sample.domain.Member;

/**
 * @author seongjuhyeon
 *
 */
public class IbatisDaoTest {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IbatisDao ibatisDao = new IbatisDao();
		
		Member addMember = new Member();
		addMember.setId(101);
		addMember.setName("seongjuhyun");
		Calendar calendar = Calendar.getInstance();
		addMember.setJoined(calendar.getTime());
		ibatisDao.add(addMember);
		
		Member getMember = ibatisDao.get(addMember.getId());
		getMember.display();
		
	}
	
}
