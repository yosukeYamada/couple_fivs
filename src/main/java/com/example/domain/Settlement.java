package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

public class Settlement {

	private Integer id;
	private Integer householdId;
	private Timestamp date;
	List<IncomeSettlement> incomeSettlementList;
	List<PaymentBudget> paymentPaymentList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getHouseholdId() {
		return householdId;
	}

	public void setHouseholdId(Integer householdId) {
		this.householdId = householdId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public List<IncomeSettlement> getIncomeSettlementList() {
		return incomeSettlementList;
	}

	public void setIncomeSettlementList(List<IncomeSettlement> incomeSettlementList) {
		this.incomeSettlementList = incomeSettlementList;
	}

	public List<PaymentBudget> getPaymentPaymentList() {
		return paymentPaymentList;
	}

	public void setPaymentPaymentList(List<PaymentBudget> paymentPaymentList) {
		this.paymentPaymentList = paymentPaymentList;
	}

	@Override
	public String toString() {
		return "Settlement [id=" + id + ", householdId=" + householdId + ", date=" + date + ", incomeSettlementList="
				+ incomeSettlementList + ", paymentPaymentList=" + paymentPaymentList + "]";
	}

}