package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.IncomeBudget;

@Repository
public class IncomeBudgetRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	
	public void insert(IncomeBudget incomeBudget) {
		String sql = "INSERT INTO income_budgets(budgets_id,category_name,amount,participants_id,date) VALUES(:budgetId,:categoryName,:amount,:participantId,:date)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(incomeBudget);
		template.update(sql, param);
	}
	
	
}
