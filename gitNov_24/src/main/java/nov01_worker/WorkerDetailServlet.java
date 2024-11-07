package nov01_worker;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WorkerDetailServlet
 */
@WebServlet("/workerDetail.do")
public class WorkerDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkerDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		String emp_id = request.getParameter("ID");
		DetailDAO dao = new DetailDAO();
		ArrayList<WorkerDetail> al = dao.doIt(emp_id);
		request.setAttribute("DETAIL", al);
		RequestDispatcher r = request.getRequestDispatcher("detail.jsp");
		r.forward(request, res);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
