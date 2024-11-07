package nov04;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Chapter0504Servlet
 */
@WebServlet("/chapter0504.do")
public class Chapter0504Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chapter0504Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Chapter0504DAO dao = new Chapter0504DAO();
		Chapter0504DTO dto = dao.doIt();
		//조회결과를 출력할 JSP(chapter0504.jsp)로 페이지를 전환한다.
		//서블릿에서 JSP로 페이지를 전환하는 방법 1.Redirect, 2.Forward
		//Redirect는 브라우저의 주소창을 바꿔서 페이지를 전환하는 방법
		//Forward는 브라우저의 주소창을 변경하지 않고 페이지를 전환하는 방법
		//Redirect? Forward? 상관없다? --> 상관없다가 정답이지만, 전송할 데이터가 객체(DTO)이므로 Forward
		//Redirect로는 객체를 데이터로 전송할 수 없다. 문자열만 파라미터로 전송가능하다.
		//Forward로는 자바의 모든 객체를 데이터로 전송할 수 있다. 
		//Forward는 데이터를 HttpServletRequest에 저장한다. 사용하는 메서드 setAttribute()이다.
		//Forward는 RequestDispatcher 객체의 forward()메서드로 화면을 바꾼다.
		//RequestDispatcher 인스턴스는 HttpServletRequest 객체의 getRequestDispathcer()메서드로 생성
		
		HttpSession session = request.getSession(); //인스턴스 생성
		String id = (String)session.getAttribute("PASS"); 
		
		if(id != null) { //로그인 한 경우
			request.setAttribute("0504", dto);
			RequestDispatcher r = request.getRequestDispatcher("chapter0504.jsp");
			r.forward(request, response);
		} else { //로그인 안한 경우 -> 리다이렉트
			response.sendRedirect("login.html");
			
		}
	}
}
class OracleXE11g2 {
	static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
}
class Chapter0504DAO {
	Connection con; PreparedStatement pstmt; ResultSet rs; Chapter0504DTO dto;
	String query = "select max(salary), min(salary), sum(salary), avg(salary) from employees";
	Chapter0504DTO doIt() {
		try {
			Class.forName(OracleXE11g2.LIB);
			con = DriverManager.getConnection(OracleXE11g2.NAME,"hr","hr");
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			rs.next();//조회 결과로 이동, 조회결과가 1건이므로 next()메서드를 한 번만 호출한다.
			dto = new Chapter0504DTO();//DTO를 생성한다.
			dto.setMax(rs.getInt(1));//select 다음 첫번째 조회결과를 DTO에 int로 저장
			dto.setMin(rs.getInt(2));//select 다음 두번째 조회결과를 DTO에 int로 저장
			dto.setSum(rs.getInt(3));//select 다음 세번째 조회결과를 DTO에 int로 저장
			dto.setAvg(rs.getDouble(4));//select 다음 네번째 조회결과를 DTO에 double로 저장
		}catch(Exception e) {
			System.out.println("DB작업 중 문제발생!!!");
		}finally {
			try { con.close(); }catch(Exception e) {}
		}
		return dto;//조회결과가 저장된 DTO를 리턴한다.
	}
}//DAO(Data Access Object)
//DTO는 public class로 작성한다. 왜? JSP에서 사용해야 되므로.
















