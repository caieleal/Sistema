package br.com.sistemtelas;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JMenuItem;

public class Menu extends JFrame {

	private JPanel contentPane;
	private JMenu mnRelatrios;
	private JMenuItem mntmUsurio;
	private JLabel usuarioLogado;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setResizable(false);

		setTitle("Sistema De Controle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1057, 713);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCadastro = new JMenu("Cadastro");
		menuBar.add(mnCadastro);

		mntmUsurio = new JMenuItem("Usu\u00E1rios");
		mntmUsurio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Usuarios telaUsuarios = new Usuarios();
				telaUsuarios.setVisible(true);
				telaUsuarios.setEnabled(true);
				panel.add(telaUsuarios);
			}
		});

		mnCadastro.add(mntmUsurio);
		
		JMenuItem mntmClientes_1 = new JMenuItem("Clientes");
		mntmClientes_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente cliente = new Cliente();
				cliente.setEnabled(true);
				cliente.setVisible(true);
				panel.add(cliente);
			}
		});
		mnCadastro.add(mntmClientes_1);
		
		JMenuItem mntmServios = new JMenuItem("Servi\u00E7os");
		mntmServios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Servicos servicos = new Servicos();
				servicos.setEnabled(true);
				servicos.setVisible(true);
				panel.add(servicos);
			}
		});
		mnCadastro.add(mntmServios);

		JMenuItem mntmClientes = new JMenuItem("Clientes");

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		mnRelatrios = new JMenu("Relat\u00F3rios");

		mnRelatrios.setEnabled(true);
		menuBar.add(mnRelatrios);

		JMenu mnSobre = new JMenu("Ajuda");
		menuBar.add(mnSobre);

		JMenuItem mntmSobre = new JMenuItem("Sobre");

		mntmSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre telaSobre = new Sobre();
				telaSobre.setVisible(true);
			}
		});
		mnSobre.add(mntmSobre);

		JButton btnDeslogar = new JButton("Deslogar");
		btnDeslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int deslogar = JOptionPane.showConfirmDialog(null, "Deseja mesmo deslogar?", "Atenção !",
						JOptionPane.YES_NO_OPTION);
				if (deslogar == JOptionPane.YES_OPTION) {
					dispose();
					Login telaLogin = new Login();
					telaLogin.setVisible(true);
				}
			}
		});
		menuBar.add(btnDeslogar);
		btnDeslogar.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(265, 21, 687, 530);
		contentPane.add(panel);
		panel.setLayout(null);

		usuarioLogado = new JLabel("Usu\u00E1rio");
		usuarioLogado.setBounds(10, 80, 179, 14);
		contentPane.add(usuarioLogado);

		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 157, 160, 14);
		contentPane.add(lblData);
		setLocationRelativeTo(null);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formataData = DateFormat.getDateInstance(DateFormat.DEFAULT);

				lblData.setText(formataData.format(data));

			}
		});
	}

	public JMenu getMnRelatrios() {
		return mnRelatrios;
	}

	public JLabel getUsuarioLogado() {
		return usuarioLogado;
	}
}
