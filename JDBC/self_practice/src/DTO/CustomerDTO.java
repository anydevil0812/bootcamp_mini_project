package DTO;

public class CustomerDTO {
	private String customer_id;
	private String customer_name;
	private String customer_address;
	private String customer_phone;
	
	public CustomerDTO(String customer_id, String customer_name, String customer_address, String customer_phone) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_address = customer_address;
		this.customer_phone = customer_phone;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	@Override
	public String toString() {
		return "[고객 아이디=" + customer_id + ", 고객 이름=" + customer_name + ", 고객 주소="
				+ customer_address + ", 고객 연락처=" + customer_phone + "]";
	}
	
}
