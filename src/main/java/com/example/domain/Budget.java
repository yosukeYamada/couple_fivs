package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * 予算ドメイン
 * 
 * @author yosuke.yamada
 *
 */
public class Budget {

	private Integer id;
	private Integer householdId;
	private Timestamp date;
	List<IncomeBudget> incomeBudgetList;
	List<PaymentBudget> paymentBudgetList;
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
	public List<IncomeBudget> getIncomeBudgetList() {
		return incomeBudgetList;
	}
	public void setIncomeBudgetList(List<IncomeBudget> incomeBudgetList) {
		this.incomeBudgetList = incomeBudgetList;
	}
	public List<PaymentBudget> getPaymentBudgetList() {
		return paymentBudgetList;
	}
	public void setPaymentBudgetList(List<PaymentBudget> paymentBudgetList) {
		this.paymentBudgetList = paymentBudgetList;
	}
	@Override
	public String toString() {
		return "Budget [id=" + id + ", householdId=" + householdId + ", date=" + date + ", incomeBudgetList="
				+ incomeBudgetList + ", paymentBudgetList=" + paymentBudgetList + "]";
	}

}
