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


		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


		panneauMots = new PanneauMots(this);

		panneauDirection = new PanneauDirectionOptions(this);


		panneauBtn = new PanneauBtn(fonctions, panneauMots, panneauDirection);

		panneauFermer = new PanneauFermer(this, cadre);
	}

	public void ajouterComposants() {

		add(panneauMots);
		add(panneauDirection);
		add(panneauBtn);
		add(panneauFermer);
	}
}

