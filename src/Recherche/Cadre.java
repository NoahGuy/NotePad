package Recherche;

import Notepad.Fonctions;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


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
	
	private Recherche.PanneauPrincipal panneauPrincipal;

	private Fonctions fonctions;
	
	public Cadre(String titre, Fonctions fonctions) {
		
		super(titre);
		
		this.setSize(400, 400);

		this.fonctions = fonctions;
	}

	/**
	 * Initilaisation des caracteristiques du cadre et ajout du panneau
	 * principal dans le cadre.
	 */
	@Override
	public void run() {

		//chaque fois qu'on veut effectuer une nouvelle recherche, on met la position a 0 afin de commencer au curseur
		fonctions.setDernierePositionTrouve(0);
		
		panneauPrincipal = new PanneauPrincipal(fonctions, this);
		
		setContentPane(panneauPrincipal);			
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
		setVisible(true);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {

				fonctions.enleverSurlignage();
			}
		});
		
	}

}

