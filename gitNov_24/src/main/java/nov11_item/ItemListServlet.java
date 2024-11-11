package nov11_item;

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
 * Servlet implementation class ItemListServlet
 */
@WebServlet("/itemList.do")
public class ItemListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemListDAO dao = new ItemListDAO();
		ArrayList<ItemDTO> list = dao.doit();
		request.setAttribute("ITEMS", list); //요청객체에 저장하고 jsp 로 전달
		
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=itemList.jsp");
		r.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

// 목록 조회. 목록만 뜨는 거니까 상품설명(info)는 조회하지 않는다
class ItemListDAO {
	String query = "select code, name, price, orgin, to_char(reg_date, 'YYYY/MM/DD') "
				+ "from item_board";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	ArrayList<ItemDTO> al;
	
	ArrayList<ItemDTO> doit() {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr", "hr");
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery(); // select 실행
			al = new ArrayList<ItemDTO>();

			while(rs.next()) {
				ItemDTO dto = new ItemDTO();
				dto.setCode(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				dto.setOrigin(rs.getString(4));
				dto.setReg_date(rs.getString(5));
				al.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("DB작업 중 문제발생!!!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		
		
		return al;
	}
}
