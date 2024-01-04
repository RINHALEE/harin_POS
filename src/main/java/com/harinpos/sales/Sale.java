package com.harinpos.sales;

import java.time.LocalDateTime;

public class Sale {
	private int code;
	private String productName; 
	private LocalDateTime sale_date;
	private int sale_quantity;
	private double sales;
	
	public Sale(int code, String productName, int sale_quantity, LocalDateTime sale_date, double sales) {
		this.code = code;
		this.productName = productName;
		this.sale_quantity = sale_quantity;
		this.sale_date = sale_date;
		this.sales = sales;
	}
	
	public Sale() {
	  }

	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public LocalDateTime getSale_date() {
		return sale_date;
	}
	public void setSale_date(LocalDateTime sale_date) {
		this.sale_date = sale_date;
	}
	public int getSale_quantity() {
		return sale_quantity;
	}
	public void setSale_quantity(int sale_quantity) {
		this.sale_quantity = sale_quantity;
	}
	public double getSales() {
		return sales;
	}
	public void setSales(double sales) {
		this.sales = sales;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
