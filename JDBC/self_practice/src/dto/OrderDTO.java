package dto;

import java.sql.Date; // java.utill.Date이 아닌 java.sql.Date로!

public class OrderDTO {
	private String customerId;
	private String productName;
	private int orderNumber;
	private int orderQuantity;
	private Date orderDate;
	
	public OrderDTO(String customerId, String productName, int orderNumber, int orderQuantity, Date orderDate) {
		super();
		this.customerId = customerId;
		this.productName = productName;
		this.orderNumber = orderNumber;
		this.orderQuantity = orderQuantity;
		this.orderDate = orderDate;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public int getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(int orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Override
	public String toString() {
		return "[고객 아이디=" + customerId + ", 상품명=" + productName + ", 주문 번호="
				+ orderNumber + ", 주문 수량=" + orderQuantity + ", 주문 날짜=" + orderDate + "]";
	}
	
}
