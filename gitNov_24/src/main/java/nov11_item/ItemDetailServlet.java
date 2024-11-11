package nov11_item;

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

/**
 * Servlet implementation class ItemDetailServlet
 */
@WebServlet("/itemDetail.do")
public class ItemDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemDetailServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("CODE");
		System.out.println("서블릿이 수신한 파라미터값: "+code);
		
		ItemDetailDAO dao = new ItemDetailDAO();
		ItemDTO dto = dao.doit(code);
		
		//response.sendRedirect("itemDetail.jsp?="+dto);
		
		// 포워드 페이지 전환. 화면을 itemDetail.jsp 로 바꾼다
		request.setAttribute("ITEM", dto);
		//RequestDispatcher r = request.getRequestDispatcher("itemDetail.jsp"); //이러면 브라우저 전체가 바뀜.
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=itemDetail.jsp"); //인덱스 페이지의 일부분인 BODY가 바뀌도록
		r.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

class ItemDetailDAO {
	String query = "select code, name, price, info, orgin, to_char(reg_date, 'YYYY/MM/DD') "
					+ "from item_board where code = ?";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ItemDTO item;
	
	ItemDTO doit(String code) {
		try {
			Class.forName(OracleXE11g.LIB); 
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				item = new ItemDTO(); //DTO를 생성함. 왜? => 쿼리실행(조회)결과를 저장하기 위해서
				item.setCode(rs.getString(1));
				item.setName(rs.getString(2));
				item.setPrice(rs.getInt(3));
				item.setInfo(rs.getString(4));
				item.setOrigin(rs.getString(5));
				item.setReg_date(rs.getString(6));				
			}
		} catch(Exception e) {
			System.out.println("DB에러, 아이템 상세정보 조회중 에러발생");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return item;
	}
}
