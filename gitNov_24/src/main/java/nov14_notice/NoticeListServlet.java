package nov14_notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/noticeList.do")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 앵커 링크를 통해 서블릿을 호출했기 때문에 doGet콜백메서드 호출
		
		NoticeListDAO dao = new NoticeListDAO();
		ArrayList<NoticeDTO> list = dao.doit();//공지사항 글 목록을 검색한다.(글번호를 기준으로 내림차순 정렬)
		request.setAttribute("NOTICES", list);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp?BODY=noticeList.jsp");
		rd.forward(request, response);
	}


}

class NoticeListDAO {
	String query = "select num, title, writer, to_char(write_date, 'YYYY/MM/DD HH24:Mi:SS') "
			+ "from notice_tbl order by num desc";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<NoticeDTO> list;
	
	ArrayList<NoticeDTO> doit() {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			list = new ArrayList<NoticeDTO>();
			
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setNum(rs.getInt(1));
				dto.setTitle(rs.getString(2));
				dto.setWriter(rs.getString(3));
				dto.setNotice_date(rs.getString(4));
				list.add(dto); //조회결과가 저장된 DTO를 ArrayList에 저장
			}
			
		} catch(Exception e) {
			System.out.println("공지사항 목록 검색 중 문제발생!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return list;
	}
	
	
}