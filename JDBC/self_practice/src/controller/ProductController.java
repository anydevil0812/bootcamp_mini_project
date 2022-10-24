package controller;

import java.sql.SQLException;

import dto.ProductDTO;
import exception.NotExistException;
import service.ProductService;
import view.EndView;

public class ProductController {
	private static ProductController instance = new ProductController();
	private ProductService productService = ProductService.getInstance();
	
	private ProductController() {}
	
	public static ProductController getInstance() {
		return instance;
	}
	
	// 상품 검색
	public void getProduct(int ProductNumber) {
		try {
			EndView.productView(productService.getProduct(ProductNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 상품 추가
	public void addProduct(ProductDTO product) {
		try {
			EndView.prodcutAdd(productService.addProduct(product));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
		
	// 상품 삭제
	public void deleteProduct(int productNumber) {
		try {
			EndView.productDelete(productService.deleteProduct(productNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
}