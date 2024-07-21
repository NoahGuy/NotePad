package Recherche;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;

public class PanneauMots extends JPanel{
	
	private JLabel trouver;
	
	private JLabel remplacer;
	
	private JComboBox saisieTrouver;
	
	private JComboBox saisieRemplacer;
	
	private JPanel panneauTrouver;
	private JPanel panneauRemplacer;
	
	public PanneauMots() {
		
		// met box layout pour que les panneaux imbriques soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		initComposants();
		
	}
	
	private void initComposants() {
		
		creerPanneau();
		
		creerEtiq();
		creerSaisie();
		
		ajouterComposants();
		
		initialiserComposants();
		
	}
	
	private void creerPanneau() {
		
		panneauTrouver = nouveauPanneau(panneauTrouver);
		panneauRemplacer = nouveauPanneau(panneauRemplacer);
	}
	
	private JPanel nouveauPanneau(JPanel panneau) {
		
		panneau = new JPanel();
		
		return panneau;
	}
	
	private void creerEtiq() {
		
		trouver = nouvelleEtiq(trouver);
		remplacer = nouvelleEtiq(remplacer);
	}
	
	private JLabel nouvelleEtiq(JLabel etiq) {
		
		etiq = new JLabel();
		
		return etiq;
	}
	
	private void creerSaisie() {
		
		saisieTrouver = nouvelleSaisie(saisieTrouver);
		saisieRemplacer = nouvelleSaisie(saisieRemplacer);
	}
	
	private JComboBox nouvelleSaisie(JComboBox saisie) {
		
		saisie = new JComboBox();
		
		saisie.setPreferredSize(new Dimension(250, 40));
		
		saisie.setEditable(true);
		
		return saisie;
	}
	
	private void ajouterComposants() {
		
		add(panneauTrouver);
		panneauTrouver.add(trouver);
		panneauTrouver.add(saisieTrouver);
		
		add(panneauRemplacer);
		panneauRemplacer.add(remplacer);
		panneauRemplacer.add(saisieRemplacer);
	}
	
	private void initialiserComposants() {
		
		trouver.setText("Trouver:");
		
		remplacer.setText("Remplacer avec:");
	}
}
