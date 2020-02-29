

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pack.DB_Manager_user;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		DB_Manager_user DB = new DB_Manager_user();
		DB.DBConfirm_login(request , request.getParameter("id"), request.getParameter("pw"));
		System.out.println(request.getAttribute("id"));
		
		if(request.getAttribute("id")==null) {
			out.println("<script>");
			out.println("alert('login fail')");
			out.println("</script>");
		}else {
			out.println("<script>");
			out.println("alert('login OK')");
			out.println("</script>");
			session.setAttribute("user_key1", request.getAttribute("key"));
			session.setAttribute("user_id", request.getAttribute("id"));
			session.setAttribute("user_pw", request.getAttribute("pw"));
			session.setAttribute("user_name", request.getAttribute("name"));
			session.setAttribute("user_phone", request.getAttribute("phone"));
			session.setAttribute("user_address", request.getAttribute("address"));
			session.setAttribute("user_Daddress", request.getAttribute("Daddress"));
		}

		response.sendRedirect("MAIN_PAGE.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
