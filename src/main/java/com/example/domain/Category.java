package com.example.domain;

public class Category {

	private Integer id;
	private String categoryName;
	private Integer householdId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getHouseholdId() {
		return householdId;
	}
	public void setHouseholdId(Integer householdId) {
		this.householdId = householdId;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", categoryName=" + categoryName + ", householdId=" + householdId + "]";
	}
	
	
	
	
}
