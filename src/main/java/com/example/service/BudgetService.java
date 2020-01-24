package com.example.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Budget;
import com.example.domain.Category;
import com.example.domain.IncomeBudget;
import com.example.domain.PaymentBudget;
import com.example.form.BudgetForm;
import com.example.repository.BudgetRepository;
import com.example.repository.IncomeBudgetRepository;
import com.example.repository.PaymentBudgetRepository;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private HttpSession session;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private IncomeBudgetRepository incomeBudgetRepository;

	@Autowired
	private PaymentBudgetRepository paymentBudgetRepository;

	public void registerBudget(BudgetForm budgetForm) {
		String month = budgetForm.getDate() + "-01";
		LocalDate localDate = LocalDate.parse(month, DateTimeFormatter.ISO_DATE);
		LocalDateTime localDateTime = localDate.atStartOfDay();
		Timestamp date = Timestamp.valueOf(localDateTime);
		Integer householdId = Integer.parseInt(budgetForm.getHouseholdId());
		List<String> eachIncomeCategoryNameList = budgetForm.getEachIncomeCategoryNameList();
		List<String> eachPaymentCategoryNameList = budgetForm.getEachPaymentCategoryNameList();
		List<IncomeBudget> incomeBudgetList = new ArrayList<>();
		List<PaymentBudget> paymentBudgetList = new ArrayList<>();
		Budget budget = new Budget();
		budget.setDate(date);
		budget.setHouseholdId(householdId);
		Budget checkBudget = budgetRepository.findByhouseholdDate(date);
		Integer budgetId;
		System.out.println(checkBudget+"checkbudgetttt");
		if(checkBudget != null) {
			budgetId = checkBudget.getId();
			System.out.println("60行目");
		}else {
			budgetId = budgetRepository.insert(budget);
			System.out.println("63行目");
		}
		for (String eachIncomeCategoryName : eachIncomeCategoryNameList) {
			if (eachIncomeCategoryName.equals("")) {
				break;
			}
			IncomeBudget incomeBudget = new IncomeBudget();
			incomeBudget.setDate(date);
			incomeBudget.setBudgetId(budgetId);
			incomeBudget.setParticipantId((Integer) session.getAttribute("userId"));
			incomeBudget.setCategoryName(eachIncomeCategoryName);
			incomeBudgetList.add(incomeBudget);
			Category category = new Category();
			category.setCategoryName(eachIncomeCategoryName);
			category.setHouseholdId(householdId);
			categoryService.registerCategory(category);
		}
		budget.setIncomeBudgetList(incomeBudgetList);
		;
		for (String eachPaymentCategoryName : eachPaymentCategoryNameList) {
			if (eachPaymentCategoryName.equals("") || eachPaymentCategoryName == null) {
				break;
			}
			PaymentBudget paymentBudget = new PaymentBudget();
			paymentBudget.setDate(date);
			paymentBudget.setBudgetId(budgetId);
			paymentBudget.setParticipantId((Integer) session.getAttribute("userId"));
			paymentBudget.setCategoryName(eachPaymentCategoryName);
			paymentBudgetList.add(paymentBudget);

			Category category = new Category();
			category.setCategoryName(eachPaymentCategoryName);
			category.setHouseholdId(householdId);
			categoryService.registerCategory(category);
		}
		budget.setPaymentBudgetList(paymentBudgetList);
		List<Integer> eachIncomeAmountList = budgetForm.getEachIncomeAmountList();
		List<Integer> eachPaymentAmountList = budgetForm.getEachPaymentAmountList();
		Integer count = 0;
		for (Integer eachIncomeAmount : eachIncomeAmountList) {
			if (eachIncomeAmount == null) {
				break;
			}
			IncomeBudget incomeBudget = budget.getIncomeBudgetList().get(count);
			incomeBudget.setAmount(eachIncomeAmount);
			count += 1;
		};
		count = 0;
		for (Integer eachPaymentAmount : eachPaymentAmountList) {
			if (eachPaymentAmount == null) {
				break;
			}
			PaymentBudget paymentBudget = budget.getPaymentBudgetList().get(count);
			paymentBudget.setAmount(eachPaymentAmount);
			count += 1;
		};
		for (IncomeBudget incomeBudget : budget.getIncomeBudgetList()) {
			incomeBudgetRepository.insert(incomeBudget);
		}
		for (PaymentBudget paymentBudget : budget.getPaymentBudgetList()) {
			paymentBudgetRepository.insert(paymentBudget);
		}
	}

}
