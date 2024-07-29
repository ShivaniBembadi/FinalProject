package com.handloomstore.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user_table")
@SequenceGenerator(name = "generator1", sequenceName = "gen1", initialValue = 101)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator1")
	@Column(name = "user_id")
	private int userId;

	@Column(name = "first_name", length = 20)
	@NotEmpty
	@Size(min = 3, message = "firstName must contain atleast 3 characters")
	private String firstName;

	@Column(name = "last_name", length = 20)
	@NotEmpty
	@Size(min = 2, message = "lastName must contain atleast 2 characters")
	private String lastName;

	@Column(name = "phone_number")
	@NotEmpty
	@Size(min = 10, max = 10, message = "Phone Number must contain  10 digits")
	private String phoneNumber;

	@Column
	@NotEmpty
	@Size(min = 3, message = "Plot number must contain atleast 3 characters")
	private String plotNo;

	@Column
	@NotEmpty
	@Size(min = 3, message = "street name must contain atleast 3 characters")
	private String streetName;

	@Column
	@NotEmpty
	@Size(min = 3, message = "City name must contain atleast 3 characters")
	private String city;

	@Column(name = "district", length = 20)
	@NotEmpty
	@Size(min = 3, message = "district must contain atleast 3 characters")
	private String district;

	@Column(name = "state", length = 20)
	@NotEmpty
	@Size(min = 3, message = "state must contain atleast 3 characters")
	private String state;

	@Column(name = "zip_code")
	@NotEmpty
	@Size(min = 6, max = 6, message = "zipCode must contain 6 digits")
	private String zipCode;

	@Column(name = "email_id", unique = true, length = 30)
	@NotEmpty
	@Email(message = "Email  is not valid!")
	public String emailId;

	@Column(name = "gender", length = 30)
	@NotEmpty
	@Size(min = 4, message = "gender must contain atleast 4 characters")
	public String gender;

	@Column(name = "password", length = 20)
	@NotEmpty
	@Size(min = 8, message = "Password length must be 8 and contain uppercase,lowercase,digits")
	@Pattern(regexp = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
	public String password;

	public String role;
	
	
	
	
	//Mappings
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@JsonManagedReference
	private List<CartItem> cartItem;

	
	
	
	// Constructors
	public User() {
		super();
		
	}

	
	public User(int userId) {
		super();
		this.userId = userId;
	}

	
	
	
	// Getters and Setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPlotNo() {
		return plotNo;
	}

	public void setPlotNo(String plotNo) {
		this.plotNo = plotNo;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}


}
