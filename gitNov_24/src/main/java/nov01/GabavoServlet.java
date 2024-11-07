package nov01;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GabavoServlet
 */
@WebServlet("/gavabo.do")
public class GabavoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GabavoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gamer = request.getParameter("game");
		
		//컴퓨터의 가위바위보를 난수로 찾는다
		int com = (int)(Math.random()*3);
		int human = Integer.parseInt(gamer);
		String result = "";
		
		if(com == human) {
			//무승부
			result = "D";
		} else if((human==0 && com==2) || (human==1 && com==0) || (human==2 && com==1)) {
			//게이머 승
			result = "G";
		} else {
			//컴 승
			result = "C";
		}
		
		//게임의 결과,컴의선택,나의선택을 gabavo.jsp 로 전송하고 페이지 전환
		//새로고침 했을때 게임의 결과가 바뀌지 않고 유지돼야함 --> 리다이렉트 (포워드하면 서블릿이 실행되니 난수가 새로 생성됨 => 게임의 결과가 바뀜)
		response.sendRedirect("gabavo.jsp?R="+result+"&G="+human+"&C="+com); //파라미터 여러개 전송할때는 &fh qnxdla
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
