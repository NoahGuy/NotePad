package Recherche;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanneauPortee extends JPanel{
	
	private JLabel titre;
	
	private JCheckBox caseTous;
	private JCheckBox caseLignesSelectionnees;
	
	public PanneauPortee() {
		
		// met box layout pour que les panneaux imbriques soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
				
		initComposants();
		
	}
	
	private void initComposants() {
		
		creerEtiq();
		creerCase();
		
		initialiserComposants();
		
		ajouterComposants();
	}
	
	private void creerEtiq() {
		
		titre = nouvelleEtiq(titre);
	}
	
	private JLabel nouvelleEtiq(JLabel etiq) {
		
		etiq = new JLabel();
		
		return etiq;
	}
	
	private void creerCase() {
		
		caseTous = nouvelleCase(caseTous);
		caseLignesSelectionnees = nouvelleCase(caseLignesSelectionnees);
	}
	
	private JCheckBox nouvelleCase(JCheckBox caseACocher) {
		
		caseACocher = new JCheckBox();
		
		return caseACocher;
	}
	
	private void initialiserComposants() {
		
		titre.setText("Portée");
		
		caseTous.setText("Tous");
		
		caseLignesSelectionnees.setText("Lignes sélectionnées");
	}
	
	private void ajouterComposants() {
		
		add(titre);
		add(caseTous);
		add(caseLignesSelectionnees);
	}
}
