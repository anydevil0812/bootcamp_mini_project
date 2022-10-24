package service;

import java.sql.SQLException;

import dao.OrderDAO;
import dto.OrderDTO;
import exception.NotExistException;

public class OrderService {
	private static OrderService instance = new OrderService();
	
	private OrderService(){}
	
	public static OrderService getInstance() {
		return instance;
	}
	
	// 주문 번호로 주문 정보 검색
	public OrderDTO searchOrder(int orderNumber) throws SQLException, NotExistException{
		return OrderDAO.searchOrder(orderNumber);
	}
	
	// 주문 번호로 주문 정보 삭제
	public boolean deleteOrder(int orderNumber) throws SQLException, NotExistException{
		return OrderDAO.deleteOrder(orderNumber);
	}	
		
	// 주문 정보 추가
	public OrderDTO addOrder(OrderDTO order) throws SQLException, NotExistException{
		return OrderDAO.addOrder(order);
	}
}
