import javax.swing.JFrame;
//import du package javax
//API swing bibliothèque graphique intégrée au JDK
// JFrame -> classe équivalente au Frame de l'AWT. 
// JFrame représente une fenêtre principale possédant un titre, une taille modifiable, un menu (non obligatoire)

public class GameFrame extends JFrame{
	
	GameFrame() {
		this.add(new GamePanel());
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}
}
