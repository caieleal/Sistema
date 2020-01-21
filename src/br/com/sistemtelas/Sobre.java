package br.com.sistemtelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;

public class Sobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre frame = new Sobre();
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
	public Sobre() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDesenvolvidoPorCai = new JLabel("Desenvolvido Por Cai\u00EA Leal");
		lblDesenvolvidoPorCai.setBounds(42, 217, 222, 14);
		contentPane.add(lblDesenvolvidoPorCai);
		
		JTextPane txtpnSistemaDesenvolvidoPara = new JTextPane();
		txtpnSistemaDesenvolvidoPara.setText("Sistema desenvolvido para controle de servi\u00E7os  e opera\u00E7\u00F5es com o intuito de ampliar meus conhecimentos em desenvolvimento de software em Java, integrando banco de dados MySQL atrav\u00E9s da utiliza\u00E7\u00E3o do JDBC.");
		txtpnSistemaDesenvolvidoPara.setBounds(42, 22, 269, 92);
		contentPane.add(txtpnSistemaDesenvolvidoPara);
		setLocationRelativeTo(null);
	}
}
