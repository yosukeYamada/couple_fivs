package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.User;

/**
 * ユーザリポジトリ.
 * 
 * @author yosuke.yamada
 *
 */
@Repository
public class UserRepository {

	private static final RowMapper<User> USER_RAW_MAPPER = (rs, i) -> {

		User user = new User();
		user.setId(rs.getInt("id"));
		user.setUserId(rs.getString("user_id"));
		user.setPassword(rs.getString("password"));
		user.setEmail(rs.getString("email"));
		user.setDate(rs.getTimestamp("date"));
		user.setStatus(rs.getInt("status"));;
		return user;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザ情報を登録するメソッド.
	 * 
	 * @param user
	 */
	public void insert(User user) {
		System.out.println(user);
		String sql = "INSERT INTO users (user_id,password,email,date,status) VALUES(:userId,:password,:email,:date,:status)";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}

	/**
	 * ユーザIDとパスワードから、ユーザ情報を検索するメソッド.
	 * 
	 * @param userId ユーザID
	 * @param password パスワード
	 * @return ユーザ情報
	 */
	public User findByUserIdAndPassword(String userId, String password) {
		String sql = "SELECT id,user_id,password,email,date,status FROM users WHERE user_id = :userId AND password = :password";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("password",
				password);
		List<User> userList = template.query(sql, param, USER_RAW_MAPPER);
		if (userList.size() != 0) {
			System.out.println(userList.get(0)+"youserリストの1番目");
			return userList.get(0);
		} else {
			System.out.println(("nullの時"));
			return null;
		}
	}

	/**
	 * ユーザIDからユーザ情報を検索するメソッド.
	 * 
	 * @param userId ユーザID
	 * @return ユーザ情報
	 */
	public User findByUserId(String userId) {
		String sql= "SELECT id,user_id,password,email,date FROM users WHERE user_id = :userId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId);
		List<User> userList = template.query(sql, param,USER_RAW_MAPPER);
		if(userList.size() != 0) {
			return userList.get(0);
		}else {
			return null;
		}
	}

}
