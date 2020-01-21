package br.com.conexaosql;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class ConexaoSQL {

	public static Connection connect() {

		Connection conexaoDB = null;

		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sistema";
		String user = "root";
		String password = "";

		try {
			Class.forName(driver);
			conexaoDB = DriverManager.getConnection(url, user, password);
			return conexaoDB;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sem conexão com o banco !", "Atenção !",
					JOptionPane.INFORMATION_MESSAGE);

			return null;
		}

	}
}
