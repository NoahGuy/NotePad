package Recherche;

import Notepad.Fonctions;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


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
	
	private PanneauMots panneauMots;
	
	private PanneauDirectionOptions panneauDirection;

	
	private PanneauBtn panneauBtn;
	
	private PanneauFermer panneauFermer;


	
	/**
	 * Constructeur du panneau principal pour la fenêtre de recherche et 
	 * de remplacement
	 */
	public PanneauPrincipal(Fonctions fonctions, Cadre cadre) {

		initComposants(fonctions, cadre);

		ajouterComposants();
	}

	public void initComposants(Fonctions fonctions, Cadre cadre) {

		// met box layout pour que les panneaux imbriqués soient les uns par
		// dessus les autres.
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// construit le panneau où on insère notre mot à remplacer et son
		// remplacement.
		panneauMots = new PanneauMots();

		
		panneauDirection = new PanneauDirectionOptions(this);

		// construit le panneau où on insère nos boutons et utilisation de paramètres
		// pour qu'on puisse interagir entre nos classes de panneaux et avec nos
		// méthodes de la classe fonctions.
		panneauBtn = new PanneauBtn(fonctions, panneauMots, panneauDirection);

		// construit le panneau où on insère notre boutons qui ferme la
		// fenêtre et lui passe le panneau principal et le cadre en paramètre 
		// afin qu'il puisse interagir ensemble.
		panneauFermer = new PanneauFermer(this, cadre);
	}

	public void ajouterComposants() {

		// ajoute tous les panneaux créés au panneau principal
		add(panneauMots);
		add(panneauDirection);
		add(panneauBtn);
		add(panneauFermer);
	}
}

