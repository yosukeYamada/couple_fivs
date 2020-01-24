package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Category;
import com.example.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * カテゴリーを新規登録するメソッド.
	 * 
	 * @param category
	 */
	public void registerCategory(Category category) {
		String categoryName = category.getCategoryName();
		Integer householdId = category.getHouseholdId();
		boolean checkCategory = categoryRepository.findByCategoryNameAndHouseholdId(categoryName, householdId);
		if(checkCategory == true) {
			categoryRepository.insert(category);			
		}
	}
	
}
