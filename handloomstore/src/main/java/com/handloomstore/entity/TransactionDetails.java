package com.handloomstore.entity;

public class TransactionDetails {
	private String orderId;
	private String currency;
	private int amount;
	private String key;

	// Constructor
	public TransactionDetails(String orderId, String currency, int amount, String key) {
		this.orderId = orderId;
		this.currency = currency;
		this.amount = amount;
		this.key = key;
	}

	// Getters and Setters
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
