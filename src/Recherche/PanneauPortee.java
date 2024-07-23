package Recherche;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer </p>
 *
 * <p>Classe	: GUI.PanneauPortee</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve nos options de portée.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */
public class PanneauPortee extends JPanel{
	
	//Le titre du panneau
	private JLabel titre;
	
	//L'option de sélectionner toutes les lignes
	private JCheckBox caseTous;
	
	//L'option de sélectionner seulement la ligne actuelle
	private JCheckBox caseLignesSelectionnees;
	
	//Le panneau principal dans lequel ce panneau est ajouté
	private JPanel panneauPrincipal;
	
	/**
	 * Construit le panneau de direction où les options "Tous" et 
	 * "Lignes selectionnées" (JCheckBox) s'y retrouvent.
	 *
	 * @param panneauPrincipal le panneau principal où on ajoute ce panneau.
	 */
	public PanneauPortee(JPanel panneauPrincipal) {
		
		//assigne le panneauPrincipal recu en parametre
		this.panneauPrincipal = panneauPrincipal;
		
		// met box layout pour que les composants imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		initComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau de portée en appelant leurs
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
		
		caseTous = nouvelleCase(caseTous);
		caseLignesSelectionnees = nouvelleCase(caseLignesSelectionnees);
	}
	
	/**
	 * Construit une nouvelle case et l'assigne à celle reçue en paramètre
	 *
	 * @param caseACocher la case à laquelle la case crée sera assignée.
	 * 
	 * @return un JCheckBox associé à celui reçu en paramètre.
	 */
	private JCheckBox nouvelleCase(JCheckBox caseACocher) {
		
		caseACocher = new JCheckBox();
		
		return caseACocher;
	}
	
	/**
	 * On ajoute le titre du panneau et celui de chaque case à cocher.
	 */
	private void initialiserComposants() {
		
		titre.setText("Portée");
		
		caseTous.setText("Tous");
		
		caseLignesSelectionnees.setText("Lignes sélectionnées");
	}
	
	/**
	 * On ajoute le titre du panneau et les cases à cocher.
	 */
	private void ajouterComposants() {
		
		add(titre);
		add(caseTous);
		add(caseLignesSelectionnees);
	}
}

