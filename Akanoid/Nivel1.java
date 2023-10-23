package Akanoid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import posta.Pong;
import prototipo.Game2;

public class Nivel1 extends JPanel implements ActionListener, KeyListener{
	
	//pantalla
	private int width = 800;
    private int height = 600;
    //paddle
    private int paddleWidth = 100;
    private int paddleHeight = 20;
    private int paddleVel = 15;
    //posicion paddle
    private int paddlePosX = width / 2 - paddleWidth / 2;
    private int paddlePosY = height - paddleHeight - 20;
    //ball
    private int ball = 20;  
    private int ballVelX = 5;
    private int ballVelY = 5;
    private JLabel tierra;
    //posicion ball
    private int ballPosX = width / 2 ;
    private int ballPosY = height / 2 ;
    //elementos
    private int vidas = 3;
    private int score = 0;
    private int time = 0; // Contador de tiempo en segundos
    private Timer tiempo; // Timer para el contador de tiempo
    
    private ImageIcon backgroundImage; // Variable para la imagen de fondo
    
    private Bloque[] bloques; //clase bloques
    
    public Nivel1() {
    	//pantalla
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        addKeyListener(this);

        backgroundImage = new ImageIcon(getClass().getResource("/img/fondoniveles.png"));
        
        tierra = new JLabel();
        tierra.setIcon(new ImageIcon(Pong.class.getResource("/img/BallTierra.png")));
        tierra.setSize(ball, ball);
        tierra.setOpaque(true);
        tierra.setBackground(new Color(0,0,0,0));
        add(tierra);

        // Configuraciï¿½n del temporizador
        Timer timer = new Timer(30, this);
        timer.start();

        // Inicializa el Timer para el contador de tiempo
        tiempo = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	time++;
                repaint();
            }
        });
        tiempo.start();
        
        //configuracion de bloques
        bloques = new Bloque[7];
        for (int i = 0; i < bloques.length; i++) {
            bloques[i] = new Bloque(width - 750 + i * 100, height - 400, 50, 20);
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	//Dibujar imagen de fondo que abarca todo el panel
    	g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
    	
    	//Graficar paddle y ball
    	g.setColor(Color.WHITE);
    	g.fillRect(paddlePosX, paddlePosY, paddleWidth, paddleHeight);
    	//g.fillOval(ballPosX, ballPosY, ball, ball);
    	tierra.setLocation(ballPosX, ballPosY);
    	
    	//Dibujar bloques
    	for(Bloque bloque : bloques) {
    		if(bloque.visible) {
    			g.setColor(Color.WHITE);
    			g.fillRect(bloque.x, bloque.y, bloque.ancho, bloque.alto);
    		}
    	}
    	// Graficar elementos 
    	g.setColor(Color.WHITE);
    	g.setFont(new Font("Courier New", Font.BOLD,22));
    	g.drawString("Tiempo: "+time, 110, 50);
    	g.drawString("Vidas: "+ vidas, 370, 50);
    	g.drawString("Score: "+score, 650, 50);	
    }
        
        public void actionPerformed(ActionEvent e) {
            // Actualiza la posiciï¿½n de la pelota
        	ballPosX += ballVelX;
        	ballPosY += ballVelY;
            // Rebote en los bordes laterales
            if (ballPosX <= 0 || ballPosX >= width - ball) {
            	ballVelX = -ballVelX;
            }
            // Rebote en la parte superior
            if (ballPosY <= 20) {
            	ballVelY = -ballVelY;
            }

            // Pierde una vida si toca el borde inferior
            if (ballPosY >= height - ball) {
                vidas--; // Pierde una vida cuando la bola toca el borde inferior
                score-=20;
                if (vidas == 0) {
                	tiempo.stop();
                	score = score + (vidas*20);
                    JOptionPane.showMessageDialog(this, "Perdiste! Tu puntuacion fue de: "+score);
                    System.exit(0);   
                } else {              
                	    // Reubicar la bola en una posiciï¿½n aleatoria en el panel
                	ballPosX = (int) (Math.random() * (width - ball));
                	ballPosY = (int) (Math.random() * (height - ball));
                }
            }
            //acaba el juego si el tim es igual a 15seg
            if (time >= 60) {
                tiempo.stop();
                JOptionPane.showMessageDialog(this, "¡Se te acabó el tiempo! Tu puntuación fue de: " + score);
                System.exit(0);
            }
            boolean todosBloquesDestruidos = true;
            for (Bloque bloque : bloques) {
                if (bloque.visible) {
                    todosBloquesDestruidos = false;
                    break;
                }
            }
            if (todosBloquesDestruidos && time<60) {
                tiempo.stop();
                JOptionPane.showMessageDialog(this, "¡Ganaste! Puntuación final: " + score);
                System.exit(0);
            }

            // Colisiï¿½n con la paleta del jugador
            if (ballPosY + ball >= paddlePosY &&
            		ballPosX + ball >= paddlePosX &&
                    		ballPosX <= paddlePosX + paddleWidth) {
            	ballVelY = -ballVelY;
            	score += 10;
            }
            
            //Colision con los bloques
            for (Bloque bloque : bloques) {
                if (bloque.visible) {
                    if (ballPosX + ball >= bloque.x &&
                    		ballPosX <= bloque.x + bloque.ancho &&
                    				ballPosY + ball >= bloque.y &&
                    						ballPosY <= bloque.y + bloque.alto) {
                        // Colisiï¿½n detectada, cambia la direcciï¿½n de la pelota y oculta el bloque
                    	ballVelY = -ballVelY;
                        bloque.visible = false;
                        score+=5;
                    }
                }
            }

            // Redibuja la escena
            repaint();
        }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT && paddlePosX > 0) {
        	paddlePosX -= paddleVel;
        } else if (keyCode == KeyEvent.VK_RIGHT && paddlePosX < width - paddleWidth) {
        	paddlePosX += paddleVel;
        }
    }

    public void keyReleased(KeyEvent e) {
    }
	
	public static void main(String[] args) {
	    JFrame frame = new JFrame("Pong Game");
	    Nivel1 game = new Nivel1();
	    frame.setResizable(false);
	    frame.add(game);
	    frame.pack();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	}
}