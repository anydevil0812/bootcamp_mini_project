package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DTO.CustomerDTO;
import DTO.OrderDTO;
import Util.DBUtil;

public class OrderDAO {

		// 주문 추가
		public static OrderDTO addOrder(OrderDTO Order) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("insert into project.주문 values(?, ?, ?, ?, ?)");
				pstmt.setString(1, Order.getCustomer_id());
				pstmt.setString(2, Order.getProduct_name());
				pstmt.setInt(3, Order.getOrder_number());
				pstmt.setInt(4, Order.getOrder_quantity());
				pstmt.setDate(5, Order.getOrder_date());
			
				int result = pstmt.executeUpdate();

				if (result == 1) {
					return Order;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return null;
		}
		
		// 주문번호를 통해서 주문 정보 삭제
		public static boolean deleteOrder(int orderNumber) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("delete from project.주문 where 주문_번호 = ?");
				pstmt.setInt(1, orderNumber);
				int result = pstmt.executeUpdate();

				if (result == 1) {
					return true;
				}
			} finally {
				DBUtil.close(con, pstmt);
			}
			return false;
		}
		
		// 주문 정보 검색
		public static OrderDTO searchOrder(int ordernumber) throws SQLException {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			OrderDTO user = null;

			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement("select * from project.주문 where 주문_번호=?");
				pstmt.setInt(1, ordernumber);
				rset = pstmt.executeQuery();
				if (rset.next()) {
					user = new OrderDTO(rset.getString(1), rset.getString(2), rset.getInt(3),
							rset.getInt(4), rset.getDate(5)); // 숫자 = 몇번째 컬럼의 데이터를 불러올지 지정
				}
			} finally {
				DBUtil.close(con, pstmt, rset);
			}
			return user;
		}
		
}
