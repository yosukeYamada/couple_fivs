package com.example.domain;

public class Payee {

	private Integer id;
	private String name;
	private Integer householdId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getHouseholdId() {
		return householdId;
	}
	public void setHouseholdId(Integer householdId) {
		this.householdId = householdId;
	}
	@Override
	public String toString() {
		return "Payee [id=" + id + ", name=" + name + ", householdId=" + householdId + "]";
	}
	
	
	
	
}
