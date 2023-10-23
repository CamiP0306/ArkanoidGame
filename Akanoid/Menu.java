package Akanoid;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import posta.Pong;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel() {
			@Override
			           protected void paintComponent(Graphics g) { // Funcion para establecer imagen de fondo de la ventana
			           ImageIcon imgfondo = new ImageIcon(Menu.class.getResource("img/menu.png"));
			               g.drawImage(imgfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			               g.setColor(new Color(255, 255, 255, 0));
			               g.fillRect(0, 0, getWidth(), getHeight());
			           }
			       };
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton(""){
			@Override
	           protected void paintComponent(Graphics g) { // Funcion para establecer imagen de fondo de la ventana
	           ImageIcon imgfondo = new ImageIcon(Menu.class.getResource("img/nivel1.png"));
	               g.drawImage(imgfondo.getImage(), 0, 0, getWidth(), getHeight(), this);
	               g.setColor(new Color(255, 255, 255, 0));
	               g.fillRect(0, 0, getWidth(), getHeight());
	           }
	       };
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComoJugar1.main(new String[] {});
				Menu.this.setVisible(false);
			}
		});
		btnNewButton.setBounds(100, 384, 149, 60);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComoJugar2.main(new String[] {});
				Menu.this.setVisible(false);
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Menu.class.getResource("/Akanoid/img/nivel2.PNG")));
		btnNewButton_1.setBounds(325, 384, 149, 60);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComoJugar3.main(new String[] {});
				Menu.this.setVisible(false);
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Menu.class.getResource("/Akanoid/img/nivel3.PNG")));
		btnNewButton_2.setBounds(546, 384, 149, 67);
		contentPane.add(btnNewButton_2);
		
		
	}
}
