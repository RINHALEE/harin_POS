package com.harinpos.product;

public class Product {
	private int code;
	private double price;
	private int quantity;
	private String name;

	public Product(int code, double price, int quantity, String name) {
		this.code = code;
		this.price = price;
		this.quantity = quantity;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
