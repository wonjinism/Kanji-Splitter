package io.zerone.kanjisplitter.DAO;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.zerone.kanjisplitter.VO.User;

@Repository
public class UserDAO {
	@Autowired
	SqlSession session;
	
	public int insertUser(User user) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		int result = 0;
		try {
			result = mapper.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
		return result;
	}
	
	public int loginUser(User user) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		User idExistUser = mapper.selectUser(user.getUser_id()); // user_id만 가지고 유저 가져오기
		if(idExistUser == null) {
			return 0; // 일치하는 user_id가 없음 
		} else if(idExistUser.getUser_password().equals(user.getUser_password())) {
			return 1; // 비밀번호까지 일치
		} else {
			return 2; // user_id는 있지만 비밀번호 일치하지 않음
		}
	}
	
}
