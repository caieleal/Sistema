package br.com.sistemtelas;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;

import br.com.conexaosql.ConexaoSQL;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usuario_txt;
	private JPasswordField senha_txt;

	/**
	 * Launch the application.
	 */

	Connection conexaoDB = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {

		conexaoDB = ConexaoSQL.connect();

		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 405, 231);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLogin = new JLabel("Usu\u00E1rio:");
		lblLogin.setBounds(10, 25, 99, 14);
		contentPane.add(lblLogin);

		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(10, 71, 74, 14);
		contentPane.add(lblSenha);

		usuario_txt = new JTextField();
		usuario_txt.setBounds(123, 22, 189, 20);
		contentPane.add(usuario_txt);
		usuario_txt.setColumns(10);

		JButton btnLogin = new JButton("Login");

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				acessar();
			}
		});
		btnLogin.setBounds(151, 126, 89, 23);
		contentPane.add(btnLogin);

		senha_txt = new JPasswordField();
		senha_txt.setBounds(123, 68, 189, 20);
		contentPane.add(senha_txt);
		setResizable(false);
		setLocationRelativeTo(null);

		if (conexaoDB != null) {

			JLabel statusDoSistema = new JLabel("");
			statusDoSistema.setIcon(new ImageIcon(Login.class.getResource("/br/com/icones/Ligado.png")));
			statusDoSistema.setBounds(10, 126, 74, 64);
			contentPane.add(statusDoSistema);

		} else {
			
			JLabel statusDoSistema = new JLabel("");
			statusDoSistema.setIcon(new ImageIcon(Login.class.getResource("/br/com/icones/Desligado.png")));
			statusDoSistema.setBounds(10, 126, 74, 64);
			contentPane.add(statusDoSistema);
			btnLogin.setEnabled(false);

		}
	}

	public void acessar() {

		String sql = "SELECT * FROM usuarios where login = ? and senha = ?";

		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, usuario_txt.getText());
			pst.setString(2, senha_txt.getText());
			rs = pst.executeQuery();

			if (rs.next()) {

				String perfil = rs.getString(6);
				
				if (perfil.equalsIgnoreCase("administrador")) {

					Menu menu = new Menu();
					menu.setVisible(true);
					menu.getMnRelatrios().setEnabled(true);
					menu.getMnRelatrios().setVisible(true);
					menu.getUsuarioLogado().setText(rs.getString(2));
					dispose();
				} else {
					Menu menu = new Menu();
					menu.setVisible(true);
					menu.getMnRelatrios().setEnabled(false);
					menu.getUsuarioLogado().setText(rs.getString(2));
					dispose();
			
				}
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha incorreto !", "Atenção !",
						JOptionPane.INFORMATION_MESSAGE);
				
						}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

}
