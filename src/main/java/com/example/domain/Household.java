package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

public class Household {

	private Integer id;
	private Timestamp date;
	private String householdName;
	private Integer userId;
	private List<Budget> budgetList;
	private List<Settlement> settlementList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getHouseholdName() {
		return householdName;
	}
	public void setHouseholdName(String householdName) {
		this.householdName = householdName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Budget> getBudgetList() {
		return budgetList;
	}
	public void setBudgetList(List<Budget> budgetList) {
		this.budgetList = budgetList;
	}
	public List<Settlement> getSettlementList() {
		return settlementList;
	}
	public void setSettlementList(List<Settlement> settlementList) {
		this.settlementList = settlementList;
	}
	@Override
	public String toString() {
		return "Household [id=" + id + ", date=" + date + ", householdName=" + householdName + ", userId=" + userId
				+ ", budgetList=" + budgetList + ", settlementList=" + settlementList + "]";
	}

	
	
	
}
