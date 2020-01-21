package br.com.sistemtelas;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

import br.com.conexaosql.ConexaoSQL;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Usuarios extends JInternalFrame implements TesteInterface {
	private JTextField idUsuario;
	private JTextField nomeUsuario;
	private JTextField telefoneUsuario;
	private JTextField emailUsuario;
	private JTextField loginUsuario;
	private JPasswordField senhaUsuario;
	private JComboBox perfilUsuario;
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
					Usuarios frame = new Usuarios();
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
	public Usuarios() {
		conexaoDB = ConexaoSQL.connect();

		setTitle("Usu\u00E1rios");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(265, 21, 687, 530);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("ID*:");
		lblId.setBounds(10, 29, 48, 14);
		getContentPane().add(lblId);

		JLabel lblNome = new JLabel("Nome*:");
		lblNome.setBounds(10, 80, 48, 14);
		getContentPane().add(lblNome);

		JLabel lblTelefone = new JLabel("Telefone*:");
		lblTelefone.setBounds(10, 185, 86, 14);
		getContentPane().add(lblTelefone);

		JLabel lblEmail = new JLabel("Email*:");
		lblEmail.setBounds(10, 131, 48, 14);
		getContentPane().add(lblEmail);

		JLabel lblLogin = new JLabel("Login*:");
		lblLogin.setBounds(10, 234, 48, 14);
		getContentPane().add(lblLogin);

		JLabel lblSenha = new JLabel("Senha*:");
		lblSenha.setBounds(10, 290, 48, 14);
		getContentPane().add(lblSenha);

		perfilUsuario = new JComboBox();
		perfilUsuario.setModel(new DefaultComboBoxModel(new String[] { "Administrador", "Colaborador" }));
		perfilUsuario.setBounds(472, 27, 109, 18);
		getContentPane().add(perfilUsuario);

		JLabel lblPerfil = new JLabel("Perfil*:");
		lblPerfil.setBounds(397, 29, 48, 14);
		getContentPane().add(lblPerfil);

		idUsuario = new JTextField();
		idUsuario.setBounds(109, 26, 65, 20);
		getContentPane().add(idUsuario);
		idUsuario.setColumns(10);

		nomeUsuario = new JTextField();
		nomeUsuario.setBounds(109, 77, 336, 20);
		getContentPane().add(nomeUsuario);
		nomeUsuario.setColumns(10);

		telefoneUsuario = new JTextField();
		telefoneUsuario.setBounds(106, 182, 109, 20);
		getContentPane().add(telefoneUsuario);
		telefoneUsuario.setColumns(10);

		emailUsuario = new JTextField();
		emailUsuario.setBounds(109, 128, 336, 20);
		getContentPane().add(emailUsuario);
		emailUsuario.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//adicionarUsuario();
				createUsu();
			}
		});
		btnAdicionar.setBounds(10, 458, 89, 23);
		getContentPane().add(btnAdicionar);

		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerUsuario();
			}
		});
		btnDeletar.setBounds(245, 458, 89, 23);
		getContentPane().add(btnDeletar);

		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarUsuario();
			}
		});
		btnPesquisar.setBounds(211, 25, 115, 23);
		getContentPane().add(btnPesquisar);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarUsuario();
			}
		});
		btnAtualizar.setBounds(492, 458, 89, 23);
		getContentPane().add(btnAtualizar);

		loginUsuario = new JTextField();
		loginUsuario.setBounds(109, 231, 106, 20);
		getContentPane().add(loginUsuario);
		loginUsuario.setColumns(10);

		senhaUsuario = new JPasswordField();
		senhaUsuario.setBounds(109, 288, 106, 18);
		getContentPane().add(senhaUsuario);

	}

	public void clear() {
		nomeUsuario.setText(null);
		telefoneUsuario.setText(null);
		loginUsuario.setText(null);
		senhaUsuario.setText(null);
		emailUsuario.setText(null);
	}
	

	public void adicionarUsuario() {
		String sql = "INSERT INTO usuarios(id, usuario, telefone, login, senha, perfil, email) values (?,?,?,?,?,?,?)";

		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, idUsuario.getText());
			pst.setString(2, nomeUsuario.getText());
			pst.setString(3, telefoneUsuario.getText());
			pst.setString(4, loginUsuario.getText());
			pst.setString(5, senhaUsuario.getText());
			pst.setString(6, perfilUsuario.getSelectedItem().toString());
			pst.setString(7, emailUsuario.getText());

			if (idUsuario.getText().isEmpty() || nomeUsuario.getText().isEmpty() || telefoneUsuario.getText().isEmpty()
					|| loginUsuario.getText().isEmpty() || senhaUsuario.getText().isEmpty()
					|| emailUsuario.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios !", "Atenção !",
						JOptionPane.INFORMATION_MESSAGE);
			} else {

				int addUsuario = pst.executeUpdate();

				if (addUsuario > 0) {

					JOptionPane.showMessageDialog(null, "Usuário criado com sucesso !", "Atenção !",
							JOptionPane.INFORMATION_MESSAGE);
					clear();
				}

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuário ou Login cadastrados!", "Atenção !",
					JOptionPane.INFORMATION_MESSAGE);
			clear();

		}

	}

	public void consultarUsuario() {

		String sql = "SELECT * FROM usuarios where id=?";
		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, idUsuario.getText());
			rs = pst.executeQuery();
			if (rs.next()) {
				nomeUsuario.setText(rs.getString(2));
				telefoneUsuario.setText(rs.getString(3));
				loginUsuario.setText(rs.getString(4));
				senhaUsuario.setText(rs.getString(5));
				perfilUsuario.setSelectedItem(rs.getString(6));
				emailUsuario.setText(rs.getString(7));
			} else {
				JOptionPane.showMessageDialog(null, "Usuário não cadastrado !", "Atenção !",
						JOptionPane.INFORMATION_MESSAGE);
				clear();
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void removerUsuario() {
		int remove = JOptionPane.showConfirmDialog(null, "Deseja mesmo remover este usuário ?", "Atenção !",
				JOptionPane.YES_NO_OPTION);
		if (remove == JOptionPane.YES_OPTION) {
			String sql = "DELETE FROM usuarios where id=?";
			try {
				pst = conexaoDB.prepareStatement(sql);
				pst.setString(1, idUsuario.getText());
				int removido = pst.executeUpdate();
				if (removido > 0) {
					JOptionPane.showMessageDialog(null, "Usuário removido com sucesso !", "Atenção !",
							JOptionPane.INFORMATION_MESSAGE);
					clear();
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	public void atualizarUsuario() {
		String sql = "UPDATE usuarios set usuario =?,telefone=?,login=?,senha=?,perfil=?,email=? where id=? ";

		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, nomeUsuario.getText());
			pst.setString(2, telefoneUsuario.getText());
			pst.setString(3, loginUsuario.getText());
			pst.setString(4, senhaUsuario.getText());
			pst.setString(5, perfilUsuario.getSelectedItem().toString());
			pst.setString(6, emailUsuario.getText());
			pst.setString(7, idUsuario.getText());

			if (idUsuario.getText().isEmpty() || nomeUsuario.getText().isEmpty() || telefoneUsuario.getText().isEmpty()
					|| loginUsuario.getText().isEmpty() || senhaUsuario.getText().isEmpty()
					|| emailUsuario.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos.", "Atenção !",
						JOptionPane.INFORMATION_MESSAGE);

			} else {
				int atualizar = pst.executeUpdate();
				if (atualizar > 0) {
					JOptionPane.showMessageDialog(null, "Atualizado com sucesso", "Atenção !",
							JOptionPane.INFORMATION_MESSAGE);
					clear();
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	@Override
	public void createUsu() {
		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, idUsuario.getText());
			pst.setString(2, nomeUsuario.getText());
			pst.setString(3, telefoneUsuario.getText());
			pst.setString(4, loginUsuario.getText());
			pst.setString(5, senhaUsuario.getText());
			pst.setString(6, perfilUsuario.getSelectedItem().toString());
			pst.setString(7, emailUsuario.getText());

			if (idUsuario.getText().isEmpty() || nomeUsuario.getText().isEmpty() || telefoneUsuario.getText().isEmpty()
					|| loginUsuario.getText().isEmpty() || senhaUsuario.getText().isEmpty()
					|| emailUsuario.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios !", "Atenção !",
						JOptionPane.INFORMATION_MESSAGE);
			} else {

				int addUsuario = pst.executeUpdate();

				if (addUsuario > 0) {

					JOptionPane.showMessageDialog(null, "Usuário criado com sucesso !", "Atenção !",
							JOptionPane.INFORMATION_MESSAGE);
					clear();
				}

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Usuário ou Login cadastrados!", "Atenção !",
					JOptionPane.INFORMATION_MESSAGE);
			clear();

		}

	}

	public JTextField getIdUsuario() {
		return idUsuario;
	}
}
