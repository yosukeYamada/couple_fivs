package com.example.form;

import java.util.List;

public class BudgetForm {

	private String date;
	private String householdId;
	private List<String> eachIncomeCategoryNameList;
	private List<String> eachPaymentCategoryNameList;
	private List<Integer> eachIncomeAmountList;
	private List<Integer> eachPaymentAmountList;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHouseholdId() {
		return householdId;
	}

	public void setHouseholdId(String householdId) {
		this.householdId = householdId;
	}

	public List<String> getEachIncomeCategoryNameList() {
		return eachIncomeCategoryNameList;
	}

	public void setEachIncomeCategoryNameList(List<String> eachIncomeCategoryNameList) {
		this.eachIncomeCategoryNameList = eachIncomeCategoryNameList;
	}

	public List<String> getEachPaymentCategoryNameList() {
		return eachPaymentCategoryNameList;
	}

	public void setEachPaymentCategoryNameList(List<String> eachPaymentCategoryNameList) {
		this.eachPaymentCategoryNameList = eachPaymentCategoryNameList;
	}

	public List<Integer> getEachIncomeAmountList() {
		return eachIncomeAmountList;
	}

	public void setEachIncomeAmountList(List<Integer> eachIncomeAmountList) {
		this.eachIncomeAmountList = eachIncomeAmountList;
	}

	public List<Integer> getEachPaymentAmountList() {
		return eachPaymentAmountList;
	}

	public void setEachPaymentAmountList(List<Integer> eachPaymentAmountList) {
		this.eachPaymentAmountList = eachPaymentAmountList;
	}

	@Override
	public String toString() {
		return "BudgetForm [date=" + date + ", householdId=" + householdId + ", eachIncomeCategoryNameList="
				+ eachIncomeCategoryNameList + ", eachPaymentCategoryNameList=" + eachPaymentCategoryNameList
				+ ", eachIncomeAmountList=" + eachIncomeAmountList + ", eachPaymentAmountList=" + eachPaymentAmountList
				+ "]";
	}

}
