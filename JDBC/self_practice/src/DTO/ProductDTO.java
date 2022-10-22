package DTO;

public class ProductDTO {
	private int product_number;
	private String product_name;
	
	public ProductDTO(int product_number, String product_name) {
		super();
		this.product_number = product_number;
		this.product_name = product_name;
	}

	public int getProduct_number() {
		return product_number;
	}

	public void setProduct_number(int product_number) {
		this.product_number = product_number;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	@Override
	public String toString() {
		return "[상품 번호=" + product_number + ", 상품명=" + product_name + "]";
	}	
	
}
