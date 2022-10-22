package View;

import DTO.CustomerDTO;
import DTO.OrderDTO;
import DTO.ProductDTO;
import DTO.SellerDTO;

public class EndView {
	
	// 고객 정보 출력 
		public static void customerView(CustomerDTO customer){
			System.out.println(customer);		
		}
		
	// 고객 정보 추가 
		public static void customerAdd(CustomerDTO customer){
			System.out.println("고객 정보 추가 성공");
			System.out.println(customer);		
		}
	
	// 고객 정보 삭제
		public static void customerDelete(boolean result){
			if (result = true) {
				System.out.println("고객 정보 삭제 성공");	
			}
			else {
				System.out.println("삭제 실패");
			}
		}	
	
	// 고객 정보 변경	
		public static void customerUpdate(boolean result){
			if (result = true) {
				System.out.println("고객 정보 변경 성공");	
			}
			else {
				System.out.println("고객 정보 삭제 실패");
			}
		}	
		
	// 주문 정보 출력 
		public static void orderView(OrderDTO order){
			System.out.println(order);		
		}	
	
	// 주문 정보 추가 
		public static void orderAdd(OrderDTO order){
			System.out.println("고객 정보 추가 성공");
			System.out.println(order);		
		}	
	
	// 주문 정보 삭제
		public static void orderDelete(boolean result){
			if (result = true) {
				System.out.println("주문 정보 삭제 성공");	
			}
			else {
				System.out.println("주문 정보 삭제 실패");
			}
		}		
	
	// 상품 정보 출력 
		public static void productView(ProductDTO product){
			System.out.println(product);		
		}
	
	// 상품 정보 추가 
		public static void prodcutAdd(ProductDTO product){
			System.out.println("고객 정보 추가 성공");
			System.out.println(product);		
		}	
			
	// 상품 정보 삭제
		public static void productDelete(boolean result){
			if (result = true) {
				System.out.println("주문 정보 삭제 성공");	
			}
			else {
				System.out.println("주문 정보 삭제 실패");
			}
		}		
		
	// 판매자 정보 출력 
		public static void sellerView(SellerDTO product){
			System.out.println(product);		
		}
		
	// 판매자 정보 추가 
		public static void sellerAdd(SellerDTO seller){
			System.out.println("고객 정보 추가 성공");
			System.out.println(seller);		
		}
		
	// 판매자 정보 삭제
		public static void sellerDelete(boolean result){
			if (result = true) {
				System.out.println("고객 정보 삭제 성공");	
			}
			else {
				System.out.println("삭제 실패");
			}
		}	
			
	// 판매자 정보 변경	
		public static void sellerUpdate(boolean result){
			if (result = true) {
				System.out.println("고객 정보 변경 성공");	
			}
			else {
				System.out.println("고객 정보 삭제 실패");
			}
		}	
}
