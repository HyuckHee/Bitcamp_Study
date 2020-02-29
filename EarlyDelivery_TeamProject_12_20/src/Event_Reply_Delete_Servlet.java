

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
 * Servlet implementation class Event_Reply_Delete_Servlet
 */
@WebServlet("/Event_Reply_Delete")
public class Event_Reply_Delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Event_Reply_Delete_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		DBManager db = new DBManager();
		db.DBConnection("ED");

		
		String Login_id = (String)session.getAttribute("user_id");
		String reply_id = (String)request.getParameter("reply_id");
		String reply_user_id = (String)request.getParameter("reply_user_id");	
		String reply_text = (String)request.getParameter("reply_text");	
		
		String ori_id = (String)request.getParameter("ori_id");
		String user_id = (String)request.getParameter("user_id");
		String password = (String)request.getParameter("password");
		String title = (String)request.getParameter("title");
		String text =  (String)request.getParameter("text");

		db.DBTable_Reply_Delete("ED", "Event_Reply", reply_id, reply_user_id, reply_text);
		
		RequestDispatcher dis = request.getRequestDispatcher("Event_Board_Show.jsp?pw_input="+password+"&ori_id="+ori_id+"&user_id="+user_id+"&pw_db="+password+"&title="+title);
		dis.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
