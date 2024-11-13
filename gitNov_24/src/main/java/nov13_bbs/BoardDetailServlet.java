package nov13_bbs;

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
 * Servlet implementation class BoardDetailServlet
 */
@WebServlet("/boardDetail.do")
public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//앵커를 통한 호출은 doGet()을 실행함
		
		String title = request.getParameter("TITLE");
		BoardDetailDAO dao = new BoardDetailDAO();
		System.out.println(title);
		
		BoardDTO dto = dao.doit(title);
		request.setAttribute("DTO", dto);//조회결과를 HttpServletRequest에 저장
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=boardDetail.jsp");
		//게시글 상세정보를 보여주는 JSP(boardDetail.jsp)로 전환
		r.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
class BoardDetailDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	BoardDTO dto;
	String query = "select id, title, content, to_char(write_date, 'YYYY/MM/DD HH24:MI:SS') "
			+ "from free_board where title= ?";
	
	BoardDTO doit(String title) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title); //쿼리 첫번째 물음표에 제목넣기
			rs = pstmt.executeQuery();
			rs.next(); //조회결과가 1건일것이라 가정한 코드. 조회결과로 한번이동
			// 같은 제목의 게시글(조회결과)이 여러개인 경우는 나중에 할거임.
			
			dto = new BoardDTO(); //조회결과를 저장하기 위한 DTO생성
			dto.setWriter(rs.getString(1)); // select 첫번째 조회결과를 DTO의 writer 에 저장
			dto.setTitle(rs.getString(2));
			dto.setContent(rs.getString(3));
			dto.setWrite_date(rs.getString(4));
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("제목과 일치하는 게시글 조회 중 문제발생!!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return dto;
	}
	
}