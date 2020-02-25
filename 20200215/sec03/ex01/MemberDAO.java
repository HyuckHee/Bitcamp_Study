package sec03.ex01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MemberVO> prod() {
		List<MemberVO> membersList = new ArrayList();
		try {

			conn = dataFactory.getConnection(); // DB Connection정보를 얻는다 String query = "select * from t_member order by
												// joinDate desc";
			String query = "select P.prod_id,prod_name,P.prod_price,P.prod_desc,V.vend_name from  products P,vendors V where P.vend_id = V.vend_id";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String prod_id = rs.getString(1);
				String prod_name = rs.getString(2);
				int prod_price = rs.getInt(3);
				String prod_desc = rs.getString("prod_desc");
				String vend_name = rs.getString("vend_name");

				MemberVO memberVO = new MemberVO(prod_id, prod_name, prod_price, prod_desc, vend_name);
				membersList.add(memberVO);
				System.out.println(prod_id);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return membersList;
	}

	public void addprod(MemberVO m) {
		try {
			conn = dataFactory.getConnection();
			String prod_id = m.getProd_id();
			String vend_id = m.getVend_id();
			String prod_name = m.getProd_name();
			int prod_price = m.getProd_price();
			String prod_desc = m.getProd_desc();
			String query = "INSERT INTO products(prod_id,vend_id,prod_name,prod_price,prod_desc)" + "VALUES(?,?,?,?,?)";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, prod_id);
			pstmt.setString(2, vend_id);
			pstmt.setString(3, prod_name);
			pstmt.setInt(4, prod_price); // Double
			pstmt.setString(5, prod_desc);
			System.out.println("제품추가: " + query);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}