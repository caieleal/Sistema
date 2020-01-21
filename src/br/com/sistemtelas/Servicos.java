package br.com.sistemtelas;

import java.awt.Button;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;


import br.com.conexaosql.ConexaoSQL;
import net.proteanit.sql.DbUtils;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Servicos extends JInternalFrame {
	private JTextField nomeCliente;
	private JTextField idDoCliente;
	private JTextField nOS;
	private JTextField dataOS;
	private JTable table;
	private JTextField avaliacao;
	private JTextField servicos;
	private JTextField pecas;
	private JTextField funcio;
	private JTextField valorTot;
	private String tipo;

	Connection conexaoDB = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JComboBox status;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Servicos frame = new Servicos();
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
	public Servicos() {
		conexaoDB = ConexaoSQL.connect();

		setTitle("Ordem de Servi\u00E7o");
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(265, 21, 687, 530);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 651, 223);
		getContentPane().add(panel);
		panel.setLayout(null);

		nomeCliente = new JTextField();
		nomeCliente.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarClientes();
			}
		});
		nomeCliente.setBounds(10, 22, 152, 20);
		panel.add(nomeCliente);
		nomeCliente.setColumns(10);

		idDoCliente = new JTextField();
		idDoCliente.setEnabled(false);
		idDoCliente.setBounds(235, 22, 96, 20);
		panel.add(idDoCliente);
		idDoCliente.setColumns(10);

		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(177, 25, 48, 14);
		panel.add(lblId);

		JLabel lblNmeroDoProtocolo = new JLabel("N\u00AA OS:");
		lblNmeroDoProtocolo.setBounds(377, 25, 40, 14);
		panel.add(lblNmeroDoProtocolo);

		JLabel lblData = new JLabel("DATA:");
		lblData.setBounds(495, 25, 40, 14);
		panel.add(lblData);

		nOS = new JTextField();
		nOS.setBounds(427, 22, 58, 20);
		panel.add(nOS);
		nOS.setColumns(10);

		dataOS = new JTextField();
		dataOS.setBounds(545, 22, 96, 20);
		panel.add(dataOS);
		dataOS.setColumns(10);

		JScrollPane table_1 = new JScrollPane();

		table_1.setBounds(10, 43, 362, 169);
		panel.add(table_1);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				complete();
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] { { null, null, null }, },
				new String[] { "Identifica\u00E7\u00E3o", "Nome", "Telefone" }));
		table_1.setViewportView(table);

		JLabel lblTeste = new JLabel("Escolha um dos campos desejados:");
		lblTeste.setBounds(377, 50, 264, 14);
		panel.add(lblTeste);

		JRadioButton btnOrcamento = new JRadioButton("Or\u00E7amento");
		btnOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";
			}
		});
		btnOrcamento.setBounds(377, 90, 109, 23);
		panel.add(btnOrcamento);

		JRadioButton btnOS = new JRadioButton("Servi\u00E7o");
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Serviço";
			}
		});
		btnOS.setBounds(495, 90, 146, 23);
		panel.add(btnOS);
		ButtonGroup bg = new ButtonGroup();
		bg.add(btnOrcamento);
		bg.add(btnOS);

		JLabel lblSituaoAtualDo = new JLabel("Situa\u00E7\u00E3o atual do pedido:");
		lblSituaoAtualDo.setBounds(377, 135, 171, 14);
		panel.add(lblSituaoAtualDo);

		status = new JComboBox();
		status.setBounds(377, 160, 95, 20);
		panel.add(status);

		JLabel lblAvaliao = new JLabel("Avalia\u00E7\u00E3o*:");
		lblAvaliao.setBounds(10, 245, 66, 14);
		getContentPane().add(lblAvaliao);

		JLabel lblServio = new JLabel("Servi\u00E7o:");
		lblServio.setBounds(10, 288, 48, 14);
		getContentPane().add(lblServio);

		JLabel lblPeas = new JLabel("Pe\u00E7as*:");
		lblPeas.setBounds(10, 335, 48, 14);
		getContentPane().add(lblPeas);

		JLabel lblFuncionriop = new JLabel("Funcion\u00E1rio*:");
		lblFuncionriop.setBounds(10, 378, 66, 14);
		getContentPane().add(lblFuncionriop);

		avaliacao = new JTextField();
		avaliacao.setBounds(86, 245, 421, 20);
		getContentPane().add(avaliacao);
		avaliacao.setColumns(10);

		servicos = new JTextField();
		servicos.setBounds(86, 288, 421, 20);
		getContentPane().add(servicos);
		servicos.setColumns(10);

		pecas = new JTextField();
		pecas.setBounds(86, 335, 421, 20);
		getContentPane().add(pecas);
		pecas.setColumns(10);

		funcio = new JTextField();
		funcio.setBounds(85, 375, 183, 20);
		getContentPane().add(funcio);
		funcio.setColumns(10);

		JLabel lblValorTotal = new JLabel("Valor Total:");
		lblValorTotal.setBounds(326, 378, 75, 14);
		getContentPane().add(lblValorTotal);

		valorTot = new JTextField();
		valorTot.setText("0");
		valorTot.setBounds(411, 375, 96, 20);
		getContentPane().add(valorTot);
		valorTot.setColumns(10);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarOS();
			}
		});
		btnAdicionar.setBounds(10, 442, 89, 23);
		getContentPane().add(btnAdicionar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(140, 442, 89, 23);
		getContentPane().add(btnRemover);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.setBounds(263, 442, 89, 23);
		getContentPane().add(btnAtualizar);

		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setBounds(517, 442, 89, 23);
		getContentPane().add(btnImprimir);

		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(386, 442, 96, 23);
		getContentPane().add(btnConsultar);

		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				tipo = "Orçamento";
				btnOrcamento.setSelected(true);
			}
		});

	}

	private void pesquisarClientes() {
		String sql = "SELECT idCliente as Identificação, nome as Nome, telefone as Telefone from clientes where nome like ?";

		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, nomeCliente.getText() + "%");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void complete() {

		int completar = table.getSelectedRow();
		idDoCliente.setText(table.getModel().getValueAt(completar, 0).toString());

	}

	private void cadastrarOS() {

		String sql = "INSERT INTO servicos(tipo, status, pecas, avaliacao, servicoRealizado, funcionario, valorDoServico, idCliente) values(?,?,?,?,?,?,?,?)";
		
		try {
			pst = conexaoDB.prepareStatement(sql);
			pst.setString(1, tipo);
			pst.setString(2, getStatus().toString());
			pst.setString(3, pecas.getText());
			pst.setString(4, avaliacao.getText());
			pst.setString(5, servicos.getText());
			pst.setString(6, funcio.getText());
			pst.setString(7, valorTot.getText().replace(",", "."));
			pst.setString(8, idDoCliente.getText());
			
			pst.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public JComboBox getStatus() {
		return status;
	}
}
