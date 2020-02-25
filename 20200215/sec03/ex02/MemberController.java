package sec03.ex02;

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
@WebServlet("/prod_/*")
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
		if (action == null || action.equals("/listProd.do")) {
			List<MemberVO> membersList = MemberDAO.prod();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03_2/listProd.jsp";
		} else if (action.equals("/productForm.do")) {
			nextPage = "/test03_2/productPage.jsp";
		} else if(action.equals("/delprod.do")){
			String id = request.getParameter("id");
			MemberDAO.delprod(id);
			request.setAttribute("msg", "deleted");
			nextPage = "/prod_/listProd.do";
		}else if(action.equals("/modprod.do")){
			String id = request.getParameter("id");
			MemberVO memInfo = MemberDAO.findprod(id);
			request.setAttribute("memInfo", memInfo);
			nextPage = "/test03_2/modForm.jsp";
		}else if(action.equals("/modsend.do")){
			String prod_id = request.getParameter("prod_id");
			String prod_name = request.getParameter("prod_name");
			Double prod_price = Double.parseDouble(request.getParameter("prod_price"));
			String prod_desc = request.getParameter("prod_desc");
			String vend_id = request.getParameter("vend_id");
			MemberVO memberVO = new MemberVO(prod_id,vend_id, prod_name, prod_price, prod_desc);
			MemberDAO.modprod(memberVO);
			request.setAttribute("msg", "modified");
			
			nextPage = "/prod_/listProd.jsp";
		}else if (action.equals("/addProd.do")) {
			String prod_id = request.getParameter("prod_id");
			String prod_name = request.getParameter("prod_name");
			Double prod_price = Double.parseDouble(request.getParameter("prod_price"));
			String prod_desc = request.getParameter("prod_desc");
			String vend_id = request.getParameter("vend_id");
			MemberVO memberVO = new MemberVO(prod_id,vend_id, prod_name, prod_price, prod_desc);
			MemberDAO.addprod(memberVO);
			request.setAttribute("msg", "addMember");
			nextPage = "/prod_/listProd.jsp";
		}else if (action.equals("/searchProd.do")) {
			String id_search = "'%"+request.getParameter("id_search")+"%'";
			String id = id_search.toUpperCase();
			List<MemberVO> searchList = MemberDAO.searchProd(id);
			request.setAttribute("membersList", searchList); //memberlist이름으로 저장
			
			nextPage = "/test03_2/listProd.jsp";
		}  else {
			List<MemberVO> membersList = MemberDAO.prod();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03_2/listProd.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
