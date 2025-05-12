package model;
import java.io.Serializable;


public class User implements Serializable {
	
	private String name; //ユーザー名
	private String pass; //パスワード
	
	public User() {}
	public User(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}
	public String getName() {return name;}
	public String getPass() {return pass;}
}


//ユーザーに関する情報を持つJavaBeans(スコープに保存可能なインスタンス)