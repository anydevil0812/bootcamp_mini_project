package controller;

import java.sql.SQLException;

import dto.OrderDTO;
import exception.NotExistException;
import service.OrderService;
import view.EndView;

public class OrderController {
	private static OrderController instance = new OrderController();
	private OrderService orderService = OrderService.getInstance();
	
	private OrderController() {}
	
	public static OrderController getInstance() {
		return instance;
	}
	
	// 주문 검색
	public void searchOrder(int orderNumber) {
		try {
			EndView.orderView(orderService.searchOrder(orderNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 주문 추가
	public void addOrder(OrderDTO order) {
		try {
			EndView.orderAdd(orderService.addOrder(order));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 주문 삭제
	public void deleteOrder(int orderNumber) {
		try {
			EndView.orderDelete(orderService.deleteOrder(orderNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
}