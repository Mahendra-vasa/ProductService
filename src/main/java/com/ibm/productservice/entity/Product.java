package com.ibm.productservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "product")
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    @NotEmpty
    private String name;
    @Column(name = "description")
    private String desc;
    @Column(name = "price")
    private float price;
    @Column(name = "creation_dt")
    private String creation_Date;
    
	public Product(int id, String name, String desc, float price, String creation_Date) {
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.creation_Date = creation_Date;
	}
	public Product() {
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getCreation_Date() {
		return creation_Date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public void setCreation_Date(String creation_Date) {
		this.creation_Date = creation_Date;
	}

   
}
