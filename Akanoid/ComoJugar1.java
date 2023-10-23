package Akanoid;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ComoJugar1 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComoJugar1 frame = new ComoJugar1();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ComoJugar1() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel() {
			@Override
			           protected void paintComponent(Graphics g) { // Funcion para establecer imagen de fondo de la ventana
			           ImageIcon imgfondo = new ImageIcon(Menu.class.getResource("img/comojugar1.PNG"));
			               g.drawImage(imgfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			               g.setColor(new Color(255, 255, 255, 0));
			               g.fillRect(0, 0, getWidth(), getHeight());
			           }
			       };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nivel1.main(new String[] {});
				ComoJugar1.this.setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ComoJugar1.class.getResource("/Akanoid/img/start.PNG")));
		btnNewButton.setBounds(352, 404, 89, 34);
		contentPane.add(btnNewButton);
	}

}
