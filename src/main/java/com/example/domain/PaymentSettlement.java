package com.example.domain;

import java.sql.Timestamp;
import java.util.List;

public class PaymentSettlement {

	private Integer id;
	private Integer SettlementId;
	private String categoryName;
	private Integer amount;
	private Integer participantId;
	private Timestamp date;
	private List<Integer> paymentSettlementDetailId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSettlementId() {
		return SettlementId;
	}

	public void setSettlementId(Integer settlementId) {
		SettlementId = settlementId;
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

	public List<Integer> getPaymentSettlementDetailId() {
		return paymentSettlementDetailId;
	}

	public void setPaymentSettlementDetailId(List<Integer> paymentSettlementDetailId) {
		this.paymentSettlementDetailId = paymentSettlementDetailId;
	}

	@Override
	public String toString() {
		return "PaymentSettlement [id=" + id + ", SettlementId=" + SettlementId + ", categoryName=" + categoryName
				+ ", amount=" + amount + ", participantId=" + participantId + ", date=" + date
				+ ", paymentSettlementDetailId=" + paymentSettlementDetailId + "]";
	}

}
