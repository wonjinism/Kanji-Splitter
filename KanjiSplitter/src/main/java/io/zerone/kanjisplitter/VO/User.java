package io.zerone.kanjisplitter.VO;

public class User {
	private int user_seq;
	private String user_id;
	private String user_password;
	private String user_regdate;
	private int user_level;

	public User() {
	}

	public User(int user_seq, String user_id, String user_password, String user_regdate, int user_level) {
		this.user_seq = user_seq;
		this.user_id = user_id;
		this.user_password = user_password;
		this.user_regdate = user_regdate;
		this.user_level = user_level;
	}

	public int getUser_seq() {
		return user_seq;
	}

	public void setUser_seq(int user_seq) {
		this.user_seq = user_seq;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_regdate() {
		return user_regdate;
	}

	public void setUser_regdate(String user_regdate) {
		this.user_regdate = user_regdate;
	}

	public int getUser_level() {
		return user_level;
	}

	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

	@Override
	public String toString() {
		return "User [user_seq=" + user_seq + ", user_id=" + user_id + ", user_password=" + user_password
				+ ", user_regdate=" + user_regdate + ", user_level=" + user_level + "]";
	}

}