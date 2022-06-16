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

	GamePanel(){
		
	}
	
	
	public void startGame() {
		
	}
	// méthode qui définit où doit être placé le "painting code"
	public void paintComponent(Graphics g) {
		
	}
	// méthode pour dessiner des points, lignes et courbes au sein d'un conteneur définit précédement
	public void draw(Graphics g) {
		
	}
	// méthode pour apporter du mouvement dans notre dessin
	public void move() {
		
	}
	
	public void checkApple() {
		
	}
	
	public void checkCollisions() {
		
	}
	
	public void gameOver(Graphics g) {
		
	}
	
	// annotation override pour définir une méthode héritée de la classe parente.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
	}
}
