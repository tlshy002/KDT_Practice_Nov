package nov08_item;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class InputItemServlet
 */
@WebServlet("/inputItem.do")
public class InputItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputItemServlet() {
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
		request.setCharacterEncoding("EUC-KR"); // method 가 post 인 경우 한글처리
		
		String code = request.getParameter("CODE"); //상품코드
		String name= request.getParameter("NAME"); //상품이름
		String price = request.getParameter("price"); //가격
		String origin = request.getParameter("ORIGIN"); //원산지
		String info = request.getParameter("INFO"); //상품설명
		
		//DTO에 파라미터들을 저장한다
		ItemDTO dto = new ItemDTO();
		dto.setCode(code);
		dto.setName(name);
		dto.setPrice(Integer.parseInt(price));
		dto.setOrigin(origin);
		dto.setInfo(info);
		
		InputItemDAO dao = new InputItemDAO();//DAO를 생성
		boolean flag = dao.doit(dto);
		if(flag) {//상품정보 등록 성공
			response.sendRedirect("itemList.do");
		}else {//상품정보 등록 실패
			
		}
	}
}
class InputItemDAO {
	Connection conn;
	PreparedStatement pstmt;
	ItemDTO dto;
	boolean yesOrNo = false;
	String query = "insert into item_board values(?,?,?,?,?,sysdate)";
	
	boolean doit(ItemDTO dto) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getCode());
			pstmt.setString(2, dto.getName());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, dto.getInfo());
			pstmt.setString(5, dto.getOrigin());
			pstmt.executeUpdate();			
			yesOrNo = true;
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("상품정보 등록 중 문제발생!!!");
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
