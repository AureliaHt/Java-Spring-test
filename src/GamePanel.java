import java.awt.*;
import java.awt.event.*;
// AWT = Abstract Windows Toolkit, pour écrire des interfaces graphiques indépendantes du système d'exploitation sur lequel elle va fonctionner.
// AWT, le toolkit contient des classes décrivant les composants graphiques, les polices, les couleurs et les images.
// Action Listener pour gérer les événements utilisateurs, en l'occurence clic de souris et touche ENTRER
// KeyAdaptater abstract class for receiving keyboards events.
import javax.swing.*;
import javax.swing.JPanel;
// import du package javax
// API swing bibliothèque graphique intégrée au JDK
// Tous les composants Swing sont des beans (composants logiciels réutilisables) et sont des composants légers.
// JPanel = conteneur pouvant stocker et organiser un groupe de composants
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
	// variables de classes, stockent des informations (width, height) sur l'objet.
	// static , keyword, permet de stocker une constante
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 75;
	// coordinates for the body parts of the snake
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	// bodyParts of the snake at the beginning
	int bodyParts = 6;
	// applesEaten will start at 0
	int applesEaten;
	// coordinates of the apples who will appear randomly
	int appleX;
	int appleY;
	// snake begin by going right
	char direction = 'R';
	boolean gameIsRunning = false;
	Timer timer;
	Random random;

	// constructing the game panel
	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	// method to initialize the game
	public void startGame() {
		newApple();
		gameIsRunning = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}

	// method use to paint elements like the black background color.
	// keyword 'super' to relate and deal with the parent class object -> GamePanel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	// draw method to draw points, lines...
	// currently used to draw the red apples, the snake, and the grid
	public void draw(Graphics g) {
		if (gameIsRunning) {
			for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
				g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
				g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
			}
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					// head of the snake
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					// snake body
					g.setColor(new Color(45, 180, 0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}
			g.setColor(Color.yellow);
			g.setFont(new Font("Ink Free", Font.BOLD, 35));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		} else {
			gameOver(g);
		}
	}

	// method for moving the snake with keyboard (up, down, left, right)
	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;
		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			break;
		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;
		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;
		}
	}

	public void newApple() {
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
	}

	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}

	public void checkCollisions() {
		// check if the snake head collides with his body
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				gameIsRunning = false;
			}
		}
		// check if the snake head touches left border
		if (x[0] < 0) {
			gameIsRunning = false;
		}
		// check if the snake head touches right border
		if (x[0] > SCREEN_WIDTH) {
			gameIsRunning = false;
		}
		// check if the snake head touches top border
		if (y[0] < 0) {
			gameIsRunning = false;
		}
		// check if the snake head touches bottom border
		if (x[0] > SCREEN_WIDTH) {
			gameIsRunning = false;
		}
		// if snake collides against his body or a border, the timer stops
		if (!gameIsRunning) {
			timer.stop();
		}
	}

	public void gameOver(Graphics g) {
		// implementing a game over text
		g.setColor(Color.yellow);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
	}

	// annotation override pour définir une méthode héritée de la classe parente.
	// Override annotation to define
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (gameIsRunning) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
					break;
				}
			}
		}
	}
}
