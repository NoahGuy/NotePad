package Recherche;

import javax.swing.*;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer un éditeur de texte simple avec les 
 *    options ‘défaire’ (undo), via Ctrl+z, et ‘refaire’ (redo), via ‘Ctrl+y’. L'éditeur
 *    a aussi une fonctionnalité de recherche et de remplacement de mots qu'on accède via
 *    la commande ‘Ctrl+f’.</p>
 *
 * <p>Classe	: GUI.PanneauDirection</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve nos options de direction.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 07/08/24
 */
public class PanneauDirectionOptions extends JPanel{

	private JPanel direction;

	private JPanel options;
	//Le titre du panneau
	private JLabel titreDirection;
	
	//L'option d'aller vers l'avant
	private JRadioButton caseAvant;
	
	//L'option d'aller vers l'arrière
	private JRadioButton caseArriere;

	private JLabel titreOption;

	private JCheckBox sensibleCasse;

	
	/**
	 * Construit le panneau de direction où les options "avant" et 
	 * "arrière" (JCheckBox) s'y retrouvent.
	 *
	 * @param panneauPrincipal le panneau principal où on ajoute ce panneau.
	 */
	public PanneauDirectionOptions(JPanel panneauPrincipal) {

		// met box layout pour que les composants imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
				
		initComposants();

		ajouterComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau de direction en appelant leurs
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {

		direction = new JPanel();

		options = new JPanel();

		direction.setLayout(new BoxLayout(direction, BoxLayout.Y_AXIS));
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));

		titreDirection = new JLabel("Direction");

		caseAvant = new JRadioButton("Avant");
		caseAvant.setSelected(true);

		caseArriere = new JRadioButton("Arrière");

		ButtonGroup group = new ButtonGroup();

		titreOption = new JLabel("Options");

		sensibleCasse = new JCheckBox("Sensible à la casse");

		group.add(caseArriere);
		group.add(caseAvant);
	}


	
	/**
	 * On ajoute le titre du panneau et les cases à cocher.
	 */
	private void ajouterComposants() {
		
		direction.add(titreDirection);
		direction.add(caseAvant);
		direction.add(caseArriere);

		add(direction);

		options.add(titreOption);
		options.add(sensibleCasse);

		add(options);
	}

	public JRadioButton getCaseArriere() {

		return caseArriere;
	}

	public JCheckBox getSensibleCasse() {

		return sensibleCasse;
	}
}

