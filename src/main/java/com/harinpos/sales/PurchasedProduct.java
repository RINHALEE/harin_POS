package com.harinpos.sales;

import java.time.LocalDateTime;

public class PurchasedProduct {
	private int id;
	private int code;
	private String name;
	private double price;
	private int quantity;
	private LocalDateTime date;

	public PurchasedProduct(int id,int code, String name, double price, int quantity, LocalDateTime date) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
	}
	
	public PurchasedProduct(int code, String name, double price, int quantity, LocalDateTime date) {
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
