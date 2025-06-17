package com.kopo.c1;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
	private Connection connection;

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void open() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 드라이버 로드
			String url = "jdbc:mysql://localhost:3306/kopodb?useSSL=false&serverTimezone=UTC";
			String user = "kopouser";
			String password = "kopouser";
			this.connection = DriverManager.getConnection(url, user, password);
			System.out.println("MySQL DB 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
			this.connection = null;  // 명확히 초기화
		}
	}

private void close() {
    try {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


	public void createTable() {
		this.open();
		if (this.connection == null) {
        System.out.println("❗ DB 연결 실패로 INSERT 중단");
        return;
    	}
		String query = "CREATE TABLE user (\r\n" + "    idx INT AUTO_INCREMENT PRIMARY KEY,\r\n"
				+ "    user_type VARCHAR(20),\r\n" + "    id VARCHAR(50),\r\n" + "    pwd VARCHAR(255),\r\n"
				+ "    name VARCHAR(100),\r\n" + "    phone VARCHAR(20),\r\n" + "    address VARCHAR(255),\r\n"
				+ "    created DATETIME DEFAULT CURRENT_TIMESTAMP,\r\n"
				+ "    last_updated DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP\r\n" + ");";
		try {
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public static String sha512(String input) {
		try {
			// SHA-512 해시 알고리즘 사용
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] hashedBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

			// 바이트 배열을 16진수 문자열로 변환
			StringBuilder sb = new StringBuilder();
			for (byte b : hashedBytes) {
				sb.append(String.format("%02x", b));
			}

			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("SHA-512 알고리즘을 사용할 수 없습니다.", e);
		}
	}

	public void insertData(User user) {
		this.open();
		if (this.connection == null) {
        System.out.println("❗ DB 연결 실패로 INSERT 중단");
        return;
    	}
		String query = "INSERT INTO user (user_type, id, pwd, name, phone, address, created, last_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, "guest");
			statement.setString(2, user.id);
			user.pwd = sha512(user.pwd);
			statement.setString(3, user.pwd);
			statement.setString(4, user.name);
			statement.setString(5, user.phone);
			statement.setString(6, user.address);
			String now = (new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new java.util.Date());
			statement.setString(7, now);
			statement.setString(8, now);
			statement.execute();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public ArrayList<User> selectAll() {
		this.open();
		ArrayList<User> data = new ArrayList<>();
		this.open();
		if (this.connection == null) {
			System.out.println("❗ DB 연결 실패로 작업 중단");
			return ...; // 적절한 기본값 또는 null 리턴
		}
		try {
			String query = "SELECT * FROM user";
			PreparedStatement statement = this.connection.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				int idx = result.getInt("idx");
				String userType = result.getString("user_type");
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String created = result.getString("created");
				String lastUpdated = result.getString("last_updated");
				data.add(new User(idx, userType, id, pwd, name, phone, address, created, lastUpdated));
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return data;
	}

	public User login(User user) {
		this.open();
		User returnData = new User();
		try {
			String query = "SELECT * FROM user WHERE id=? AND pwd=?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, user.id);
			user.pwd = sha512(user.pwd);
			statement.setString(2, user.pwd);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				int idx = result.getInt("idx");
				String userType = result.getString("user_type");
				String id = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String created = result.getString("created");
				String lastUpdated = result.getString("last_updated");
				returnData = new User(idx, userType, id, pwd, name, phone, address, created, lastUpdated);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return returnData;
	}
	
	public void updateUser(User user) {
		this.open();
		String query = "UPDATE user SET name=?, phone=?, address=?, last_updated=NOW() WHERE id=?";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, user.name);
			statement.setString(2, user.phone);
			statement.setString(3, user.address);
			statement.setString(4, user.id); // 로그인한 유저 ID 기준으로 수정
			statement.executeUpdate();
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
	}

	public User selectUserById(String id) {
		this.open();
		User returnData = new User();
		try {
			String query = "SELECT * FROM user WHERE id=?";
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1, id);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				int idx = result.getInt("idx");
				String userType = result.getString("user_type");
				String userId = result.getString("id");
				String pwd = result.getString("pwd");
				String name = result.getString("name");
				String phone = result.getString("phone");
				String address = result.getString("address");
				String created = result.getString("created");
				String lastUpdated = result.getString("last_updated");
				returnData = new User(idx, userType, userId, pwd, name, phone, address, created, lastUpdated);
			}
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.close();
		return returnData;
	}

}
