package sec03.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/prod/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberDAO MemberDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() throws ServletException {
		MemberDAO = new MemberDAO();
	}

	public MemberController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doHandle(request, response);
		/*
		 * response.getWriter().append("Served at: ").append(request.getContextPath());
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doHandle(request, response);
		// TODO Auto-generated method stub
		/* doGet(request, response); */
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("gdgdgd");
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String action = request.getPathInfo();
		if (action == null || action.equals("/listprod.do.do")) {
			List<MemberVO> membersList = MemberDAO.prod();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listProd.jsp";
		} else if (action.equals("/memberForm.do")) {
			nextPage = "/test03/productPage.jsp";
		} else if (action.equals("/addProd.do")) {
			String prod_id = request.getParameter("prod_id");
			String prod_name = request.getParameter("prod_name");
			int prod_price = Integer.parseInt(request.getParameter("prod_price"));
			String prod_desc = request.getParameter("prod_desc");
			String vend_id = request.getParameter("vend_id");
			MemberVO memberVO = new MemberVO(prod_id,vend_id, prod_name, prod_price, prod_desc);
			MemberDAO.addprod(memberVO);
			nextPage = "/prod/listProd.jsp";
		} else {
			List<MemberVO> membersList = MemberDAO.prod();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listProd.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
