package Recherche;

import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de </p>
 *
 * <p>Classe	: GUI.Cadre</p>
 *
 * <p>Desc		: Cadre (JFrame) dans lequel se retrouvera notre fenêtre de 
 * 				  recherche et de remplacement.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */

public class Cadre extends JFrame implements Runnable {
	
	private JPanel panneauPrincipal;
	
	public Cadre(String titre) {
		
		super(titre);
		
		this.setSize(600, 600);
	}

	/**
	 * Initilaisation des caracteristiques du cadre et ajout du panneau
	 * principal dans le cadre.
	 */
	@Override
	public void run() {
		
		panneauPrincipal = new PanneauPrincipal();
		
		setContentPane(panneauPrincipal);			
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		setVisible(true);
		
	}

}

