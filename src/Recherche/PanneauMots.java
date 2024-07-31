package Recherche;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer </p>
 *
 * <p>Classe	: GUI.PanneauMots</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve notre mot à remplacer et son
		          remplacement.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */
public class PanneauMots extends JPanel{
	
	//Le titre de la barre de recherche du mot à remplacer
	private JLabel trouver;
	
	//Le titre de la barre de recherche du mot remplaçant
	private JLabel remplacer;
	
	//La barre de recherche dans laquelle l'utilisateur écrivera le mot
	//à remplacer
	private JComboBox<String> saisieTrouver;

	
	//La barre de recherche dans laquelle l'utilisateur écrivera le mot
	//remplaçant
	private JComboBox<String> saisieRemplacer;

	


	
	/**
	 * Construit le panneau de mots où on insère notre mot à remplacer et son
	 * remplacement.
	 *

	 */
	public PanneauMots() {
		
		//assigne le panneauPrincipal recu en parametre
		
		// met box layout pour que les panneaux imbriqués soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		// initialise les composants
		initComposants();
		ajouterComposants();
		
	}
	
	/**
	 * Initialise tous les composants du panneau de mots en appelant leurs
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {

		trouver = new JLabel("Trouver");
		remplacer = new JLabel("Remplacer");
		saisieTrouver = new JComboBox<>();
		saisieTrouver.setEditable(true);
		saisieTrouver.setMaximumSize(new Dimension(300, 50));
		saisieRemplacer = new JComboBox<>();
		saisieRemplacer.setEditable(true);
		saisieRemplacer.setMaximumSize(new Dimension(300, 50));

	}
	

	

	
	/**
	 * On ajoute les étiquettes et les saisies à leur panneau respectif et
	 * on ajoute les panneaux créés au panneau de mots.
	 */
	private void ajouterComposants() {
		

		add(trouver);
		add(saisieTrouver);
		

		add(remplacer);
		add(saisieRemplacer);
	}


	public String getMotRecherche() {

		return (String) saisieTrouver.getSelectedItem();
	}

	public void ajouterSaisie(JComboBox<String> saisie) {

		if (saisie.getSelectedItem() != null) {

			String chaine = saisie.getSelectedItem().toString();

			for (int i = 0; i < saisie.getItemCount()- 1; i++) {

				if (Objects.equals(chaine, saisie.getItemAt(i))) {

					saisie.removeItemAt(i);
				}
			}

			saisie.addItem(chaine);
		}
	}

	public void ajouterSaisies() {

		ajouterSaisie(saisieTrouver);
		ajouterSaisie(saisieRemplacer);
	}
}
