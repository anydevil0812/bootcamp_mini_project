package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.CustomerDTO;
import DTO.SellerDTO;
import Util.DBUtil;

public class SellerDAO {
	
		// 판매자 추가
		public static SellerDTO addSeller(SellerDTO Seller) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("insert into project.판매자 values(?, ?, ?, ?)");
				pstmt.setInt(1, Seller.getProduct_number());
				pstmt.setString(2, Seller.getSeller_name());
				pstmt.setString(3, Seller.getSeller_address());
				pstmt.setString(4, Seller.getSeller_phone());
			
				int result = pstmt.executeUpdate();

				if (result == 1) {
					return Seller;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return null;
		}
		
		// 판매자 정보 수정
		public static boolean updateSeller(String name, String address, String phone, int productNumber) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			CustomerDTO user = null;
			
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("update project.판매자 set 판매자_이름=?, 판매자_주소=?, 판매자_연락처=? where 상품번호=?");
				pstmt.setString(1, name);
				pstmt.setString(2, address);
				pstmt.setString(3, phone);
				pstmt.setInt(4, productNumber);
				
				boolean result = pstmt.execute();

				if (result == true) {
					return result;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return false;
		}
		
		
		// 판매자 이름을 통해서 판매자 정보 삭제
		public static boolean deleteSeller(String Seller_name) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("delete from project.판매자 where 판매자_이름 = ?");
				pstmt.setString(1, Seller_name);
				int result = pstmt.executeUpdate();

				if (result == 1) {
					return true;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return false;
		}
		
		// 판매자 정보 검색
		public static SellerDTO getSeller(int ProductNumber) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			SellerDTO user = null;

			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("select * from project.판매자 where 상품번호=?");
				pstmt.setInt(1, ProductNumber);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					user = new SellerDTO(rset.getInt(1), rset.getString(2), rset.getString(3),
							rset.getString(4)); // 숫자 = 몇번째 컬럼의 데이터를 불러올지 지정
				}
			} finally {
				DBUtil.close(con, pstmt, rset);
			}
			return user;
		}
}
