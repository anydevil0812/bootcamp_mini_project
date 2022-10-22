package View;

import Controller.ProjectController;
import DTO.CustomerDTO;
import DTO.OrderDTO;
import DTO.ProductDTO;
import DTO.SellerDTO;

public class StartView {

	public static void main(String[] args) {
		ProjectController controller = ProjectController.getInstance();
		
		System.out.println("-----고객 정보 검색-----");
		controller.getCustomer("metacoda"); // 아이디 받아서 고객 정보 출력
		
		System.out.println("-----고객 추가-----");
		controller.addCustomer(new CustomerDTO ("master04", "백준", "대전", "010-2324-1433"));
		
		System.out.println("-----고객 삭제-----");
		controller.deleteCustomer("master03"); // 아이디를 받아서 고객 정보 삭제
		
		System.out.println("-----고객 정보 수정-----");
		controller.updateCustomer("이수민", "서울", "010-6235-4232", "dlsi142"); // 아이디를 받아서 나머지 정보 수정
		
		System.out.println("-----주문 정보 검색-----");
		controller.searchOrder(12395); //주문번호 받아서 주문 정보 출력
		
		System.out.println("-----주문 추가-----");
		controller.addOrder(new OrderDTO ("metacoda", "서랍장", 71393, 5, java.sql.Date.valueOf("2022-10-18")));
		
		System.out.println("-----주문 삭제-----");
		controller.deleteOrder(612021); // 를 받아서 고객 정보 삭제
		
		System.out.println("-----상품 정보 검색-----");
		controller.getProduct(508012); // 상품번호 받아서 상품 정보 출력
		
		System.out.println("-----상품 추가-----");
		controller.addProduct(new ProductDTO (810443, "의자"));
		
		System.out.println("-----상품 삭제-----");
		controller.deleteProduct(612021); // 를 받아서 고객 정보 삭제
		
		System.out.println("-----판매자 정보 검색-----");
		controller.getSeller(508012);
			
		System.out.println("-----판매자 추가-----");
		controller.addSeller(new SellerDTO (562522, "파이썬", "전남", "061-642-3412"));
		
		System.out.println("-----판매자 삭제-----");
		controller.deleteSeller("master02"); // 판매자 이름을 받아서 판매자 정보 삭제
		
		System.out.println("-----판매자 정보 수정-----");
		controller.updateSeller("회사1", "서울", "02-635-4192", 352121); // 상품번호를 받아서 나머지 정보 수정
	}
}
