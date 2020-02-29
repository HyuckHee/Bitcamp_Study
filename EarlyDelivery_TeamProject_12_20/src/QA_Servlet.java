


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pack.DBManager;

/**
 * Servlet implementation class Board_Suvlet
 */
@WebServlet("/QA_Write")
public class QA_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QA_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		DBManager db = new DBManager();
		
		String user_id = (String)session.getAttribute("user_id");
		String password = (String)request.getParameter("password");
		String title = (String)request.getParameter("title");
		String text = (String)request.getParameter("text");
		String secret = (String)request.getParameter("secret");
			
		db.DBConnection("ED");
		db.DB_Board_Insert("ED", "QA_Board",user_id, password, title, text);
			
		response.sendRedirect("QA_Board.jsp");			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
