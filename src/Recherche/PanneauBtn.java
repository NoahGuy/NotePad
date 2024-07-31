package Recherche;

import Notepad.Fonctions;

import java.awt.*;
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
	
	public PanneauBtn(Fonctions fonctions, PanneauMots panneauMots, PanneauDirectionOptions panneauDirection) {

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

		btnTrouver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				fonctions.rechercher(panneauMots.getMotRecherche(), panneauDirection.getSensibleCasse(),
						panneauDirection.getCaseArriere());
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
