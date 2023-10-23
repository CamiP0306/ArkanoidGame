package Akanoid;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ComoJugar3 extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComoJugar3 frame = new ComoJugar3();
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
	public ComoJugar3() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel() {
			@Override
			           protected void paintComponent(Graphics g) { // Funcion para establecer imagen de fondo de la ventana
			           ImageIcon imgfondo = new ImageIcon(Menu.class.getResource("img/comojugar3.PNG"));
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
				Nivel3.main(new String[] {});
				ComoJugar3.this.setVisible(false);
			}
		});
		btnNewButton.setIcon(new ImageIcon(ComoJugar3.class.getResource("/Akanoid/img/start.PNG")));
		btnNewButton.setBounds(352, 404, 89, 34);
		contentPane.add(btnNewButton);
	}

}
