package com.handloomstore.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;


@Entity
@Table(name = "ProductSubCategory")
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subCategoryId;

    @Column
    private String subCategoryName;

    
    //Mapping
    @ManyToOne(fetch = FetchType.EAGER)     //multiple subcategories can have one category
    @JsonBackReference
    @JoinColumn(name = "pc_id")
    private Category category;
    

   
    //Getters and Setters
	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
	//Constructor
	public SubCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
}