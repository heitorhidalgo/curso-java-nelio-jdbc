package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB04;
import db.DbIntegrityException;

public class Program04 {

	public static void main(String[] args) {

		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB04.getConnection();

			st = conn.prepareStatement("DELETE FROM department " + "WHERE " + "Id = ?");

			st.setInt(1, 5);

			int rowsAffected = st.executeUpdate();

			System.out.println("Done! Rows affected: " + rowsAffected);
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB04.closeStatement(st);
			DB04.closeConnection();
		}
	}
}