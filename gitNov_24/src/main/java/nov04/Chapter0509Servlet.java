package nov04;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Chapter0509Servlet
 */
@WebServlet("/chapter0509.do")
public class Chapter0509Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Chapter0509Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Chapter0509DAO dao = new Chapter0509DAO();
		String salary = request.getParameter("salary");
		ArrayList<Chapter0509DTO> al = dao.doIt(Integer.parseInt(salary));
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("PASS");
		
		if(id == null) {//세션에 데이터가 없으므로 로그인 안함
			response.sendRedirect("login.html");//login.html로 바꾼다.(리다이렉트)
		}else {//세션에 데이터가 있으므로 로그인 함
			request.setAttribute("0509", al);
			RequestDispatcher r = request.getRequestDispatcher("chapter0509.jsp");
			r.forward(request, response);
		}
	}
}
class Chapter0509DAO {
	Connection con; PreparedStatement pstmt; ResultSet rs; ArrayList<Chapter0509DTO> al;
	String query = "select manager_id, min(salary) from employees "
			+ "where manager_id is not null group by manager_id "
			+ "having min(salary) > ? order by min(salary) desc";
	//PreparedStatment는 쿼리에 데이터가 위치하는 부분을 물음표(?)로 처리한다.
	ArrayList<Chapter0509DTO> doIt(int salary){
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME, "hr","hr");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, salary);//첫번째 물음표에 salary에 저장된 정수를 넣는다.
			rs = pstmt.executeQuery();//select를 실행하고 결과를 ResultSet에 넣는다.
			al = new ArrayList<Chapter0509DTO>();//ArrayList 인스턴스를 생성한다.
			while(rs.next()) {//건 수가 여러개 이므로 next()메서드를 반복 실행한다.
				Chapter0509DTO dto = new Chapter0509DTO();//dto를 생성한다.
				dto.setManager_id(rs.getInt(1));//select 다음 첫번째 결과(관리자사번)을 정수로 저장
				dto.setMin(rs.getInt(2));//select 다음 두번째 결과(최소월급)을 정수로 저장
				al.add(dto);//DTO를 ArrayList에 저장한다.
			}
		}catch(Exception e) {
			System.out.println("DB작업 중 문제 발생!!!");
		}finally {
			try { con.close(); }catch(Exception e) {}
		}
		return al;
	}
}//DAO 객체





















