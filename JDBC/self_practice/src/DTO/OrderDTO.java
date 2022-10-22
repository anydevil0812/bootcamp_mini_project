package DTO;

import java.sql.Date; // java.utill.Date이 아닌 java.sql.Date로!

public class OrderDTO {
	private String customer_id;
	private String product_name;
	private int order_number;
	private int order_quantity;
	private Date order_date;
	
	public OrderDTO(String customer_id, String product_name, int order_number, int order_quantity, Date order_date) {
		super();
		this.customer_id = customer_id;
		this.product_name = product_name;
		this.order_number = order_number;
		this.order_quantity = order_quantity;
		this.order_date = order_date;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getOrder_number() {
		return order_number;
	}

	public void setOrder_number(int order_number) {
		this.order_number = order_number;
	}

	public int getOrder_quantity() {
		return order_quantity;
	}

	public void setOrder_quantity(int order_quantity) {
		this.order_quantity = order_quantity;
	}

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "[고객 아이디=" + customer_id + ", 상품명=" + product_name + ", 주문 번호="
				+ order_number + ", 주문 수량=" + order_quantity + ", 주문 날짜=" + order_date + "]";
	}
	
}
