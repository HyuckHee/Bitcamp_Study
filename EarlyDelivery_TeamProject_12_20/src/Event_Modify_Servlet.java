

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pack.DBManager;

/**
 * Servlet implementation class Event_Modify_Servlet
 */
@WebServlet("/Event_Modify")
public class Event_Modify_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Event_Modify_Servlet() {
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
		String ori_id = (String)request.getParameter("ori_id");	
		String password = (String)request.getParameter("password");
		String title = (String)request.getParameter("title");
		String text = (String)request.getParameter("text");
		
		db.DBConnection("ED");
		db.DB_Board_Modify("ED", "Event_Board", password, title, text, ori_id);
		
		response.sendRedirect("Event_Board.jsp");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
