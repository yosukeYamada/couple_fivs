package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.PaymentBudget;

@Repository
public class PaymentBudgetRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	public void insert(PaymentBudget paymentBudget) {
		String sql = "INSERT INTO payment_budgets(budgets_id,category_name,amount,participants_id,date) VALUES(:budgetId,:categoryName,:amount,:participantId,:date)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(paymentBudget);
		template.update(sql, param);
		
	}
	
}
