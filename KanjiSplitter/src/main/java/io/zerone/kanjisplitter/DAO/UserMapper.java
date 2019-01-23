package io.zerone.kanjisplitter.DAO;

import io.zerone.kanjisplitter.VO.User;

public interface UserMapper {
	public int insertUser(User user);
	public User selectUser(String user_id);
}
