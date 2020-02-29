package pack;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import com.mysql.fabric.Response;
import com.sun.net.httpserver.HttpServer;
import javax.servlet.http.HttpServletResponse;
public class DB_Manager_user {
	
	static public String confirm_id;
	static public int checknumber_id=1;
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Boolean connect = false;

	// DB와 통신을 연결하는 메소드
	public boolean DBConnection(String databaseName) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); // jdbc 드라이버를 갖고온다.
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, "root", "fbemskfm"); // 자바와
																												// mysql을
																												// 통신
																												// 연하는
																												// 프로토
			System.out.println("DB connect OK");
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	// 새로운 DB를 만드는 메소드
	public boolean DB_Create(String databaseName) {
		String query_DBcreate = "create database " + databaseName + ";";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_DBcreate);
			System.out.println("DB create OK");
			return true;
		} catch (Exception e) {
			System.out.println("DB create Fail");
		}
		return false;
	}

	// 새로운 테이블을 만드는 메소드
	public boolean DBTable_Create(String tableName) {
		String query_TBcreate = "create table " + tableName + "(" + "key1 int not null auto_increment primary key,"
				+ "name_DB varchar(10)," + "id_DB varchar(13)," + "unique index(id_DB)," + "pw_DB varchar(13),"
				+ "phone_DB varchar(11)," + "address_DB varchar(20)," +"Detailed_address_DB varchar(20)" + ")";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_TBcreate);

			System.out.println("Table create OK");
			return true;
		} catch (Exception e) {
			System.out.println("Table create Fail"+e);
		}
		return false;
	}

	// 테이블에 값을 입력하는 메소드.
	public boolean DBTable_Insert(String name, String id, String pw, String phone, String address,String D_address) {
		String query_insert = "insert into ED.user values(" + "null, " + "'" + name + "','" + id + "','" + pw
				+ "','" + phone + "','" + address + "','" + D_address+"'"+ ")";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_insert);

			System.out.println("insert OK");
			return true;
		} catch (Exception e) {
			System.out.println("insert Fail" + e);
			System.out.println("alert('이미 가입된아이디입니다.')");
		}
		return false;
	}

	//세션에 회원정보 저장하는 함수
	public HttpServletRequest DBTable_Select(HttpServletRequest request) {
		String query_select = "select * from ED.user";
		try {
			rs = stmt.executeQuery(query_select);
			int i = 0;
			while (rs.next()) {

				request.setAttribute(("key" + i), rs.getString(1));
				request.setAttribute(("name" + i), rs.getString(2));
				request.setAttribute(("id" + i), rs.getString(3));
				request.setAttribute(("pw" + i), rs.getString(4));
				request.setAttribute(("phone" + i), rs.getString(5));
				request.setAttribute(("address" + i), rs.getString(6));
				i++;
			}

			System.out.println("select Ok");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("select Fail");
		}

		return request;
	}
	public HttpServletRequest DBConfirm_login(HttpServletRequest request ,String id, String pw) { //로그인함수
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}
		Statement stmt = null;
		ResultSet rs = null;
		String a;
		String sq1 = "select * from user where id_DB='"+ id + "'";
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ED", "root", "fbemskfm");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sq1);
			while(rs.next()) {
			System.out.println("rs.3번(아이디)"+rs.getString(3));
			System.out.println("입력한 아이디"+id);
			System.out.println("rs.4번(비밀번호)"+rs.getString(4));
			System.out.println("입력한 비밀번호"+pw);
			a = rs.getString("id_DB");
			
			
			if(id.equals(rs.getString(3))&&pw.equals(rs.getString(4))) {
				System.out.println("<script>");
				System.out.println("alert('로그인성공')");
				System.out.println("<script>");
				request.setAttribute(("key" ), rs.getString(1));
				request.setAttribute(("name"), rs.getString(2));
				request.setAttribute(("id"), rs.getString(3));
				request.setAttribute(("pw"), rs.getString(4));
				request.setAttribute(("phone"), rs.getString(5));
				request.setAttribute(("address"), rs.getString(6));
				request.setAttribute(("Daddress"), rs.getString(7));
				
				return request;
			}else{
				System.out.println("<script>");
				System.out.println("alert('로그인실패')");
				System.out.println("<script>");
				
			}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("로그인 에러"+e);
		}
	
		return request;	
	}

	/*public boolean confirm_id(String insert_id) {
		String sq1 = "select * from user where id_DB=?";
		try {
			pstmt = conn.prepareStatement(sq1);

			pstmt.setString(1, insert_id);
			rs = pstmt.executeQuery();

			String id_check;
			rs.next();
			id_check = rs.getString("id_DB");
		
		if(id_check.equals(insert_id)) {
			System.out.print("alert('중복된 아이디입니다.')");
			return false;
		}
		else {
			System.out.print("alert('가입가능한 아이디입니다.')");
			return true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			return true;
	}*/
}