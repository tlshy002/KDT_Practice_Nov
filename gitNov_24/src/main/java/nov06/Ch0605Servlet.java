package nov06;

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
 * Servlet implementation class Ch0605Servlet
 */
@WebServlet("/ch0605.do")
public class Ch0605Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ch0605Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("USER");
		
		if(id == null) {
			response.sendRedirect("login.jsp");
		} else {
			String name = request.getParameter("NAME");
			
			Ch0605DAO dao = new Ch0605DAO();
			ArrayList<Ch0605DTO> al = dao.doIt(name);
			
			System.out.println("검색된 건 수 :"+al.size());
			request.setAttribute("AL", al);
			RequestDispatcher r = request.getRequestDispatcher("ch0605Result.jsp");
			r.forward(request, response);
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

class Ch0605DAO {
	Connection conn; PreparedStatement pstmt; ResultSet rs; ArrayList<Ch0605DTO> al;
	String query="select last_name, salary from employees "
			+ "where manager_id in (select employee_id from employees "
			+ "where last_name = ?) ";
	ArrayList<Ch0605DTO> doIt(String name){
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);//첫번째 물음표에 변수 name에 있는 문자열을 넣는다.
			rs = pstmt.executeQuery();//select를 실행하고 결과를 ResultSet에 넣는다.
			al = new ArrayList<Ch0605DTO>();//ArrayList 인스턴스를 생성한다.
			while(rs.next()) {
				Ch0605DTO dto = new Ch0605DTO();//dto를 생성한다.
				dto.setName(rs.getString(1));
				dto.setSalary(rs.getInt(2));
				al.add(dto);//dto를 ArrayList에 저장한다.
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB작업 중 문제발생!!!");
		}finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return al;
	}
	
}