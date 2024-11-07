package nov01_worker;

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
 * Servlet implementation class WorkerServlet
 */
@WebServlet("/worker.do")
public class WorkerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//String emp_id = req.getParameter("ID");
		//DetailDAO dao = new DetailDAO();
		WorkerInformation dao = new WorkerInformation();
		ArrayList<WorkerDepartment> al = dao.doIt();
		req.setAttribute("AL", al);
		RequestDispatcher r = req.getRequestDispatcher("worker.jsp");
		r.forward(req, res);
	}
}

class DetailDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<WorkerDetail> al;
	String query = "select employee_id, last_name, salary, to_char(hire_date, 'YYYY/MM/DD'), email, phone_number "
				+ "from employees where employee_id = ?";
	
	ArrayList<WorkerDetail> doIt(String id) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(id));
			rs = pstmt.executeQuery();
			al = new ArrayList<WorkerDetail>();
			
			while(rs.next()) {
				WorkerDetail wd = new WorkerDetail(); //DTO생성
				wd.setEmp_id(rs.getInt(1));
				wd.setName(rs.getString(2));
				wd.setSalary(rs.getDouble(3));
				wd.setHire_date(rs.getString(4));
				wd.setEmail(rs.getString(5));
				wd.setPhone(rs.getString(6));
				al.add(wd);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB작업 중 문제발생!!!");
		}finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return al;
	}
	
}



class OracleXE11g {
	static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
}

class WorkerInformation { //DAO
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<WorkerDepartment> al;
	String query = "select e.employee_id, e.last_name, d.department_id, d.department_name "
				+ "from employees e, departments d where e.employee_id = d.department_id";
	
	ArrayList<WorkerDepartment> doIt() {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			al = new ArrayList<WorkerDepartment>();//제네릭으로 ArrayList생성
			
			while(rs.next()) {
				WorkerDepartment we = new WorkerDepartment();//DTO 생성
				we.setId(rs.getInt(1));//사번 조회결과를 저장한다. 
				we.setName(rs.getString(2));//사원이름 조회결과를 저장한다.
				we.setDept_id(rs.getInt(3));//부서번호 조회결과를 저장한다.
				we.setDept_name(rs.getString(4));//부서이름 조회결과를 저장한다.
				al.add(we);//DTO를 ArrayList에 저장한다.
			}
		}catch(Exception e) {
			System.out.println("DB작업 중 문제발생!!!");
		}finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return al;
	}
	
}


