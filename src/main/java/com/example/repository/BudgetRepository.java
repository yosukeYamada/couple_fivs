package com.example.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Budget;

@Repository
public class BudgetRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Budget> BUDGET_RAW_MAPPER = (rs, i) -> {
		Budget budget = new Budget();
		budget.setId(rs.getInt("id"));
		budget.setHouseholdId(rs.getInt("households_id"));
		budget.setDate(rs.getTimestamp("date"));
		return budget;
	};
	private static final RowMapper<Budget> BUDGET_ID_RAW_MAPPER = (rs, i) -> {
		Budget budget = new Budget();
		budget.setId(rs.getInt("id"));
		return budget;
	};

	public Integer insert(Budget budget) {
		String sql = "INSERT INTO budgets(households_id,date) VALUES(:householdId,:date) RETURNING id";
//		String sql = "INSERT INTO budgets(households_id,category_name,amount,participants_id,status,date) VALUES(:householdId,:categoryName,:amount,:participantId,:status,:date)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(budget);
		Budget budget2 = template.queryForObject(sql, param, BUDGET_ID_RAW_MAPPER);
		return budget2.getId();
	}

	public Budget findByhouseholdDate(Timestamp date) {
		String sql = "SELECT id,households_id,date FROM budgets WHERE date = :date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("date", date);
		List<Budget> budgetList = template.query(sql, param, BUDGET_RAW_MAPPER);
		if (budgetList.size() != 0) {
			return budgetList.get(0);
		} else {
			return null;
		}
	}

}
