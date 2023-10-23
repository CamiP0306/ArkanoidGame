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

public class Nivel3 extends JPanel implements ActionListener, KeyListener{
	
	//pantalla
	private int width = 800;
    private int height = 600;
    //paddle
    private int paddleWidth = 50;
    private int paddleHeight = 25;
    private int paddleVel = 15;
    //posicion paddle
    private int paddlePosX = width / 2 - paddleWidth / 2;
    private int paddlePosY = height - paddleHeight - 20;
    //ball
    private int ball = 20;  
    private int ballVelX = 10;
    private int ballVelY = 10;
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
    
    private Bloque[] bloquesFila1, bloquesFila2, bloquesFila3; //clase bloques
    
    public Nivel3() {
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
        
     // Configuración de bloques para la primera fila
        bloquesFila1 = new Bloque[7];
        for (int i = 0; i < bloquesFila1.length; i++) {
            bloquesFila1[i] = new Bloque(width - 750 + i * 100, height - 400, 50, 20);
        }

        // Configuración de bloques para la segunda fila
        bloquesFila2 = new Bloque[7];
        for (int i = 0; i < bloquesFila2.length; i++) {
            bloquesFila2[i] = new Bloque(width - 750 + i * 100, height - 450, 50, 20); 
        }
        
        // Configuracion de blowues para la tercer fila
        bloquesFila3 = new Bloque[7];
        for (int i = 0; i < bloquesFila3.length; i++) {
            bloquesFila3[i] = new Bloque(width - 750 + i * 100, height - 500, 50, 20); 
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
    	for(Bloque bloque : bloquesFila1) {
    		if(bloque.visible) {
    			g.setColor(Color.WHITE);
    			g.fillRect(bloque.x, bloque.y, bloque.ancho, bloque.alto);
    		}
    	}
    	for(Bloque bloque : bloquesFila2) {
    		if(bloque.visible) {
    			g.setColor(Color.WHITE);
    			g.fillRect(bloque.x, bloque.y, bloque.ancho, bloque.alto);
    		}
    	}
    	for(Bloque bloque : bloquesFila3) {
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
            if (time >= 25) {
                tiempo.stop();
                JOptionPane.showMessageDialog(this, "¡Se te acabó el tiempo! Tu puntuación fue de: " + score);
                System.exit(0);
            }
            boolean todosBloquesDestruidos = true;
            for (Bloque bloque : bloquesFila1) {
                if (bloque.visible) {
                    todosBloquesDestruidos = false;
                    break;
                }
            }
            if (todosBloquesDestruidos) {
                for (Bloque bloque : bloquesFila2) {
                    if (bloque.visible) {
                        todosBloquesDestruidos = false;
                        break;
                    }
                }
            }

            if (todosBloquesDestruidos) {
                for (Bloque bloque : bloquesFila3) {
                    if (bloque.visible) {
                        todosBloquesDestruidos = false;
                        break;
                    }
                }
            }
            if (todosBloquesDestruidos) {
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
            for (Bloque bloque : bloquesFila1) {
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
            for (Bloque bloque : bloquesFila2) {
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
            for (Bloque bloque : bloquesFila3) {
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
	    Nivel3 game = new Nivel3();
	    frame.setResizable(false);
	    frame.add(game);
	    frame.pack();
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	    frame.setLocationRelativeTo(null);
	}
}