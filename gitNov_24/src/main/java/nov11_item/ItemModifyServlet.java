package nov11_item;

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

/**
 * Servlet implementation class ItemModifyServlet
 */
@WebServlet("/itemModify.do")
public class ItemModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("EUC-KR"); //한글처리
		String btn = request.getParameter("BTN"); //버튼의 제목 정보가 들어있는 파라미터
		String code = request.getParameter("CODE"); //상품번호가 담긴 파라미터 받기
		
		if(btn.equals("상품정보 수정")) {
			System.out.println("수정버튼 누름");
			
			String name = request.getParameter("NAME");
			String price = request.getParameter("PRICE");
			String origin = request.getParameter("ORIGIN");
			String content = request.getParameter("CONTENT");
			
			ItemDTO dto = new ItemDTO();
			dto.setCode(code); dto.setName(name); dto.setPrice(Integer.parseInt(price));
			dto.setOrigin(origin); dto.setInfo(content);
			
			ItemUpdateDAO dao = new ItemUpdateDAO(); //DAO 생성
			boolean flag = dao.doit(dto); 
			
			if(flag) { //상품정보가 수정된 경우
				response.sendRedirect("itemList.do"); //상품목록 다시 출력
			} else { //상품정보가 수정되지 않은 경우
				response.sendRedirect("index.jsp?BODY=itemUpdateFaile.jsp"); //상품목록 다시 출력
				
			}
			
			
		} else if(btn.equals("상품정보 삭제")) {
			System.out.println("삭제버튼 누름");
			
			ItemDeleteDAO dao = new ItemDeleteDAO();
			boolean flag = dao.doit(code);
			
			if(flag) { //상품정보 삭제완료 -> 상품목록 다시 출력
				response.sendRedirect("itemList.do");
			} else { //상품정보 삭제실패 -> 삭제실패 화면 출력
				response.sendRedirect("index.jsp?BODY=itemDeleteFaile.jsp");
			}
			
		}
	}

}

class ItemUpdateDAO {
	String query = "update item_board set name = ?, price = ?, orgin = ?, info = ? where code = ?";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	boolean yesOrNo;
	
	boolean doit(ItemDTO dto) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr","hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getName());
			pstmt.setInt(2, dto.getPrice());
			pstmt.setString(3, dto.getOrigin());
			pstmt.setString(4, dto.getInfo());
			pstmt.setString(5, dto.getCode());
			pstmt.executeUpdate(); // update 실행
			yesOrNo = true;
			
		} catch(Exception e) {
			System.out.println("상품정보 변경 중 문제발생!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		}
		return yesOrNo;
	}
}


class ItemDeleteDAO {
	String query = "delete from item_board where code = ? ";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	boolean yesOrNo;
	
	boolean doit(String code) {
		try {
			Class.forName(OracleXE11g.LIB);
			conn = DriverManager.getConnection(OracleXE11g.NAME, "hr","hr");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, code);
			pstmt.executeUpdate(); //delete 실행
			yesOrNo = true;
			
		} catch(Exception e) {
			System.out.println("상품 삭제 작업 중 문제발생!");
		} finally {
			try { conn.close(); }catch(Exception e) {}
		} return yesOrNo;
	} 
}