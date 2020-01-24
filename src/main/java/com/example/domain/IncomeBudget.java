package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yosuke.yamada
 *
 */
public class IncomeBudget {

	private Integer id;
	private Integer budgetId;
	private String categoryName;
	private Integer amount;
	private Integer participantId;
	private Timestamp date;
	private List<Integer> incomeBudgetDetailIdList;
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
	public List<Integer> getIncomeBudgetDetailIdList() {
		return incomeBudgetDetailIdList;
	}
	public void setIncomeBudgetDetailIdList(List<Integer> incomeBudgetDetailIdList) {
		this.incomeBudgetDetailIdList = incomeBudgetDetailIdList;
	}
	@Override
	public String toString() {
		return "IncomeBudget [id=" + id + ", budgetId=" + budgetId + ", categoryName=" + categoryName + ", amount="
				+ amount + ", participantId=" + participantId + ", date=" + date + ", incomeBudgetDetailIdList="
				+ incomeBudgetDetailIdList + "]";
	}


}
