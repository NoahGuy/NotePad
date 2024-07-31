package Recherche;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer </p>
 *
 * <p>Classe	: GUI.PanneauOptions</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve nos options de filtre.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */
public class PanneauOptions extends JPanel{
	
	//Le titre du panneau
	private JLabel titre;
	
	//L'option de rendre la recherche sensible à la casse
	private JCheckBox caseSensibleCasse;

	

	
	public PanneauOptions(JPanel panneauPrincipal) {

		
		// met box layout pour que les composants imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		initComposants();
		ajouterComposants();
	}
	
	/**
	 * Initialise tous les composants du panneau d'options en appelant leurs
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {
		

		titre = new JLabel("Options");
		caseSensibleCasse = new JCheckBox("Sensible à la casse");

	}

	
	/**
	 * On ajoute le titre du panneau et les cases à cocher.
	 */
	private void ajouterComposants() {
		
		add(titre);
		add(caseSensibleCasse);

	}

	public JCheckBox getCaseSensibleCasse() {
		return caseSensibleCasse;
	}
}
