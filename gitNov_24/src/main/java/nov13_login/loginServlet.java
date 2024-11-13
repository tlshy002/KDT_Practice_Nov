package nov13_login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/login.do")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");
		
		LoginCheckDAO dao = new LoginCheckDAO();
		boolean yesOrNo = dao.doit(id, pw);
		
		if(yesOrNo) {
			//로그인 성공했으므로 HttpSession에 데이터(보통 계정정보)를 저장한다.
			HttpSession session = request.getSession();
			session.setAttribute("ID", id);
			response.sendRedirect("index.jsp?BODY=loginSuccess.jsp");
		} else {
			response.sendRedirect("index.jsp?BODY=login.jsp?MSG=f");
		}
	}

}
