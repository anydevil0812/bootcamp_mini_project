package dto;

public class SellerDTO {
	private int productNumber;
	private String sellerName;
	private String sellerAddress;
	private String sellerPhone;
	
	public SellerDTO(int productNumber, String sellerName, String sellerAddress, String sellerPhone) {
		super();
		this.productNumber = productNumber;
		this.sellerName = sellerName;
		this.sellerAddress = sellerAddress;
		this.sellerPhone = sellerPhone;
	}

	public int getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public String getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(String sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	@Override
	public String toString() {
		return "[상품 번호=" + productNumber + ", 판매자 이름=" + sellerName + ", 판매자 주소="
				+ sellerAddress + ", 판매자 연락처=" + sellerPhone + "]";
	}
	
}
