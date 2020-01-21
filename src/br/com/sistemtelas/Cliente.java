package br.com.sistemtelas;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JInternalFrame;

import br.com.conexaosql.ConexaoSQL;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cliente extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	Connection conexaoDB = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JTextField pesquiCliente;
	private JLabel lblId;
	private JTextField idCliente;
	private JLabel lblNome;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNascimento;
	private JTextField nomeCliente;
	private JTextField emailCliente;
	private JTextField endCliente;
	private JTextField telefoneCliente;
	private JTextField cpfCliente;
	private JTextField nasciCliente;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton btnAdicionar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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

	public Cliente() {
		conexaoDB = ConexaoSQL.connect();
		setBounds(265, 21, 687, 530);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Clientes");
		getContentPane().setLayout(null);

		JLabel lblPesquisar = new JLabel("Pesquisar");
		lblPesquisar.setBounds(10, 11, 101, 14);
		getContentPane().add(lblPesquisar);

		pesquiCliente = new JTextField();
		pesquiCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClientes();
			}
		});
		pesquiCliente.setBounds(10, 36, 358, 20);
		getContentPane().add(pesquiCliente);
		pesquiCliente.setColumns(10);

		lblId = new JLabel("ID:");
		lblId.setVisible(false);
		lblId.setBounds(418, 11, 48, 14);
		getContentPane().add(lblId);

		idCliente = new JTextField();
		idCliente.setVisible(false);
		idCliente.setBounds(481, 8, 48, 20);
		getContentPane().add(idCliente);
		idCliente.setColumns(10);

		lblNome = new JLabel("Nome*:");
		lblNome.setBounds(10, 280, 48, 14);
		getContentPane().add(lblNome);

		lblNewLabel = new JLabel("Email*:");
		lblNewLabel.setBounds(10, 311, 48, 14);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Endere\u00E7o*:");
		lblNewLabel_1.setBounds(10, 349, 88, 14);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Telefone*:");
		lblNewLabel_2.setBounds(10, 386, 88, 14);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("CPF:");
		lblNewLabel_3.setBounds(10, 424, 48, 14);
		getContentPane().add(lblNewLabel_3);

		lblNascimento = new JLabel("Nascimento:");
		lblNascimento.setBounds(10, 461, 101, 14);
		getContentPane().add(lblNascimento);

		nomeCliente = new JTextField();
		nomeCliente.setBounds(123, 277, 305, 20);
		getContentPane().add(nomeCliente);
		nomeCliente.setColumns(10);

		emailCliente = new JTextField();
		emailCliente.setBounds(123, 308, 305, 20);
		getContentPane().add(emailCliente);
		emailCliente.setColumns(10);

		endCliente = new JTextField();
		endCliente.setBounds(123, 346, 305, 20);
		getContentPane().add(endCliente);
		endCliente.setColumns(10);

		telefoneCliente = new JTextField();
		telefoneCliente.setBounds(123, 383, 122, 20);
		getContentPane().add(telefoneCliente);
		telefoneCliente.setColumns(10);

		cpfCliente = new JTextField();
		cpfCliente.setBounds(123, 421, 122, 20);
		getContentPane().add(cpfCliente);
		cpfCliente.setColumns(10);

		nasciCliente = new JTextField();
		nasciCliente.setBounds(123, 458, 122, 20);
		getContentPane().add(nasciCliente);
		nasciCliente.setColumns(10);

		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setBounds(551, 275, 89, 23);
		getContentPane().add(btnAdicionar);

		JButton btnApagar = new JButton("Remover");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerCliente();
				
			}
		});
		btnApagar.setBounds(551, 345, 89, 23);
		getContentPane().add(btnApagar);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCliente();
			}
		});
		btnAtualizar.setBounds(551, 420, 89, 23);
		getContentPane().add(btnAtualizar);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 651, 189);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				autoComplete();
			}
		});
		scrollPane.setViewportView(table);

	}

	public void clear() {
		nomeCliente.setText(null);
		telefoneCliente.setText(null);
		endCliente.setText(null);
		emailCliente.setText(null);
		cpfCliente.setText(null);
		nasciCliente.setText(null);
	}

	public void pesquisarClientes() {
		String sql = "SELECT * FROM clientes where nome like ?";
		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, pesquiCliente.getText() + "%");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void adicionarCliente() {
		String sql = "INSERT INTO clientes(nome, endCliente, telefone, email, cpf, nascimento) values (?,?,?,?,?,?)";

		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, nomeCliente.getText());
			pst.setString(2, endCliente.getText());
			pst.setString(3, telefoneCliente.getText());
			pst.setString(4, emailCliente.getText());
			pst.setString(5, cpfCliente.getText());
			pst.setString(6, nasciCliente.getText());

			if (nomeCliente.getText().isEmpty() || endCliente.getText().isEmpty() || emailCliente.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios", "Atenção !",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				int addCli = pst.executeUpdate();
				if (addCli > 0) {
					JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso !", "Atenção !",
							JOptionPane.INFORMATION_MESSAGE);
					clear();
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Impossivel adicionar cliente", "Atenção !",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	public void atualizarCliente() {
		String sql = "UPDATE clientes set nome=?, endCliente=?, telefone=?, email=?, cpf=?, nascimento=?";
		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, nomeCliente.getText());
			pst.setString(2, endCliente.getText());
			pst.setString(3, telefoneCliente.getText());
			pst.setString(4, emailCliente.getText());
			pst.setString(5, cpfCliente.getText());
			pst.setString(6, nasciCliente.getText());

			if (nomeCliente.getText().isEmpty() || endCliente.getText().isEmpty() || emailCliente.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios", "Atenção !",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				int atualiza = pst.executeUpdate();
				if (atualiza > 0) {
					JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso !", "Atenção !",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void removerCliente() {
		int removerCli = JOptionPane.showConfirmDialog(null, "Deseja remover este cliente ?", "Atenção !",
				JOptionPane.YES_NO_OPTION);
		if (removerCli == JOptionPane.YES_OPTION) {
			String sql = "DELETE FROM clientes where idCliente=?";
			try {
				pst = conexaoDB.prepareStatement(sql);
				pst.setString(1, idCliente.getText());
				if(idCliente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não foi possível remover o cliente", "Atenção !", JOptionPane.INFORMATION_MESSAGE);
				}else {
					int remover = pst.executeUpdate();
					if(remover >0) {
						JOptionPane.showMessageDialog(null, "Cliente removido com sucesso !","Atenção", JOptionPane.INFORMATION_MESSAGE);
						clear();
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}

	}

	public void autoComplete() {
		int complete = table.getSelectedRow();
		idCliente.setText(table.getModel().getValueAt(complete, 0).toString());
		nomeCliente.setText(table.getModel().getValueAt(complete, 1).toString());
		endCliente.setText(table.getModel().getValueAt(complete, 2).toString());
		telefoneCliente.setText(table.getModel().getValueAt(complete, 3).toString());
		emailCliente.setText(table.getModel().getValueAt(complete, 4).toString());
		cpfCliente.setText(table.getModel().getValueAt(complete, 5).toString());
		nasciCliente.setText(table.getModel().getValueAt(complete, 6).toString());
		getBtnAdicionar().setEnabled(false);
	}

	public JButton getBtnAdicionar() {
		return btnAdicionar;
	}
}
