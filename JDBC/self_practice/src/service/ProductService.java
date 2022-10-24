package service;

import java.sql.SQLException;

import dao.ProductDAO;
import dto.ProductDTO;
import exception.NotExistException;

public class ProductService {
	private static ProductService instance = new ProductService();
	
	private ProductService(){}
	
	public static ProductService getInstance() {
		return instance;
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
}
