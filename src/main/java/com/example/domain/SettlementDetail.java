package com.example.domain;

public class SettlementDetail {

	private Integer id;
	private Integer settlementId;
	private Integer payeeId;
	private String itemName;
	private Integer payment;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSettlementId() {
		return settlementId;
	}
	public void setSettlementId(Integer settlementId) {
		this.settlementId = settlementId;
	}
	public Integer getPayeeId() {
		return payeeId;
	}
	public void setPayeeId(Integer payeeId) {
		this.payeeId = payeeId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
	@Override
	public String toString() {
		return "SettlementDetail [id=" + id + ", settlementId=" + settlementId + ", payeeId=" + payeeId + ", itemName="
				+ itemName + ", payment=" + payment + "]";
	}
	
	
	
}
