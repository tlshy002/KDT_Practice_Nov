package nov19_crud;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import nov14_notice.NoticeDTO;

public class Crud { //매퍼의 쿼리를 호출

	private final String MAPPER_NAME="noticeMapper"; //매퍼의 이름을 선언
	
	public int updateNotice(NoticeDTO dto) {
		SqlSession ss = this.getSession(); int result = -1;
		try {
			result = ss.update(MAPPER_NAME+".updateNotice", dto);
			if(result > 0) ss.commit();
			else ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int deleteNotice(Integer dto) {
		//매퍼파일의 쿼리를 호출하는 객체(SqlSession)를 생성한다
		SqlSession ss = this.getSession();
		int result = -1;
		
		try {
			result = ss.delete(MAPPER_NAME+".deleteNotice", dto);
			if(result > 0) ss.commit(); //삭제 성공
			else ss.rollback(); //삭제 실패
			
		} finally {
			ss.close();
		}
		return result;
	}
	
	private SqlSession getSession() { 
		//SqlSession을 생성하기 위해 DB정보가 필요하고, DB정보를 XML파일에 작성한다
		// 따라서, SqlSession을 생성할 때 XML파일에서 DB정보를 불러온다.
		String config = "mybatisConfig.xml"; 
		InputStream is = null; //파일을 불러올때(열 때) 필요한 객체 선언
		try {
			is = Resources.getResourceAsStream(config); //XML파일을 연다
		} catch(Exception e) { System.out.println("myBatis환경설정 파일이 없거나 잘못 되었음."); }
		
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(is);
		SqlSession ss = factory.openSession(); //SqlSession 인스턴스 생성
		return ss;
	}

} //=====매퍼의 쿼리를 호출 END
