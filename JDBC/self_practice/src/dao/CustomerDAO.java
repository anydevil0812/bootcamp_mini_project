package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.CustomerDTO;
import util.DBUtil;

public class CustomerDAO {
	
	// 고객 추가
	public static CustomerDTO addCustomer(CustomerDTO customer) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("insert into project.고객 values(?, ?, ?, ?)");
			pstmt.setString(1, customer.getCustomerId());
			pstmt.setString(2, customer.getCustomerName());
			pstmt.setString(3, customer.getCustomerAddress());
			pstmt.setString(4, customer.getCustomerPhone());
		
			int result = pstmt.executeUpdate();

			if (result == 1) {
				return customer;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return null;
	}
	
	// 고객 정보 수정
	public static boolean updateCustomer(String name, String address, String phone, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("update project.고객 set 고객_이름=?, 고객_주소=?, 고객_연락처=? where 고객_아이디=?");
			pstmt.setString(1, name);
			pstmt.setString(2, address);
			pstmt.setString(3, phone);
			pstmt.setString(4, id);
			
			boolean result = pstmt.execute();

			if (result == true) {
				return result;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 고객 정보 삭제
	public static boolean deleteCustomer(String customerId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("delete from project.고객 where 고객_아이디 = ?");
			pstmt.setString(1, customerId);
			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} finally {
			DBUtil.close(con, pstmt);
		}
		return false;
	}
	
	// 고객 정보 검색
	public static CustomerDTO getCustomer(String customerName) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		CustomerDTO user = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("select * from project.고객 where 고객_아이디=?");
			pstmt.setString(1, customerName);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				user = new CustomerDTO(rset.getString(1), rset.getString(2), rset.getString(3),
						rset.getString(4)); // 숫자 = 몇번째 컬럼의 데이터를 불러올지 지정
			}
		} finally {
			DBUtil.close(con, pstmt, rset);
		}
		return user;
	}
	
}
