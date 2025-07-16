package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB05;
import db.DbException05;

public class Program05 {

	public static void main(String[] args) {

		Connection conn = null;
		Statement st = null;
		try {
			conn = DB05.getConnection();

			conn.setAutoCommit(false);

			st = conn.createStatement();

			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

			// int x = 1;
			// if (x < 2) {
			// throw new SQLException("Fake error");
			// }

			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

			conn.commit();

			System.out.println("rows1 = " + rows1);
			System.out.println("rows2 = " + rows2);
		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new DbException05("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException05("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB05.closeStatement(st);
			DB05.closeConnection();
		}
	}
}