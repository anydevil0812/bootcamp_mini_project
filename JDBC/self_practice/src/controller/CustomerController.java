package controller;

import java.sql.SQLException;

import dto.CustomerDTO;
import exception.NotExistException;
import service.CustomerService;
import view.EndView;

public class CustomerController {
	private static CustomerController instance = new CustomerController();
	private CustomerService customerService = CustomerService.getInstance();
	
	private CustomerController() {}
	
	public static CustomerController getInstance() {
		return instance;
	}
	
	// 고객 검색
	public void getCustomer(String customerName) {
		try {
			EndView.customerView(customerService.getCustomer(customerName));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 고객 추가
	public void addCustomer(CustomerDTO customer) {
		try {
			EndView.customerAdd(customerService.addCustomer(customer));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 고객 삭제
	public void deleteCustomer(String customerId) {
		try {
			EndView.customerDelete(customerService.deleteCustomer(customerId));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 고객 정보 변경
	public void updateCustomer(String name, String address, String phone, String id) {
		try {
			EndView.customerUpdate(customerService.updateCustomer(name, address, phone, id));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
}