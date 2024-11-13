package nov13_login;

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
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet("/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// HttpSession에 계정이 있는지 확인함
		// 계정이 있으면, 관리자 계정인지 한번더 확인한다.
		// 관리자 계정인 경우, 화면을 input_item.jsp로 전환함
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("ID");
		
		if(id != null) { //로그인 한경우
			if(id.equals("admin")) { //로그인계정이 관리자계정인 경우
				response.sendRedirect("index.jsp?BODY=input_item.jsp");
			} else { //일반회원 계정인 경우
				response.sendRedirect("index.jsp?BODY=noAdmin.jsp");
			}
			
		} else { //로그인 안한경우
			response.sendRedirect("index.jsp?BODY=login.jsp?MSG=f");
		}
		
	}
}

class LoginCheckDAO {
	String query = "select user_id from user_info where user_id = ? and user_pw = ?";
	Connection conn; 
	PreparedStatement pstmt;
	ResultSet rs;
	boolean yesOrNo;
	
	boolean doit(String id, String pw) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();			
			if(rs.next()) yesOrNo = true;
			
		} catch(Exception e) {
			System.out.println("로그인 검사 중 문제 발생!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return yesOrNo;
	}
}

class OracleXE11g {
	static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
}