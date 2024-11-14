package nov14_notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class WriteNoticeServlet
 */
@WebServlet("/writeNotice.do")
public class WriteNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteNoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.한글처리 
		request.setCharacterEncoding("EUC-KR");
		
		//2.제목과 내용 불러오기 
		String title = request.getParameter("TITLE");
		String content = request.getParameter("CONTENT");
		
		//3.글번호를 찾는다(기존의 글번호 +1)
		NoticeNumDAO numDao = new NoticeNumDAO();
		int newNumber = numDao.findMax() + 1; //새 게시글 번호
		
		//4.DTO에 글번호,제목,내용,작성자를 넣고 DB에 삽입한다 
		NoticeDTO dto = new NoticeDTO();
		dto.setNum(newNumber);
		dto.setTitle(title);
		dto.setContent(content);
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("ID");
		dto.setWriter(id);
		
		NoticeWriteDAO dao = new NoticeWriteDAO();
		boolean result = dao.doit(dto);
		
		if(result) {
			//5.페이지를 공지사랑 목록으로 바꾼다
			
		} else {
			
		}
		
		
		
	}

}

class NoticeWriteDAO {
	String query = "insert into notice_tbl values(?,?,?,sysdate,?)";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	boolean result;
	
	boolean doit(NoticeDTO dto) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getWriter());
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
			} else {
			}
		} catch(Exception e) {
			System.out.println("공지사항 등록 중 문제발생!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return result;
	}
}

class NoticeNumDAO { //공지사항 글번호 중 가장큰 번호를 찾는 DAO
	String query = "select max(num) from notice_tbl";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	int max;
	
	int findMax() {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				max = rs.getInt(1); //조회결과가 존재하는 경우, 가장 큰 글번호가 검색됨
			} else {
				max = 0; //조회결과가 없는경우 즉, 현재 글이 없는 경우
			}
		} catch(Exception e) {
			System.out.println("가장 큰 공지사항 번호 검색 중 문제발생!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return max;
	}
}
class OracleXE11g {
	static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
}
