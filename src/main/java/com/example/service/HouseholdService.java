package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Budget;
import com.example.domain.Household;
import com.example.domain.IncomeBudget;
import com.example.domain.PaymentBudget;
import com.example.repository.CategoryRepository;
import com.example.repository.HouseholdRepository;

@Service
public class HouseholdService {

	@Autowired
	private HttpSession session;

	@Autowired
	private HouseholdRepository householdRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * 新規で家計簿を作るメソッド.
	 * 
	 * @param household 家計簿
	 */
	public void registerNewHousehold(Household household) {
		LocalDate localDate = LocalDate.now();
		LocalDateTime localDateTime = localDate.atStartOfDay();
		Timestamp date = Timestamp.valueOf(localDateTime);
		household.setDate(date);
		Integer userId = (Integer) session.getAttribute("userId");
		System.out.println(userId + "aaa");
		household.setUserId(userId);
		householdRepository.insert(household);
	}

	/**
	 * ログインしたユーザーが管理する家計簿を検索するメソッド.
	 * 
	 * @param userId
	 * @return
	 */
	public List<Household> searchHouseholds(Integer userId) {
		List<Household> householdList = householdRepository.findByUserId(userId);
		return householdList;
	}

	/**
	 * 選択した家計簿を表示するメソッド
	 * 
	 * @param householdId 家計簿ID
	 * @return 家計簿詳細画面へ遷移
	 */
	public Household searchHousehold(Integer householdId) {
		Household household = householdRepository.load(householdId);
		return household;
	}
	
	public Household searchHouseholdHistory(Integer householdId) {
		Household household = householdRepository.findByHouseholdId(householdId);
		return household;
	}

	public Map<String, Integer> getTotalAmountAboutBudget(Household household) {
		Map<String, Integer> totalAmountMap = new HashMap<>();
		Integer totalIncomeAmountAboutBudget = 0;
		Integer totalPaymentAmountAboutBudget = 0;
		if (household.getBudgetList() != null) {
			for (Budget budget : household.getBudgetList()) {
				if (budget.getIncomeBudgetList() != null) {
					for (IncomeBudget incomeBudget : budget.getIncomeBudgetList()) {
						totalIncomeAmountAboutBudget += incomeBudget.getAmount();
					}
				}
				if (budget.getPaymentBudgetList() != null) {
					for (PaymentBudget paymentBudget : budget.getPaymentBudgetList()) {
						totalPaymentAmountAboutBudget += paymentBudget.getAmount();
					}
				}
			}
		}
		totalAmountMap.put("totalIncomeAmountAboutBudget", totalIncomeAmountAboutBudget);
		totalAmountMap.put("totalPaymentAmountAboutBudget", totalPaymentAmountAboutBudget);
		return totalAmountMap;
	}

}
