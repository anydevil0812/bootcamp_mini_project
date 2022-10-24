package service;

import java.sql.SQLException;

import dao.CustomerDAO;
import dto.CustomerDTO;
import exception.NotExistException;

public class CustomerService {
	private static CustomerService instance = new CustomerService();
	
	private CustomerService(){}
	
	public static CustomerService getInstance() {
		return instance;
	}
	
	// 고객 정보 검색
	public CustomerDTO getCustomer(String customerName) throws SQLException, NotExistException{
		return CustomerDAO.getCustomer(customerName);
	}
		
	// 고객 정보 추가
	public CustomerDTO addCustomer(CustomerDTO customer) throws SQLException, NotExistException{
		return CustomerDAO.addCustomer(customer);
	}
		
	// 고객 정보 삭제
	public boolean deleteCustomer(String customerId) throws SQLException, NotExistException{
		return CustomerDAO.deleteCustomer(customerId);
	}
	
	// 고객 정보 변경
	public boolean updateCustomer(String name, String address, String phone, String id) throws SQLException, NotExistException{
		return CustomerDAO.updateCustomer(name, address, phone, id);
	}			
}
