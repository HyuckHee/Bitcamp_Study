

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
 * Servlet implementation class Notice_Reply_Servlet
 */
@WebServlet("/Notice_Reply")
public class Notice_Reply_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Notice_Reply_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
		
		HttpSession session = request.getSession();
		DBManager db = new DBManager();
		db.DBConnection("ED");

		String ori_id = (String)request.getParameter("ori_id");
		String user_id = (String)request.getParameter("user_id");
		String password = (String)request.getParameter("password");
		String title = (String)request.getParameter("title");
		
		String login_id = (String)session.getAttribute("user_id");
		if(session.getAttribute("user_id")==null) {
			login_id = "visitor";
		}
		
		if(login_id != "visitor") {
			String reply_input = (String)request.getParameter("reply_text");
			request.setAttribute("id", ori_id);
			
			db.DB_Reply_Insert("ED", "Notice_Reply", ori_id, login_id, reply_input);
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("Notice_Board_Show.jsp?pw_input="+password+"&ori_id="+ori_id+"&user_id="+user_id+"&pw_db="+password+"&title="+title);
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
