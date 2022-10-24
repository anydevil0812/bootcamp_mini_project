package dto;

public class CustomerDTO {
	private String customerId;
	private String customerName;
	private String customerAddress;
	private String customerPhone;
	
	public CustomerDTO(String customerId, String customerName, String customerAddress, String customerPhone) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.customerPhone = customerPhone;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	@Override
	public String toString() {
		return "[고객 아이디=" + customerId + ", 고객 이름=" + customerName + ", 고객 주소="
				+ customerAddress + ", 고객 연락처=" + customerPhone + "]";
	}
	
}
