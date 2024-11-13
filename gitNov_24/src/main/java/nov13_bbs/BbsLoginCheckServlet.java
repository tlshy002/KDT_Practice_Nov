package nov13_bbs;

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
 * Servlet implementation class BbsLoginCheckServlet
 */
@WebServlet("/bbsLoginCheck.do")
public class BbsLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BbsLoginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 일반회원만 게시글 쓸수있고, 관리자는 글 못쓰게 할거임. 
		// 일반회원으로 로그인한 경우만 write_board.jsp를 보여준다
/*		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");		
		BbsLoginCheckDAO dao = new BbsLoginCheckDAO();
		String result = dao.doit(id, pw);*/ //파라미터에서찾, DB에서 찾는게 아니라 세션에서 계정을 찾아야함.
		
		// 세션에서 찾는거기 때문에 dao는 필요없다.
		HttpSession session = request.getSession();
		String result = (String)session.getAttribute("ID");
		
		String jsp = "";
		
		if(result == null) { //로그인 안한경우
			jsp = "noLogin.jsp";
		} else if(result.equals("admin")) {
			jsp = "noUser.jsp";
		} else {
			jsp = "write_board.jsp";
		}
		response.sendRedirect("index.jsp?BODY="+jsp);
		
	}
}

class BbsLoginCheckDAO {
	String query = "select user_id from user_info where user_id = ? and user_pwd = ?";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String user_id; //이거는 왜필요? --> ㅇㅎ 조회한 계정정보 저장하려고
	
	String doit(String id, String pwd) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user_id = rs.getString(1); //조회된 계정을 변수에 할당
			}
			
		} catch(Exception e) {
			System.out.println("db에러");
		} finally {
			try {
				rs.close();
			} catch(Exception e) { }
		}
		return user_id;
	}
	
}
class OracleXE11g {
	static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
}
