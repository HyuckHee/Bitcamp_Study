package pack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;

import com.sun.net.httpserver.HttpServer;

public class DBManager {

	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Boolean connect = false;

	
	// DB와 통신을 연결하는 메소드
	public boolean DBConnection(String databaseName) {	
		try{
			Class.forName("com.mysql.jdbc.Driver");		//jdbc 드라이버를 갖고온다.
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+databaseName,"root","fbemskfm"); // 자바와 mysql을 통신 연하는 프로토
			System.out.println("DB connect OK");
			stmt = conn.createStatement();
			return true;
		}catch(Exception e){
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
		} catch(Exception e) {
			System.out.println("DB create Fail");
		}
		return false;
	}
	
	
	// 새로운 테이블을 만드는 메소드
	public boolean DBTable_Create(String tableName) {
		String query_TBcreate = "create table " + tableName +"("+
				"id int not null auto_increment primary key,"
				+"name varchar(10),"			
				+"kor int, eng int, math int, total int, avg int"
				+")";	
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_TBcreate);
			
			System.out.println("Table create OK");
			return true;
		} catch(Exception e) {
			System.out.println("Table create Fail");
		}
		return false;
	}
	
	
	
	// 게시판 테이블에 값을 입력하는 메소드.
	public boolean DB_Board_Insert(String database, String table, String user_id, 
									String password, String title, String text) {
		String query_insert = "insert into "+database+"."+table+" values(null,"
				+"'"+user_id+"', '"+password+"', '"+title+"', '"+text+"')";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_insert);
			
			System.out.println("insert OK");
			return true;
		} catch(Exception e) {
			System.out.println("insert Fail" + e);
		}
		return false;
	}
	
	
	// 게시판 테이블에 값을 수정하는 메소드.	
	public boolean DB_Board_Modify(String database, String table, String password, String title, String text, String ori_id) {
		String query_modify = "update "+database+"."+table+" set password='"+password+"', title='"+title+"', text='"+text+"' where id='"+ori_id+"'";
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query_modify);
			
			System.out.println("modify OK");
			return true;
		} catch(Exception e) {
			System.out.println("modify Fail" + e);
		}
		return false;
	}
	
	
	
	// 게시글 삭제 메소드
		public boolean DBTable_Delete(String database, String table, String var){
			String query_delete = "delete from "+database+"."+table+" where id=" + var;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(query_delete);

				System.out.println("delete Ok");
				return true;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("delete Fail");
			}
			return false;
		}
		
	
	
	// 게시판 테이블에서 전체 리스트를 가져오는 메소드
	public HttpServletRequest DBTable_Select(HttpServletRequest request, String database, String table){
		String query_select = "select * from "+database+"."+table;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query_select);

		int i=0;
			while(rs.next()) {	
				
				request.setAttribute(("id"+i), i+1);
				request.setAttribute(("ori_id"+i), rs.getString(1));
				request.setAttribute(("user_id"+i), rs.getString(2));
				request.setAttribute(("password"+i), rs.getString(3));
				request.setAttribute(("title"+i), rs.getString(4));
				request.setAttribute(("text"+i), rs.getString(5));				

				i++;
			} 
			System.out.println("select Ok");
			request.setAttribute(("index"),i);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("select Fail");
		}
		
		return request;
	}
	
	
	// 게시판 테이블에서 누른 링크의 ori_id에 해당되는 게시물을 가져오는 메소드
	public HttpServletRequest DBTable_Select_Id(HttpServletRequest request, String database, String table, String var){
		String query_select_Id = "select * from "+database+"."+table+" where id='" + var+ "'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query_select_Id);
			while(rs.next()) {	
				request.setAttribute(("ori_id"), rs.getString(1));
				request.setAttribute(("user_id"), rs.getString(2));
				request.setAttribute(("password"), rs.getString(3));
				request.setAttribute(("title"), rs.getString(4));
				request.setAttribute(("text"), rs.getString(5));
			}
			System.out.println("select Ok");
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("select Fail");
		}
		
		return request;
	}
	
	// 게시판 테이블에서 검색한 유저 아이디에 해당되는 게시물을 가져오는 메소드
		public HttpServletRequest DBTable_Select_UserId(HttpServletRequest request, String database, String table, String var){
			String query_select_user_Id = "select * from "+database+"."+table+" where user_id='" + var + "'";
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query_select_user_Id);
				int i = 0;
				while(rs.next()) {
					request.setAttribute(("id"+i), i+1);
					request.setAttribute(("ori_id"+i), rs.getString(1));
					request.setAttribute(("user_id"+i), rs.getString(2));
					request.setAttribute(("password"+i), rs.getString(3));
					request.setAttribute(("title"+i), rs.getString(4));
					request.setAttribute(("text"+i), rs.getString(5));
					i++;
				}
				System.out.println("select Ok");
				request.setAttribute("index",i);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("select Fail");
			}
			
			return request;
		}
	
		
		// 댓글을 DB에 입력하는 메소드.
		public boolean DB_Reply_Insert(String database, String table, 
										String ori_id, String user_id, String text) {
			String query_reply_insert = "insert into "+database+"."+table+" values("
					+ori_id+", '"+user_id+"', '"+text+"')";
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(query_reply_insert);
				
				System.out.println("reply_insert OK");
				return true;
			} catch(Exception e) {
				System.out.println("reply_insert Fail" + e);
			}
			return false;
		}
			
	
		// 해당 게시물에 달린 전체 댓글을 가져오는 메소드
		public HttpServletRequest DBTable_Reply_Select_Id(HttpServletRequest request, String database, String table, String ori_id){
			String query_reply_select = "select * from "+database+"."+table+" where id="+ori_id;
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query_reply_select);
				int i=0;
				while(rs.next()) {	
					request.setAttribute(("reply_id"+i), rs.getString(1));
					request.setAttribute(("reply_user_id"+i), rs.getString(2));
					request.setAttribute(("reply_text"+i), rs.getString(3));
					i++;
				} 

				System.out.println("reply_select Ok");
				request.setAttribute("index",i);
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("reply_select Fail");
			}
			
			return request;
		}
	
		
		// 댓글 삭제 메소드
		public boolean DBTable_Reply_Delete(String database, String table, String reply_id, String reply_user_id, String reply_text){
			String query_reply_delete = "delete from "+database+"."+table+" where id="+reply_id+" and user_id='"
										+reply_user_id+"' and text='"+reply_text+"'";
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(query_reply_delete);

				System.out.println("reply_delete Ok");
				return true;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("reply_delete Fail");
			}
			return false;
		}
	
		
		
		// 게시판 리스트에서 각 글마다 댓글 갯수를 띄우기 위해 댓글 갯수를 가져오는 메소드.
		public HttpServletRequest DBTable_Reply_Amount(HttpServletRequest request, String database, String reply_table){
			String query_reply_amount = "select * from "+database+"."+reply_table;
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(query_reply_amount);
				while(rs.next()) {	
					if(request.getAttribute(rs.getString(1))==null) {
						request.setAttribute(rs.getString(1), 0);
					}
					request.setAttribute(rs.getString(1),((int)request.getAttribute(rs.getString(1)))+1);
				} 
				System.out.println("reply_amount Ok");
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("reply_amount Fail");
			}
			
			return request;
		}
		



		
}
