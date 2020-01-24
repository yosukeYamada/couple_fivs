package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.User;
import com.example.repository.UserRepository;

/**
 * ログインサービス.
 * ログインする以前に行う業務処理を行うクラス.
 * 
 * @author yosuke.yamada
 *
 */
@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * ユーザIDの重複確認を確認するメソッド.
	 * 
	 * @param userId
	 * @return
	 */
	public boolean findByUserId(String userId) {
		User user = userRepository.findByUserId(userId);
		if(user == null) {
			return true;
		}else {
			return false;
		}
	}
	
	
	/**
	 * ユーザ情報を登録するメソッド.
	 * 
	 * @param user 登録するユーザ情報
	 */
	public void insert(User user) {
		userRepository.insert(user);
	}

	/**
	 * ユーザ登録有無を確認するメソッド.
	 * 
	 * @param userId ユーザID
	 * @param password パスワード
	 * @return 判断結果
	 */
	public Integer findByUserIdAndPassword(String userId, String password) {
		User user = userRepository.findByUserIdAndPassword(userId, password);
		if(user != null) {
			return user.getId();
		}else {
			return null;
		}
	}

}
