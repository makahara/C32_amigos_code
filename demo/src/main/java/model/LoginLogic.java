package model;

public class LoginLogic {
	public boolean execute(User user) {
		if (user.getPass().equals("1234")) {return true;}
		return false;
	}
}

//ログインに関する処理を行うモデル