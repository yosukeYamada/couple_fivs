package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Budget;
import com.example.domain.Household;
import com.example.domain.IncomeBudget;
import com.example.domain.IncomeSettlement;
import com.example.domain.PaymentBudget;
import com.example.domain.PaymentSettlement;
import com.example.domain.Settlement;

@Repository
public class HouseholdRepository {

	private static final RowMapper<Household> HOUSEHOLD_ROW_MAPPER = (rs, i) -> {
		Household household = new Household();
		household.setId(rs.getInt("id"));
		household.setDate(rs.getTimestamp("date"));
		household.setUserId(rs.getInt("users_id"));
		household.setHouseholdName(rs.getString("household_name"));
		return household;
	};

	private static final ResultSetExtractor<List<Household>> HOUSEHOLD_RESULTSETEXTRACTER = (rs) -> {
		List<Household> householdList = new ArrayList<>();
		List<Budget> budgetList = null;
		List<IncomeBudget> incomeBudgetList = null;
		List<PaymentBudget> paymentBudgetList = null;
		List<Settlement> settlementList = null;
		List<IncomeSettlement> incomeSettlementList = null;
		List<PaymentSettlement> paymentSettlementList = null;
		int checkId = 0;
		while (rs.next()) {
			int nowId = rs.getInt("h_id");
			if (nowId != checkId) {
				Household household = new Household();
				household.setId(rs.getInt("h_id"));
				household.setDate(rs.getTimestamp("h_date"));
				household.setUserId(rs.getInt("h_users_id"));
				household.setHouseholdName(rs.getString("h_household_name"));
				budgetList = new ArrayList<>();
				household.setBudgetList(budgetList);
				settlementList = new ArrayList<>();
				household.setSettlementList(settlementList);
				householdList.add(household);
			}
			System.out.println("60行目"+rs.getInt("b_id"));
			if (rs.getInt("b_id") != 0) {
				Budget budget = new Budget();
				budget.setId(rs.getInt("b_id"));
				budget.setHouseholdId(rs.getInt("b_households_id"));
				budget.setDate(rs.getTimestamp("b_date"));
				incomeBudgetList = new ArrayList<>();
				budget.setIncomeBudgetList(incomeBudgetList);
				paymentBudgetList = new ArrayList<>();
				budget.setPaymentBudgetList(paymentBudgetList);
				budgetList.add(budget);
			}
			if(rs.getInt("ib_id") !=0) {
				IncomeBudget incomeBudget = new IncomeBudget();
				incomeBudget.setId(rs.getInt("ib_id"));
				incomeBudget.setBudgetId(rs.getInt("b_id"));
				incomeBudget.setCategoryName(rs.getString("ib_category_name"));
				incomeBudget.setAmount(rs.getInt("ib_amount"));
				incomeBudget.setParticipantId(rs.getInt("ib_participants_id"));
				incomeBudget.setDate(rs.getTimestamp("ib_date"));
				incomeBudgetList.add(incomeBudget);
			}
			if(rs.getInt("pb_id") != 0) {
				PaymentBudget paymentBudget = new PaymentBudget();
				paymentBudget.setId(rs.getInt("pb_id"));
				paymentBudget.setBudgetId(rs.getInt("b_id"));
				paymentBudget.setCategoryName(rs.getString("pb_category_name"));
				paymentBudget.setAmount(rs.getInt("pb_amount"));
				paymentBudget.setParticipantId(rs.getInt("pb_participants_id"));
				paymentBudget.setDate(rs.getTimestamp("pb_date"));
				paymentBudgetList.add(paymentBudget);
			}
			if (rs.getInt("s_id") != 0) {
				Settlement settlement = new Settlement();
				settlement.setId(rs.getInt("s_id"));
				settlement.setHouseholdId(rs.getInt("s_households_id"));
				incomeSettlementList = new ArrayList<>();
				settlement.setIncomeSettlementList(incomeSettlementList);
				paymentSettlementList = new ArrayList<>();
				settlement.setIncomeSettlementList(incomeSettlementList);
				settlementList.add(settlement);
			}
			if(rs.getInt("is_id") != 0) {
				IncomeSettlement incomeSettlement = new IncomeSettlement();
				incomeSettlement.setId(rs.getInt("is_id"));
				incomeSettlement.setCategoryName(rs.getString("i_s_category_name"));
				incomeSettlement.setAmount(rs.getInt("i_s_amount"));
				incomeSettlement.setParticipantId(rs.getInt("i_s_participant_id"));
				incomeSettlement.setDate(rs.getTimestamp("i_s_date"));
				incomeSettlement.setSettlementId(rs.getInt("s_id"));
				incomeSettlementList.add(incomeSettlement);
			}
			if(rs.getInt("ps_id") !=0) {
				PaymentSettlement paymentSettlement = new PaymentSettlement();
				paymentSettlement.setId(rs.getInt("ps_id"));
				paymentSettlement.setCategoryName(rs.getString("ps_category_name"));
				paymentSettlement.setAmount(rs.getInt("ps_amount"));
				paymentSettlement.setParticipantId(rs.getInt("ps_participant_id"));
				paymentSettlement.setDate(rs.getTimestamp("ps_date"));
				paymentSettlement.setSettlementId(rs.getInt("s_id"));
				paymentSettlementList.add(paymentSettlement);
			}
			checkId = nowId;
		}
		return householdList;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	public void insert(Household household) {
		String sql = "INSERT INTO households (users_id,date,household_name) VALUES(:userId,:date,:householdName)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(household);
		template.update(sql, param);
	}

	/**
	 * @param userId
	 * @return
	 */
	public List<Household> findByUserId(Integer userId) {
		String sql = "SELECT id,date,users_id,household_name FROM households WHERE users_id = :userId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<Household> householdList = template.query(sql, param, HOUSEHOLD_ROW_MAPPER);
		return householdList;
	}
	
	public Household load(Integer householdId) {
		String sql="SELECT id,date,users_id,household_name FROM households WHERE id = :householdId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("householdId", householdId);
		List<Household> householdList = template.query(sql, param,HOUSEHOLD_ROW_MAPPER);
		if(householdList.size() != 0) {
			return householdList.get(0);
		}else {
			return null;
		}
	}
	
	

	public Household findByHouseholdId(Integer householdId) {
		System.out.println(householdId+"srtyhtmrynet");
//		String sql = "SELECT id,date,users_id,household_name FROM households WHERE id = :householdId";
//		String sql = "SELECT h.id AS households_id," + " h.date AS households_date,"
//				+ "h.users_id AS households_users_id," + "h.household_name AS households_name," + "b.id AS budgets_id,"
//				+ "b.households_id AS budgets_households_id," + "b.category_name AS budgets_category_name,"
//				+ "b.amount AS budgets_amount," + "b.participants_id AS budgets_participants_id,"
//				+ "b.status AS budgets_status," + "b.date AS budgets_date," + "s.id AS settlements_id,"
//				+ "s.households_id AS settlements_households_id," + "s.category_name AS settlements_category_name,"
//				+ "s.amount AS settlements_amount," + "s.participants_id AS settlements_participants_id,"
//				+ "s.status AS settlements_status," + "s.date AS settlements_date "
//				+ "FROM households AS h LEFT OUTER JOIN budgets AS b " + "ON h.id = b.households_id "
//				+ "LEFT OUTER JOIN settlements AS s " + "ON h.id = s.households_id " + "WHERE h.id = :householdId";
		String sql = "SELECT h.id AS h_id,h.date AS h_date,h.users_id AS h_users_id,h.household_name AS h_household_name,b.id AS b_id,b.households_id AS b_households_id,b.date AS b_date,ib.id AS ib_id,ib.budgets_id AS ib_budgets_id,ib.category_name AS ib_category_name,ib.amount AS ib_amount,ib.participants_id AS ib_participants_id,ib.date AS ib_date,pb.id AS pb_id,pb.budgets_id AS pb_budgets_id,pb.category_name AS pb_category_name,pb.amount AS pb_amount,pb.participants_id AS pb_participants_id,pb.date AS pb_date,s.id AS s_id,s.households_id AS s_households_id,s.date AS s_date,i_s.id AS is_id,i_s.settlements_id AS is_budgets_id,i_s.category_name AS is_category_name,i_s.amount AS is_amount,i_s.participants_id AS is_participants_id,i_s.date AS is_date, ps.id AS ps_id,ps.settlements_id AS ps_settlements_id, ps.category_name AS ps_category_name,ps.amount AS ps_amount,ps.participants_id AS ps_participants_id,ps.date AS ps_date FROM households AS h LEFT OUTER JOIN budgets AS b ON h.id = b.households_id LEFT OUTER JOIN income_budgets AS ib ON b.id = ib.budgets_id LEFT OUTER JOIN payment_budgets AS pb ON b.id = pb.budgets_id LEFT OUTER JOIN settlements AS s ON h.id = s.households_id LEFT OUTER JOIN income_settlements AS i_s ON s.id = i_s.settlements_id LEFT OUTER JOIN payment_settlements AS ps ON s.id = ps.settlements_id WHERE h.id = :householdId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("householdId", householdId);
		List<Household> householdList = template.query(sql, param, HOUSEHOLD_RESULTSETEXTRACTER);
		Household household = householdList.get(0);
		return household;
	}

}
