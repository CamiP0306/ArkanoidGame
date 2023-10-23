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

public class Inicio extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inicio() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) { // Funcion para establecer imagen de fondo de la ventana
				ImageIcon imgfondo = new ImageIcon(Inicio.class.getResource("img/Inicio.png"));
				g.drawImage(imgfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
				g.setColor(new Color(255, 255, 255, 0));
				g.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.main(new String[] {});
				Inicio.this.setVisible(false);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Inicio.class.getResource("/img/menu.PNG")));
		btnNewButton_1.setBounds(350, 352, 89, 40);
		contentPane.add(btnNewButton_1);

	}
}