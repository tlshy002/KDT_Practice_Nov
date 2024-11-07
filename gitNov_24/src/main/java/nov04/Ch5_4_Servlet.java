package nov04;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Ch5_4_Servlet
 */
@WebServlet("/chap5-4.do")
public class Ch5_4_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ch5_4_Servlet() {
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
	}

}

class Chap5_4_DAO { //DB와의 연동 책임을 맡음
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	Ch5_4_DTO dto;
	String query = "select max(salary), min(salary), sum(salary), avg(salary) from employees";
	
	Ch5_4_DTO doIt() {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			rs.next(); // 조회결과로 이동, 조회결과는 1건이므로 while 반복필요없이 next()메서드 한번만 호출함
			
			dto = new Ch5_4_DTO();
			dto.setMax((rs.getInt(1)));
			dto.setMin((rs.getInt(2)));
			dto.setSum((rs.getInt(3)));
			dto.setAvg((rs.getDouble(4)));
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB작업중 문제발생!");
		} finally {
			try {
				rs.close();
			} catch(Exception e) {}
		}
		return dto;
	} 
	
	
}
class OracleXE11g {
	static final String LIB = "oracle.jdbc.driver.OracleDriver";//상수
	static final String NAME = "jdbc:oracle:thin:@localhost:1521:XE";//상수
}



