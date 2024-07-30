package Recherche;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer </p>
 *
 * <p>Classe	: GUI.PanneauFermer</p>
 *
 * <p>Desc		: Panneau (JPanel) dans lequel se trouve notre bouton "fermer".</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 14/07/24
 */
public class PanneauFermer extends JPanel{
	
	//Le bouton qui nous permet de fermer la fenêtre de recherche et de
	//remplacement
	private JButton btnFermer;
	
	//Le panneau principal dans lequel ce panneau est ajouté
	private JPanel panneauPrincipal;

	private Cadre cadre;
	
	public PanneauFermer(JPanel panneauPrincipal, Cadre cadre) {
		
		//assigne le panneauPrincipal recu en parametre
		this.panneauPrincipal = panneauPrincipal;

		this.cadre = cadre;
				
		initComposants();
		
	}
	
	/**
	 * Initialise le composant du panneauFermer en appelant ses
	 * fonctions d'initialisation individuelles.
	 */
	private void initComposants() {
	
		creerBtn();
		
		initialiserComposants();
		
		ajouterComposants();
		
		fermer(btnFermer);
	}
	
	/**
	 * Créé le bouton
	 */
	private void creerBtn() {
		
		btnFermer = nouveauBtn(btnFermer);
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
	 * On ajoute un titre au bouton.
	 */
	private void initialiserComposants() {
		
		btnFermer.setText("Fermer");
	}
	
	/**
	 * On ajoute le bouton.
	 */
	private void ajouterComposants() {
		
		add(btnFermer);
	}
	
	/**
	 * On active le bouton "fermer" avec un écouteur qui fait que la fenêtre 
	 * de recherche et de remplacement se ferme lorsque le bouton "fermer"
	 * est cliqué.
	 */
	private void fermer(JButton btn) {
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				cadre.dispose();
			}
		});
	}
}
