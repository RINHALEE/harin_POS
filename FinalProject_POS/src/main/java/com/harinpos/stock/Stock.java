package com.harinpos.stock;

import java.time.LocalDateTime;

public class Stock {
	private int code;
	private LocalDateTime receive_date;
	private int receive_quantity;
	private String productName; 
	
	public Stock(int code, LocalDateTime receive_date, int receive_quantity) {
		this.code = code;
		this.receive_date = receive_date;
		this.receive_quantity = receive_quantity;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public LocalDateTime getReceive_date() {
		return receive_date;
	}

	public void setReceive_date(LocalDateTime receive_date) {
		this.receive_date = receive_date;
	}

	public int getReceive_quantity() {
		return receive_quantity;
	}

	public void setReceive_quantity(int receive_quantity) {
		this.receive_quantity = receive_quantity;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}

}
