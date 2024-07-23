package Recherche;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.*;


/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer </p>
 *
 * <p>Classe	: GUI.PanneauPrincipal</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve tous les panneaux qui
 * 				  composent notre fenêtre de recherche et de remplacement</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */

public class PanneauPrincipal extends JPanel{
	
	private JPanel panneauMots;
	
	private JPanel panneauDirection;
	
	private JPanel panneauOptions;
	
	private JPanel panneauBtn;
	
	private JPanel panneauFermer;
	
	/**
	 * Constructeur du panneau principal pour la fenêtre de recherche et 
	 * de remplacement
	 */
	public PanneauPrincipal() {
		
		// met box layout pour que les panneaux imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// construit le panneau où on insère notre mot à remplacer et son
		// remplacement et lui passe le panneau principal en
		// paramètre pour qu'on puisse interagir entre nos classes de
		// panneaux
		panneauMots = new PanneauMots(this);
		
		// construit le panneau où on insère nos choix de direction (JCheckBox)
		// et lui passe le panneau principal en paramètre pour qu'on puisse
		// interagir entre nos classes de panneaux
		panneauDirection = new PanneauDirection(this);
		
		// construit le panneau où on insère nos options (JCheckBox)
		// et lui passe le panneau principal en paramètre pour qu'on puisse
		// interagir entre nos classes de panneaux
		panneauOptions = new PanneauOptions(this);
		
		// construit le panneau où on insère nos boutons et lui passe le 
		// panneau principal en paramètre pour qu'on puisse interagir
		// entre nos classes de panneaux
		panneauBtn = new PanneauBtn(this);
		
		// construit le panneau où on insère notre boutons qui ferme la
		// fenêtre et lui passe le panneau principal en paramètre pour 
		// qu'on puisse interagir entre nos classes de panneaux
		panneauFermer = new PanneauFermer(this);
		
		// ajoute tous les panneaux créés au panneau principal
		add(panneauMots);
		add(panneauDirection);
		add(panneauOptions);
		add(panneauBtn);
		add(panneauFermer);
	}
}

