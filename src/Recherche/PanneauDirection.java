package Recherche;

import java.awt.Color;

import javax.swing.*;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer </p>
 *
 * <p>Classe	: GUI.PanneauDirection</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve nos options de direction.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */
public class PanneauDirection extends JPanel{
	
	//Le titre du panneau
	private JLabel titre;
	
	//L'option d'aller vers l'avant
	private JRadioButton caseAvant;
	
	//L'option d'aller vers l'arrière
	private JRadioButton caseArriere;

	
	/**
	 * Construit le panneau de direction où les options "avant" et 
	 * "arrière" (JCheckBox) s'y retrouvent.
	 *
	 * @param panneauPrincipal le panneau principal où on ajoute ce panneau.
	 */
	public PanneauDirection(JPanel panneauPrincipal) {

		// met box layout pour que les composants imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		initComposants();

		ajouterComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau de direction en appelant leurs
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {

		titre = new JLabel("Direction");

		caseAvant = new JRadioButton("Avant");
		caseAvant.setSelected(true);

		caseArriere = new JRadioButton("Arrière");

		ButtonGroup group = new ButtonGroup();
		group.add(caseArriere);
		group.add(caseAvant);
	}


	
	/**
	 * On ajoute le titre du panneau et les cases à cocher.
	 */
	private void ajouterComposants() {
		
		add(titre);
		add(caseAvant);
		add(caseArriere);
	}

	public JRadioButton getCaseArriere() {

		return caseArriere;
	}

	//test
}

