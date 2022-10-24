package dto;

public class ProductDTO {
	private int productNumber;
	private String productName;
	
	public ProductDTO(int productNumber, String productName) {
		super();
		this.productNumber = productNumber;
		this.productName = productName;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "[상품 번호=" + productNumber + ", 상품명=" + productName + "]";
	}	
	
}
