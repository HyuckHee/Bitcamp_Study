package sec03.ex02;

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

			conn = dataFactory.getConnection(); 
			// DB Connection정보를 얻는다 String query = "select * from t_member order by
												// joinDate desc";
			String query = "select * from  products P,vendors V where P.vend_id = V.vend_id";
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String prod_id = rs.getString("prod_id");
				String prod_name = rs.getString("prod_name");
				Double prod_price = rs.getDouble("prod_price");
				String prod_desc = rs.getString("prod_desc");
				String vend_name = rs.getString("vend_name");
				
				MemberVO memberVO = new MemberVO(prod_id, prod_name, prod_price, prod_desc,vend_name);
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
			Double prod_price = m.getProd_price();
			String prod_desc = m.getProd_desc();
			String query = "INSERT INTO products(prod_id,vend_id,prod_name,prod_price,prod_desc)" + "VALUES(?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, prod_id);
			pstmt.setString(2, vend_id);
			pstmt.setString(3, prod_name);
			pstmt.setDouble(4, prod_price);//Double
			pstmt.setString(5, prod_desc);
			System.out.println("제품추가: "+query);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void delprod(String id) {
		try {
			conn = dataFactory.getConnection();
			String query = "DELETE FROM products WHERE TRIM(prod_id)=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			System.out.println("삭제함수실행완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public MemberVO findprod(String id) {
		MemberVO memInfo = null;
		try {
			conn = dataFactory.getConnection();
			String query = "SELECT * FROM products P ,vendors V  WHERE P.vend_id = V.vend_id AND TRIM(prod_id)=?";
			
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			System.out.println(query);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			String prod_id = rs.getString("prod_id");
			String vend_id = rs.getString("vend_id");
			String prod_name = rs.getString("prod_name");
			Double prod_price = rs.getDouble("prod_price");
			String prod_desc = rs.getString("prod_desc");
			memInfo = new MemberVO(prod_id,vend_id,prod_name,prod_price,prod_desc);
			pstmt.close();
			conn.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return memInfo;
	}
	/*
	 * public void aa(Strig id) { String query =
	 * "SELECT * FROM products WHERE prod_id LIKE '%?%'";
	 * 
	 * pstmt = conn.prepareStatement(query); pstmt.setString(1, id);
	 * System.out.println(query); 
	 * 
	 * pstmt = conn.prepareStatement(query);
	 * pstmt.setString(1, id);
	 * 	
	 * }
	 */
	public List<MemberVO> searchProd(String _id) {
		List<MemberVO> searchList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select * from Products p, Vendors v where p.vend_id=v.vend_id and prod_id LIKE "+_id;
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String prod_id = rs.getString("prod_id");
				System.out.println("서치된 아이디"+prod_id);
				String prod_name = rs.getString("prod_name");
				Double prod_price = rs.getDouble("prod_price");
				String prod_desc = rs.getString("prod_desc");
				String vend_id = rs.getString("vend_name");
				MemberVO memInfo = new MemberVO(prod_id,vend_id,prod_name,prod_price,prod_desc);
				searchList.add(memInfo);				
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return searchList;	
		
	}
	public void modprod(MemberVO m) {
		String prod_id = m.getProd_id();
		System.out.println("getid="+prod_id);
		String vend_id = m.getVend_id();
		System.out.println("getVend="+vend_id);
		String prod_name = m.getProd_name();
		System.out.println("getName="+prod_name);
		Double prod_price = m.getProd_price();
		System.out.println("getprice="+prod_price);
		String prod_desc = m.getProd_desc();
		System.out.println("getDesc="+prod_desc);
		try {
			conn = dataFactory.getConnection();
			String query = "UPDATE products set prod_name=?, prod_price=?, prod_desc=? where TRIM(prod_id)=?";
			System.out.println(query);

			pstmt = conn.prepareStatement(query);
//			pstmt.setString(1, prod_id);
//			pstmt.setString(2, vend_id);
			pstmt.setString(1, prod_name);
			pstmt.setDouble(2, prod_price);//Double
			pstmt.setString(3, prod_desc);
			System.out.println(prod_id);
			pstmt.setString(4, prod_id);
			int ret = pstmt.executeUpdate();
			System.out.println("수정완료:"+ret);
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}