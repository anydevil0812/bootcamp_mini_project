package Controller;

import java.sql.SQLException;

import DTO.CustomerDTO;
import DTO.OrderDTO;
import DTO.ProductDTO;
import DTO.SellerDTO;
import Exception.NotExistException;
import Model.Service;
import View.EndView;


public class ProjectController {
	private static ProjectController instance = new ProjectController();
	private Service service = Service.getInstance();
	
	private ProjectController() {}
	
	public static ProjectController getInstance() {
		return instance;
	}
	
	// 고객 검색
	public void getCustomer(String customerName) {
		try {
			EndView.customerView(service.getCustomer(customerName));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 고객 추가
	public void addCustomer(CustomerDTO customer) {
		try {
			EndView.customerAdd(service.addCustomer(customer));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 고객 삭제
	public void deleteCustomer(String customer_id) {
		try {
			EndView.customerDelete(service.deleteCustomer(customer_id));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 고객 정보 변경
	public void updateCustomer(String name, String address, String phone, String id) {
		try {
			EndView.customerUpdate(service.updateCustomer(name, address, phone, id));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 주문 검색
	public void searchOrder(int orderNumber) {
		try {
			EndView.orderView(service.searchOrder(orderNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 주문 추가
		public void addOrder(OrderDTO order) {
			try {
				EndView.orderAdd(service.addOrder(order));
			} catch (SQLException | NotExistException e) {
				e.printStackTrace();
			} 
		}
	
	// 주문 삭제
		public void deleteOrder(int orderNumber) {
			try {
				EndView.orderDelete(service.deleteOrder(orderNumber));
			} catch (SQLException | NotExistException e) {
				e.printStackTrace();
			} 
		}
		
	// 상품 검색
	public void getProduct(int ProductNumber) {
		try {
			EndView.productView(service.getProduct(ProductNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 상품 추가
	public void addProduct(ProductDTO product) {
		try {
			EndView.prodcutAdd(service.addProduct(product));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
		
	// 상품 삭제
	public void deleteProduct(int productNumber) {
		try {
			EndView.productDelete(service.deleteProduct(productNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 판매자 검색
		public void getSeller(int ProductNumber) {
			try {
				EndView.sellerView(service.getSeller(ProductNumber));
			} catch (SQLException | NotExistException e) {
				e.printStackTrace();
			} 
		}
		
	// 판매자 추가
	public void addSeller(SellerDTO seller) {
		try {
			EndView.sellerAdd(service.addSeller(seller));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 판매자 삭제
	public void deleteSeller(String seller_name) {
		try {
			EndView.sellerDelete(service.deleteSeller(seller_name));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 판매자 정보 변경
	public void updateSeller(String name, String address, String phone, int productNumber) {
		try {
			EndView.sellerUpdate(service.updateSeller(name, address, phone, productNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
}