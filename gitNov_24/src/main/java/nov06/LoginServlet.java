package nov06;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");
		LoginDAO dao = new LoginDAO();
		
		System.out.println(id + pw);
		boolean result = dao.login(id, pw);
		String loginSuccess = "FAIL";
		
		if(result) { //result 불린값을 리다이렉트 url에 바로 넣어도 되고, 이렇게 변수로 바꿔서 넣어도 되고~
			loginSuccess = "OK";
			HttpSession session = request.getSession();
			session.setAttribute("USER", id); //세션객체에 키이름과 계정정보를 저장함
		} else {} //로그인 실패
		
		System.out.println(result);
		response.sendRedirect("loginResult.jsp?LOGIN="+loginSuccess); 
 	}

}

class LoginDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	boolean yesOrNo;
	String query = "select user_id from user_info where user_id = ? and user_pw = ?";
	
	boolean login(String id, String pw) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //조회결과가 존재하는 경우, 로그인 성공
				yesOrNo = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB작업 중 문제 발생!");
		}
		return yesOrNo;
	}
}

class OracleXE11g {
	static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
}


