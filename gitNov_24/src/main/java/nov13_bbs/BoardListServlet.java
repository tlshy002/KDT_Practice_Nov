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
 * Servlet implementation class BoardListServlet
 */
@WebServlet("/boardList.do")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardListDAO dao = new BoardListDAO();
		ArrayList<BoardDTO> al = dao.doit();
		
		int totalCount = dao.getBBSCount(); //게시글의 개수 찾기
		int pageCount = totalCount / 5;
		if(totalCount % 5 != 0) pageCount++; // 5개씩 출력하고 나머지 있으면 +1
		
		request.setAttribute("BOARD", al);
		request.setAttribute("PAGES", pageCount);
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=boardList.jsp");
		r.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

class BoardListDAO {
	int getBBSCount() { //게시글의 개수를 리턴하는 메서드
		String query = "select count(*) from free_board";
		int count = 0;
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			System.out.println("db조회, 게시글 건 수 찾는 중 문제발생");
		}finally {
			try { rs.close(); } catch(Exception e) {}
		}
		return count;
	}
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<BoardDTO> al;
	String query = "select id, title, content, to_char(write_date, 'YYYY/MM/DD HH24:MI:SS') from free_board";
	
	ArrayList<BoardDTO> doit() {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			al = new ArrayList<BoardDTO>();
			
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setWriter(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString(3));
				dto.setWrite_date(rs.getString(4));
				al.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB작업 중 문제발생!!!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return al;
	}
	
}
