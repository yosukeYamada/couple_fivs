package com.example.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Household;
import com.example.form.BudgetForm;
import com.example.form.HouseholdForm;
import com.example.service.BudgetService;
import com.example.service.HouseholdService;

@Controller
@RequestMapping("/registerHouseholds")
public class RegisterHouseholdsController {

	@Autowired
	private HouseholdService householdService;

	@Autowired
	private BudgetService budgetService;

	@Autowired
	private HttpSession session;

	@RequestMapping("")
	public String index() {
		return "main_menu";
	}

	@RequestMapping("/showRegisterHouseholdForm")
	public String showRegisterHouseholdForm() {
		return "register_household";
	}

	@RequestMapping("/registerNewHousehold")
	public String registerNewHOusehold(HouseholdForm householdForm) {
		Household household = new Household();
		BeanUtils.copyProperties(householdForm, household);
		householdService.registerNewHousehold(household);
		return "main_menu";

	}

	@RequestMapping("/showHouseholdList")
	public String showHouseholdList(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		List<Household> householdList = householdService.searchHouseholds(userId);
		model.addAttribute("householdList", householdList);
		model.addAttribute("userId", userId);
		return "household_list";
	}
	
	@RequestMapping("/showHouseholdMenu")
	public String showHouseholdMenu(Integer householdId,Model model){
		if(householdId==null) {
			householdId = (Integer)session.getAttribute("householdId");
		}
		Household household = householdService.searchHousehold(householdId);
		session.setAttribute("householdId", household.getId());
		model.addAttribute("household", household);
		return "household_detail";
	}
		

	@RequestMapping("/showHousehold")
	public String showHousehold(Integer householdId, Model model) {
		Household household = householdService.searchHouseholdHistory(householdId);
		Map<String,Integer> totalAmountAboutBudget = householdService.getTotalAmountAboutBudget(household);
//		session.setAttribute("householdId", household.getId());
		model.addAttribute("totalAmountAboutBudget",totalAmountAboutBudget);
		model.addAttribute("household", household);
		System.out.println("householdの値を見てみよう"+household);
		return "household_history";
	}

	@RequestMapping("/showRegisterBudgetForm")
	public String showRegisterBudgetForm() {
		return "register_budget";
	}

	@RequestMapping("/registerBudget")
	public String registerBudget(BudgetForm budgetForm) {
		budgetService.registerBudget(budgetForm);
		return "main_menu";
	}
	
//	@RequestMapping("/showBudgetList")
//	public String showBudgetList(Integer householdId) {
//		
//	}

	@RequestMapping("/showRegisterSettlementForm")
	public String showRegisterSettlementForm() {
		return "register_settlement";
	}

}
