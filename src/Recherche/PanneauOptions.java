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
	
	//L'option d'effectuer une recherche généralisée
	private JCheckBox caseRechercheGeneralisee;
	
	//L'option de rechercher des mots complets
	private JCheckBox caseMotComplet;
	
	//L'option d'effectuer une recherche incrémentale
	private JCheckBox caseIncremental;
	
	//L'option de rechercher des expressions régulières
	private JCheckBox caseExpressionsRegulieres;
	
	//Le panneau principal dans lequel ce panneau est ajouté
	private JPanel panneauPrincipal;
	
	public PanneauOptions(JPanel panneauPrincipal) {
		
		//assigne le panneauPrincipal recu en parametre
		this.panneauPrincipal = panneauPrincipal;
		
		// met box layout pour que les composants imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		initComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau d'options en appelant leurs
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
	 * Créé les cinq cases à cocher qui représenteront les options.
	 */
	private void creerCase() {
		
		caseSensibleCasse = nouvelleCase(caseSensibleCasse);
		caseRechercheGeneralisee = nouvelleCase(caseRechercheGeneralisee);
		caseMotComplet = nouvelleCase(caseMotComplet);
		caseIncremental = nouvelleCase(caseIncremental);
		caseExpressionsRegulieres = nouvelleCase(caseExpressionsRegulieres);
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
		
		titre.setText("Options");
		
		caseSensibleCasse.setText("Sensible à la casse");
		
		caseRechercheGeneralisee.setText("Recherche généralisée");
		
		caseMotComplet.setText("Mot complet");
		
		caseIncremental.setText("Incrémental");
		
		caseExpressionsRegulieres.setText("Expressions régulières");
	}
	
	/**
	 * On ajoute le titre du panneau et les cases à cocher.
	 */
	private void ajouterComposants() {
		
		add(titre);
		add(caseSensibleCasse);
		add(caseRechercheGeneralisee);
		add(caseMotComplet);
		add(caseIncremental);
		add(caseExpressionsRegulieres);
	}

	public JCheckBox getCaseSensibleCasse() {
		return caseSensibleCasse;
	}
}
