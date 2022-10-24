package service;

import java.sql.SQLException;

import dao.SellerDAO;
import dto.SellerDTO;
import exception.NotExistException;

public class SellerService {
	private static SellerService instance = new SellerService();
	
	private SellerService(){}
	
	public static SellerService getInstance() {
		return instance;
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
	public boolean deleteSeller(String SellerName) throws SQLException, NotExistException{
		return SellerDAO.deleteSeller(SellerName);
	}	
						
	// 판매자 추가
	public SellerDTO addSeller(SellerDTO seller) throws SQLException, NotExistException{
		return SellerDAO.addSeller(seller);
	}		
}
