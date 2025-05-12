package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Mutter; // モデルは既存の Mutter を再利用

public class DiariesDAO {
	private final String JDBC_URL = "jdbc:mysql://localhost/keg_db";
	private final String DB_USER = "keg_user";
	private final String DB_PASS = "keg_pass";

	public List<Mutter> findAll() {
		List<Mutter> diaryList = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, NAME, TITLE, TEXT, CREATED_AT, FLAG, IMAGE, ICON_IMAGE FROM DIARIES WHERE FLAG = 1 ORDER BY ID DESC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("ID");
				String userName = rs.getString("NAME");
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				Timestamp createdAt = rs.getTimestamp("CREATED_AT");
				byte[] image = rs.getBytes("IMAGE");
				byte[] iconImage = rs.getBytes("ICON_IMAGE");
				Mutter diary = new Mutter(id, userName, title, text, createdAt, image, iconImage);
				diaryList.add(diary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return diaryList;
	}

	public void insert(Mutter diary) {
		String sql = "INSERT INTO DIARIES (NAME, TITLE, TEXT, FLAG, IMAGE, ICON_IMAGE) VALUES (?, ?, ?, 1, ?, ?)";
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, diary.getUserName());
			stmt.setString(2, diary.getTitle());
			stmt.setString(3, diary.getText());
			stmt.setBytes(4, diary.getImage());
			stmt.setBytes(5, diary.getIconImage());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteDiaryById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "UPDATE DIARIES SET FLAG = 0 WHERE ID = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateDiary(Mutter diary) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "UPDATE DIARIES SET TITLE = ?, TEXT = ?, IMAGE = ? WHERE ID = ? AND FLAG = 1";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, diary.getTitle());
			stmt.setString(2, diary.getText());
			stmt.setBytes(3, diary.getImage());
			stmt.setInt(4, diary.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Mutter findById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Mutter diary = null;
		try {
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "SELECT ID, NAME, TITLE, TEXT, CREATED_AT, IMAGE, ICON_IMAGE FROM DIARIES WHERE ID = ? AND FLAG = 1";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if (rs.next()) {
				String userName = rs.getString("NAME");
				String title = rs.getString("TITLE");
				String text = rs.getString("TEXT");
				Timestamp createdAt = rs.getTimestamp("CREATED_AT");
				byte[] image = rs.getBytes("IMAGE");
				byte[] iconImage = rs.getBytes("ICON_IMAGE");
				diary = new Mutter(id, userName, title, text, createdAt, image, iconImage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return diary;
	}

	public byte[] getImageById(int id) {
		String sql = "SELECT IMAGE FROM DIARIES WHERE ID = ? AND FLAG = 1";
		byte[] imageData = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					imageData = rs.getBytes("IMAGE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return imageData;
	}

	public byte[] getIconImageById(int id) {
		String sql = "SELECT ICON_IMAGE FROM DIARIES WHERE ID = ? AND FLAG = 1";
		byte[] iconImageData = null;
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					iconImageData = rs.getBytes("ICON_IMAGE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return iconImageData;
	}
}