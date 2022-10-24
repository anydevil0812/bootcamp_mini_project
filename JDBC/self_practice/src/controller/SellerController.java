package controller;

import java.sql.SQLException;

import dto.SellerDTO;
import exception.NotExistException;
import service.SellerService;
import view.EndView;

public class SellerController {
	private static SellerController instance = new SellerController();
	private SellerService sellerService = SellerService.getInstance();
	
	private SellerController() {}
	
	public static SellerController getInstance() {
		return instance;
	}
	
	// 판매자 검색
	public void getSeller(int ProductNumber) {
		try {
			EndView.sellerView(sellerService.getSeller(ProductNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
		
	// 판매자 추가
	public void addSeller(SellerDTO seller) {
		try {
			EndView.sellerAdd(sellerService.addSeller(seller));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 판매자 삭제
	public void deleteSeller(String sellerName) {
		try {
			EndView.sellerDelete(sellerService.deleteSeller(sellerName));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}
	
	// 판매자 정보 변경
	public void updateSeller(String name, String address, String phone, int productNumber) {
		try {
			EndView.sellerUpdate(sellerService.updateSeller(name, address, phone, productNumber));
		} catch (SQLException | NotExistException e) {
			e.printStackTrace();
		} 
	}	
}