package nov04;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class logoutServlet
 */
@WebServlet("/logout.do")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// HttpSession에 저장된 데이터를 지운다
		// 지우려면 세션 객체 먼저 생성
		HttpSession session = request.getSession();
		
		//어떻게 지움? 지우는 메서드 invalidate() 인벨리데이트 호출
		session.invalidate();
		
		//화면을 logoutResult.jsp 로 바꾼다
		//리다이렉트(포워드는 안됨. 세션 지웠는데 이거 또 지우게?ㄴㄴ)
		response.sendRedirect("logoutResult.jsp");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
