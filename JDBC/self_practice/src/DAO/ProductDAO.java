package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.ProductDTO;
import Util.DBUtil;

public class ProductDAO {
	
		// 상품 추가
		public static ProductDTO addProduct(ProductDTO Product) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("insert into project.상품 values(?, ?)");
				pstmt.setInt(1, Product.getProduct_number());
				pstmt.setString(2, Product.getProduct_name());
				int result = pstmt.executeUpdate();

				if (result == 1) {
					return Product;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return null;
		}
		
		// 상품번호를 통해서 상품 정보 삭제
		public static boolean deleteProduct(int productNumber) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("delete from project.상품 where 상품번호 = ?");
				pstmt.setInt(1, productNumber);
				int result = pstmt.executeUpdate();

				if (result == 1) {
					return true;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return false;
		}
		
		// 상품 정보 검색
		public static ProductDTO getProduct(int ProductNumber) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			ProductDTO user = null;

			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("select * from project.상품 where 상품번호=?");
				pstmt.setInt(1, ProductNumber);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					user = new ProductDTO(rset.getInt(1), rset.getString(2)); // 숫자 = 몇번째 컬럼의 데이터를 불러올지 지정
				}
			} finally {
				DBUtil.close(con, pstmt, rset);
			}
			return user;
		}
	
	
}
