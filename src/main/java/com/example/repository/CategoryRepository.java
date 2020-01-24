package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Category;

@Repository
public class CategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs,i) ->{
		
		Category category = new Category();
		category.setId(rs.getInt("id"));
		category.setHouseholdId(rs.getInt("households_id"));
		category.setCategoryName(rs.getString("category_name"));
		return category;
	};
	
	private static final RowMapper<Category> CATEGORY_ID_ROW_MAPPER = (rs,i) ->{
		Category category = new Category();
		category.setId(rs.getInt("id"));
		return category;
	};
	
	
	public Category insert(Category category) {
		String sql ="INSERT INTO categories (category_name,households_id) VALUES(:categoryName,:householdId) RETURNING id,households_id,category_name";
		SqlParameterSource param = new BeanPropertySqlParameterSource(category);
		return template.queryForObject(sql, param,CATEGORY_ROW_MAPPER);
	}
	
	public boolean findByCategoryNameAndHouseholdId(String categoryName,Integer householdId) {
		String sql = "SELECT id,category_name,households_id FROM categories WHERE category_name = :categoryName AND households_id = :householdId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("categoryName",categoryName).addValue("householdId", householdId);
		List<Category> categoryList = template.query(sql, param,CATEGORY_ROW_MAPPER);
		if(categoryList.size() == 0) {
			return true;
		}else{
			return false;
		}
	}
	
}
