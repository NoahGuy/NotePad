package Recherche;

import Notepad.Fonctions;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer un éditeur de texte simple avec les 
 *    options ‘défaire’ (undo), via Ctrl+z, et ‘refaire’ (redo), via ‘Ctrl+y’. L'éditeur
 *    a aussi une fonctionnalité de recherche et de remplacement de mots qu'on accède via
 *    la commande ‘Ctrl+f’. </p>
 *
 * <p>Classe	: GUI.PanneauBtn</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve nos boutons.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 07/08/24
 */
public class PanneauBtn extends JPanel{
	
	//Le bouton qui permet de trouver le mot recherché
	private JButton btnTrouver;

	
	//Le bouton qui permet de remplacer le mot recherché par son remplaçant et
	//de sélectionner le prochain mot 
	private JButton btnRemplacerTrouver;
	
	//Le bouton qui permet de remplacer le mot recherché par son remplaçant
	private JButton btnRemplacer;

	private JButton btnSelectionnerTout;
	
	//Le bouton qui permet de remplacer toutes les instances du mot recherché 
	//par son remplaçant
	private JButton btnRemplacerTout;

	private PanneauMots panneauMots;


	private PanneauDirectionOptions panneauDirection;

	private Fonctions fonctions;
	
	public PanneauBtn(Fonctions fonctions, PanneauMots panneauMots,
					  PanneauDirectionOptions panneauDirection) {

		this.panneauMots = panneauMots;

		this.panneauDirection = panneauDirection;

		this.fonctions = fonctions;

		setLayout(new FlowLayout());
				
		initComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau de boutons en appelant leurs
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {



		creerBtns();
		ajouterBtns();
	}
	
	

	private void creerBtns() {
		
		btnTrouver = new JButton("Trouver");
		btnSelectionnerTout = new JButton("Selectionner");
		btnRemplacerTrouver = new JButton("Remplacer/Trouver");
		btnRemplacer = new JButton("Remplacer");
		btnRemplacerTout = new JButton("Remplacer tout");

		// Désactiver le bouton Remplacer initialement
		btnRemplacer.setEnabled(false);

		btnTrouver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				fonctions.rechercher(
						panneauMots.getMotRecherche(),
						panneauDirection.getSensibleCasse(),
						panneauDirection.getCaseArriere());

				panneauMots.ajouterSaisies();

				btnRemplacer.setEnabled(true);
			}
		});

		btnRemplacerTout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				fonctions.remplacer(
						panneauMots.getMotRecherche(),
						panneauMots.getMotRemplacer(),
						panneauDirection.getSensibleCasse(),
						panneauDirection.getCaseArriere());

            }
		});

		btnRemplacer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				fonctions.remplacerOccurrenceSurligneeEnVert(
						panneauMots.getMotRecherche(),
						panneauMots.getMotRemplacer(),
						panneauDirection.getSensibleCasse(),
						panneauDirection.getCaseArriere());

				btnRemplacer.setEnabled(false);
			}
		});
	}


	
	/**
	 * On ajoute les boutons.
	 */
	private void ajouterBtns() {
		
		add(btnTrouver);
		add(btnSelectionnerTout);
		add(btnRemplacerTrouver);
		add(btnRemplacer);
		add(btnRemplacerTout);

	}
}
