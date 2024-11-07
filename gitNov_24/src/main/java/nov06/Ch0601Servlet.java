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
 * Servlet implementation class ch0601Servlet
 */
@WebServlet("/ch0601.do")
public class Ch0601Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ch0601Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("USER");
		
		if(id == null) { //로그인 안한 경우
			response.sendRedirect("login.jsp");
			
		} else {
			String name = request.getParameter("NAME");
			Ch0601DAO dao = new Ch0601DAO();
			ArrayList<Ch0601DTO> al = dao.doit(name);
			
			//조회결과를 출력하는 ch0601_Result.jsp로 화면을 전환하고, 조회결과가 저장된 ArrayList를 전달해야함
			// 포워드냐, 리다이렉트냐? --> 리다이렉트는 객체 전달을 할수없기때문에 포워드 ㄱㄱ (데이터를 어디다 넣냐, 리퀘스트객체에 넣음)
			request.setAttribute("AL", al);
			RequestDispatcher r = request.getRequestDispatcher("ch0601Result.jsp");
			r.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

class Ch0601DAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<Ch0601DTO> al;
	
	String query = "Select last_name, to_char(hire_date, 'YYYY/MM/DD'), department_id"
				+ " from employees where department_id ="
				+ " (Select department_id from employees where last_name = ?)"
				+ " and last_name <> ?";
	
	ArrayList<Ch0601DTO> doit(String name) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			
			al = new ArrayList<Ch0601DTO>();
			while(rs.next()) {
				Ch0601DTO dto = new Ch0601DTO(); //DTO인스턴스 생성
				dto.setLast_name(rs.getString(1));
				dto.setHire_date(rs.getString(2));
				dto.setDept_id(rs.getInt(3));
				al.add(dto); //DTO를 어레이리스트에 저장
			}
		} catch(Exception e) {
			
		}
		return al;
	}
	
}
