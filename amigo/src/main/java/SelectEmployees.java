import java.util.List;

import dao.EmployeesDAO;
import model.Employee;

public class SelectEmployees {
	public static void main(String[] args) {
		// EMPLOYEEテーブルの全レコードを取得
		EmployeesDAO empDAO = new EmployeesDAO();
		List<Employee> empList = empDAO.findAll();
		
		//取得したレコードの内容を出力
		for (Employee emp : empList) {
			System.out.println("ID:" + emp.getId());
			System.out.println("名前:" + emp.getName());
			System.out.println("年齢:" + emp.getAge() + "\n");
		}
	}
}		

//コード13-1:JDBCプログラムのコード(上記のDAOパターンを利用する前)
		//
		//JDBCドライバを読み込む
/*		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		
		//データベースに接続
		try (Connection conn = 
		DriverManager.getConnection("jdbc:mysql://localhost/keg_db", "keg_user", "keg_pass")) {
	
			//select文を準備
			String sql = "SELECT ID,NAME,AGE FROM EMPLOYEES";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			
			//SELECTを実行し、結果表(ResultSet)を取得
			ResultSet rs = pStmt.executeQuery();
			
			//結果表に格納されたレコードの内容を表示
			while (rs.next()) {
				String id = rs.getString("ID");
				String name = rs.getString("NAME");
				int age = rs.getInt("AGE");
				
			//取得したデータを出力
			System.out.println("ID:" + id);
			System.out.println("名前:" + name);
			System.out .println("年齢:" + age + "\n");	
			}
		} catch (SQLException e) {
			e.printStackTrace();
					
		}
	}
}*/
