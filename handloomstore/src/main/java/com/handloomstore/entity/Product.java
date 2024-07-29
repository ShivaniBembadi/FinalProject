package com.handloomstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "product_table")
@SequenceGenerator(name = "generator2", sequenceName = "gen2", initialValue = 5000)
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator2")
	private int productId;

	@Column(name = "product_name")
	@NotEmpty
	private String name;

	@Column(name = "product_price")
	private double price;

	@Column(name = "product_description")
	@NotEmpty
	private String description;

	@Column
	private String image;

	@Column(name = "quantity")
	private int quantity;

	// Mappings

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "sub_catagory_id")
	private SubCategory subCategory;

	// Getters and Setters
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	// Constructor
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

}
