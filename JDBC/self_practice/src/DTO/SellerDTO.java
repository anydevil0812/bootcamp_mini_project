package DTO;

public class SellerDTO {
	private int product_number;
	private String seller_name;
	private String seller_address;
	private String seller_phone;
	
	public SellerDTO(int product_number, String seller_name, String seller_address, String seller_phone) {
		super();
		this.product_number = product_number;
		this.seller_name = seller_name;
		this.seller_address = seller_address;
		this.seller_phone = seller_phone;
	}

	public int getProduct_number() {
		return product_number;
	}

	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_address() {
		return seller_address;
	}

	public void setSeller_address(String seller_address) {
		this.seller_address = seller_address;
	}

	public String getSeller_phone() {
		return seller_phone;
	}

	public void setSeller_phone(String seller_phone) {
		this.seller_phone = seller_phone;
	}

	@Override
	public String toString() {
		return "[상품 번호=" + product_number + ", 판매자 이름=" + seller_name + ", 판매자 주소="
				+ seller_address + ", 판매자 연락처=" + seller_phone + "]";
	}
	
}
