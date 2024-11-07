package nov04;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String id = rintln("수신한계정: "+id+", 암호: "+pw);
	}

	// 리다이렉트? 포워드? 상관없음? => 리다이렉트
	// 브라우저에서는 계속 성공했음이 뜨기 때문에 크게 상관은 없지만.... 논리적으로는 상관없음 근데
	// 서블릿에서 DB갔다 오는과정은 자원 소요가 큼(오래걸림) 
	// 이미 로그인 인증 끝났는데 뭐하러 계속 인증함, 자원 낭비가 크기 때문에 => 리다이렉트
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("수신한계정: "+id+", 암호: "+pw);
		
		LoginDAO dao = new LoginDAO();
		boolean result = dao.doit(id, pw);
		
		// 지금 하는 방식은 jdbc 방식인데, 쿼리가 하드코딩이라 문제임. 나중에 바꿀것임
		
		if(result) {
			HttpSession session = request.getSession();
			session.setAttribute("PASS", id);
		}
		response.sendRedirect("loginResult.jsp?RESULT="+result);
		
	}

}

class LoginDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	boolean yesOrNo = false;
	
	String query = "select user_id, user_pw from user_info "
				+ "where user_id = ? and user_pw = ?"; 
				//요즘은 보안상의 문제로 계정과 비번을 동시에 찾음.
				//예전에는 쿼리문을 계정먼저 찾고 그후 그 계정이 있으면 비번을 찾는 쿼리를 실행했었음
				//근데 계정이 있는지를 알려주니까 때려맞추기 좋아서 이제는 동시에 찾음
	
	boolean doit(String id, String pw) {	
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				yesOrNo = true; //로그인 성공
			} //아니면 실패
			
			rs.next();
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB작업 중 문제발생");
		} finally {
			try {
				rs.close();
			} catch(Exception e) {}
		}
		return yesOrNo;
	}
}

