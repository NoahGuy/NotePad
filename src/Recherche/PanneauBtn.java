package Recherche;

import Notepad.Fonctions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer </p>
 *
 * <p>Classe	: GUI.PanneauBtn</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve nos boutons.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */
public class PanneauBtn extends JPanel{
	
	//Le bouton qui permet de trouver le mot recherché
	private JButton btnTrouver;
	
	//Le bouton qui permet de tout selectionner les mots recherchés
	private JButton btnSelectionnerTout;
	
	//Le bouton qui permet de remplacer le mot recherché par son remplaçant et
	//de sélectionner le prochain mot 
	private JButton btnRemplacerTrouver;
	
	//Le bouton qui permet de remplacer le mot recherché par son remplaçant
	private JButton btnRemplacer;
	
	//Le bouton qui permet de remplacer toutes les instances du mot recherché 
	//par son remplaçant
	private JButton btnRemplacerTout;
	
	//Le bouton qui permet d'accéder à la prochaine instance du mot recherché
	private JButton btnSuivant;
	
	//Le panneau principal dans lequel ce panneau est ajouté
	private Recherche.PanneauPrincipal panneauPrincipal;

	private Fonctions fonctions;
	
	public PanneauBtn(PanneauPrincipal panneauPrincipal, Fonctions fonctions) {

		this.fonctions = fonctions;
		
		//assigne le panneauPrincipal recu en parametre
		this.panneauPrincipal = panneauPrincipal;
		
		// met box layout pour que les composants imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		initComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau de boutons en appelant leurs
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {
	
		creerBtn();
		
		initialiserComposants();
		
		ajouterComposants();
	}
	
	
	/**
	 * Créé les six boutons
	 */
	private void creerBtn() {
		
		btnTrouver = nouveauBtn(btnTrouver);
		btnSelectionnerTout = nouveauBtn(btnSelectionnerTout);
		btnRemplacerTrouver = nouveauBtn(btnRemplacerTrouver);
		btnRemplacer = nouveauBtn(btnRemplacer);
		btnRemplacerTout = nouveauBtn(btnRemplacerTout);
		btnSuivant = nouveauBtn(btnSuivant);
	}
	
	/**
	 * Construit un nouveau bouton et l'assigne à celui reçu en paramètre
	 *
	 * @param btn le bouton auquel le bouton créé sera assigné.
	 * 
	 * @return un JButton associé à celui reçu en paramètre.
	 */
	private JButton nouveauBtn(JButton btn) {
		
		btn = new JButton();
		
		return btn;
	}
	
	/**
	 * On ajoute un titre à chaque bouton.
	 */
	private void initialiserComposants() {
		
		btnTrouver.setText("Trouver");
		
		btnSelectionnerTout.setText("Sélectionner tout");
		
		btnRemplacerTrouver.setText("Remplacer/Trouver");
		
		btnRemplacer.setText("Remplacer");
		
		btnRemplacerTout.setText("Remplacer tout");
		
		btnSuivant.setText("Suivant");

		btnTrouver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fonctions.rechercher(panneauPrincipal.getPanneauMots().getMotRecherche(),
						panneauPrincipal.getPanneauOptions().getCaseSensibleCasse());
			}
		});
	}
	
	/**
	 * On ajoute les boutons.
	 */
	private void ajouterComposants() {
		
		add(btnTrouver);
		add(btnSelectionnerTout);
		add(btnRemplacerTrouver);
		add(btnRemplacer);
		add(btnRemplacerTout);
		add(btnSuivant);
	}
}
