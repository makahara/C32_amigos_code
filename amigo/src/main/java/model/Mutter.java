package model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Mutter implements Serializable {
	private int id; //ID
	private String userName; //ユーザー名
	private String text; //日記本文
	private Timestamp createdAt;//投稿日時
	private byte[] image; //メイン画像
	private String title; //日記タイトル
	private byte[] iconImage; //アイコン画像

	public Mutter() {
	}

	public Mutter(int id, String userName, String title, String text, Timestamp createdAt, byte[] image,
			byte[] iconImage) {
		this.id = id;
		this.userName = userName;
		this.title = title;
		this.text = text;
		this.createdAt = createdAt;
		this.image = image;
		this.iconImage = iconImage;
	}

	public Mutter(String userName, String title, String text, byte[] image, byte[] iconImage) {
		this.userName = userName;
		this.title = title;
		this.text = text;
		this.image = image;
		this.iconImage = iconImage;
	}

	// ゲッターとセッターは省略 (必要に応じて追加)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public byte[] getIconImage() {
		return iconImage;
	}

	public void setIconImage(byte[] iconImage) {
		this.iconImage = iconImage;
	}
}