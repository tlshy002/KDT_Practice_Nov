package nov20_crud;

import java.io.InputStream;
import java.util.ArrayList;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import nov13_login.LoginDTO;
import nov14_notice.FromTo;
import nov14_notice.NoticeDTO;

public class Crud { //매퍼의 쿼리를 호출
	private final String MAPPER_NAME="noticeMapper"; //매퍼의 이름을 선언
	
	public String getIdByLogin(LoginDTO dto) {
		SqlSession ss = this.getSession();
		String result;
		
		try {
			result = ss.selectOne(MAPPER_NAME + ".getUserByLogin", dto);
		} finally {
			ss.close();
		}
		return result;
		
	}
	
	public Integer getNoticeCount() {
		SqlSession ss = this.getSession();
		Integer result;
		
		try {
			result = ss.selectOne(MAPPER_NAME + ".getNoticeCount");
			if(result == null) result = 0;
		} finally {
			ss.close();
		}
		return result;
	}
	
	public ArrayList<NoticeDTO> getNoticeList(FromTo ft) {
		SqlSession ss = this.getSession();
		ArrayList<NoticeDTO> list = new ArrayList<NoticeDTO>();
		try {
			list = (ArrayList)ss.selectList(MAPPER_NAME+".getNoticeList", ft);
			
		} finally {
			ss.close();
		}
		return list;
	}
	
	public int getMaxNoticeNum() {
		SqlSession ss = this.getSession();
		Integer result = 0; // 쿼리문 max()를 조회한 최소값은 0이기때문
		
		try {
			result = ss.selectOne(MAPPER_NAME+".getMaxNotice");
			// 조회결과가 1행인 경우 사용하는 메서드 : selectOne()
			// 조회결과가 N행인 경우 사용하는 메서드 : select
			if(result == null) result = 0;
			
		}finally {
			ss.close();
		}
		return result;
	}
	
	public int insertNotice(NoticeDTO dto) {
		SqlSession ss = this.getSession();
		int result = -1;
		
		try {
			result = ss.insert(MAPPER_NAME+".putNotice", dto);
			if(result > 0) ss.commit();
			else ss.rollback();
		}finally {
			ss.close();
		}
		return result;
	}
	
	
	public int updateNotice(NoticeDTO dto) {
		SqlSession ss = this.getSession(); 
		int result = -1;
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

} //===== DAO역할 매퍼의 쿼리를 호출 END
