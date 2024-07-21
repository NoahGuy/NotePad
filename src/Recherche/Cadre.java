package Recherche;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cadre extends JFrame implements Runnable {
	
	private JPanel panneauPrincipal;
	
	public Cadre(String titre) {
		
		super(titre);
		
		this.setSize(300, 575);
	}

	@Override
	public void run() {
		
		panneauPrincipal = new PanneauPrincipal();
		
		setContentPane(panneauPrincipal);			
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		setVisible(true);
		
	}

}
