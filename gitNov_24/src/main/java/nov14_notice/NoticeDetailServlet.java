package nov14_notice;

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
 * Servlet implementation class NoticeDetailServlet
 */
@WebServlet("/noticeDetail.do")
public class NoticeDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("COUNT");
		System.out.println("글번호: "+num);
		
		NoticeDetailDAO dao = new NoticeDetailDAO();
		NoticeDTO dto = dao.doIt(num);//글번호로 공지글을 검색하고 결과를 NoticeDTO에 저장
		request.setAttribute("NOTICE", dto);//DTO를 HttpServletRequest에 저장
		
		RequestDispatcher r = request.getRequestDispatcher("index.jsp?BODY=noticeDetail.jsp");
		r.forward(request, response);
	}
}
class NoticeDetailDAO{
	String query = "select num,title,writer,content,"
			+ "to_char(write_date,'YYYY/MM/DD HH24:MI:SS') from notice_tbl where num=?";
	Connection con; PreparedStatement pstmt; ResultSet rs; NoticeDTO dto;
	NoticeDTO doIt(String no) {
		try {
			Class.forName(OracleXE11g.LIB);
			con = DriverManager.getConnection(OracleXE11g.NAME, "hr","hr");
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(no));//첫번째 물음표에 글번호를 정수로 넣는다.
			rs = pstmt.executeQuery();
			if(rs.next()) {//조회결과가 존재하는 경우
				dto = new NoticeDTO();//조회결과를 저장하기 위해 DTO를 생성한다.
				dto.setNum(rs.getInt(1)); dto.setTitle(rs.getString(2));
				dto.setWriter(rs.getString(3)); dto.setContent(rs.getString(4));
				dto.setNotice_date(rs.getString(5));
			}
		}catch(Exception e) {
			System.out.println("공지글 상세조회 중 문제발생!");
		}finally {
			try { con.close(); }catch(Exception e) {}
		}
		return dto;
	}
}//공지글 상세정보를 찾는 DAO