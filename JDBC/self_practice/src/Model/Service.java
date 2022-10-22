package Model;

import java.sql.SQLException;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.ProductDAO;
import DAO.SellerDAO;
import DTO.CustomerDTO;
import DTO.OrderDTO;
import DTO.ProductDTO;
import DTO.SellerDTO;
import Exception.NotExistException;


public class Service {
	private static Service instance = new Service();
	
	private Service(){}
	
	public static Service getInstance() {
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
		public boolean deleteCustomer(String customer_id) throws SQLException, NotExistException{
			return CustomerDAO.deleteCustomer(customer_id);
		}
	
	// 고객 정보 변경
		public boolean updateCustomer(String name, String address, String phone, String id) throws SQLException, NotExistException{
			return CustomerDAO.updateCustomer(name, address, phone, id);
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
		
	// 상품 번호로 상품 정보 검색
		public ProductDTO getProduct(int ProductNumber) throws SQLException, NotExistException{
			return ProductDAO.getProduct(ProductNumber);
		}
		
	// 상품 번호로 상품 정보 삭제
		public boolean deleteProduct(int productNumber) throws SQLException, NotExistException{
			return ProductDAO.deleteProduct(productNumber);
		}	
				
	// 상품 추가
		public ProductDTO addProduct(ProductDTO product) throws SQLException, NotExistException{
			return ProductDAO.addProduct(product);
		}
		
	// 상품 번호로 판매자 검색
		public SellerDTO getSeller(int ProductNumber) throws SQLException, NotExistException{
			return SellerDAO.getSeller(ProductNumber);
		}
	
	// 판매자 정보 변경
		public boolean updateSeller(String name, String address, String phone, int productNumber) throws SQLException, NotExistException{
			return SellerDAO.updateSeller(name, address, phone, productNumber);
		}	
		
	// 판매자 이름으로 판매자 정보 삭제
		public boolean deleteSeller(String Seller_name) throws SQLException, NotExistException{
			return SellerDAO.deleteSeller(Seller_name);
		}	
						
	// 판매자 추가
		public SellerDTO addSeller(SellerDTO seller) throws SQLException, NotExistException{
			return SellerDAO.addSeller(seller);
		}

}
