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
	
	//Le panneau principal dans lequel ce panneau est ajouté
	private JPanel panneauPrincipal;
	
	/**
	 * Construit le panneau de direction où les options "avant" et 
	 * "arrière" (JRadioButton) s'y retrouvent.
	 *
	 * @param panneauPrincipal le panneau principal où on ajoute ce panneau.
	 */
	public PanneauDirection(JPanel panneauPrincipal) {
		
		//assigne le panneauPrincipal recu en parametre
		this.panneauPrincipal = panneauPrincipal;
		
		// met box layout pour que les composants imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		initComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau de direction en appelant leurs
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {
		
		creerEtiq();
		creerCase();
		
		initialiserComposants();
		
		ajouterComposants();
	}
	
	/**
	 * Créé l'étiquette qui comporte le titre du panneau
	 */
	private void creerEtiq() {
		
		titre = nouvelleEtiq(titre);
	}
	
	/**
	 * Construit une nouvelle étiquette et l'assigne à celle reçue en paramètre
	 *
	 * @param etiq l'étiquette à laquelle l'étiquette crée sera assignée.
	 * 
	 * @return un JLabel associé à celui reçu en paramètre.
	 */
	private JLabel nouvelleEtiq(JLabel etiq) {
		
		etiq = new JLabel();
		
		return etiq;
	}
	
	/**
	 * Créé les deux cases à cocher qui représenteront les options.
	 */
	private void creerCase() {
		
		caseAvant = nouvelleCase(caseAvant);
		caseArriere = nouvelleCase(caseArriere);
	}
	
	/**
	 * Construit une nouvelle case et l'assigne à celle reçue en paramètre
	 *
	 * @param caseACocher la case à laquelle la case crée sera assignée.
	 * 
	 * @return un JRadioButton associé à celui reçu en paramètre.
	 */
	private JRadioButton nouvelleCase(JRadioButton caseACocher) {
		
		caseACocher = new JRadioButton();
		
		return caseACocher;
	}
	
	/**
	 * On ajoute le titre du panneau et celui de chaque case à cocher.
	 */
	private void initialiserComposants() {
		
		titre.setText("Direction");
		
		caseAvant.setText("Avant");
		
		caseArriere.setText("Arrière");
	}
	
	/**
	 * On ajoute le titre du panneau et les cases à cocher.
	 */
	private void ajouterComposants() {
		
		add(titre);
		add(caseAvant);
		add(caseArriere);
	}
}

