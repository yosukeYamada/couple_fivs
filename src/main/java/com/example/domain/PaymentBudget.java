package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

public class PaymentBudget {

	private Integer id;
	private Integer budgetId;
	private String categoryName;
	private Integer amount;
	private Integer participantId;
	private Timestamp date;
	private List<Integer> paymentBudgetDetailIdList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getParticipantId() {
		return participantId;
	}

	public void setParticipantId(Integer participantId) {
		this.participantId = participantId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<Integer> getPaymentBudgetDetailIdList() {
		return paymentBudgetDetailIdList;
	}

	public void setPaymentBudgetDetailIdList(List<Integer> paymentBudgetDetailIdList) {
		this.paymentBudgetDetailIdList = paymentBudgetDetailIdList;
	}

	@Override
	public String toString() {
		return "PaymentBudget [id=" + id + ", budgetId=" + budgetId + ", categoryName=" + categoryName + ", amount="
				+ amount + ", participantId=" + participantId + ", date=" + date + ", paymentBudgetDetailIdList="
				+ paymentBudgetDetailIdList + "]";
	}

}
