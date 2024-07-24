package Recherche;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class PanneauOptions extends JPanel{
	
	private JLabel titre;
	
	private JCheckBox caseSensibleCasse;
	//private JCheckBox caseRechercheGeneralisee;
	//private JCheckBox caseMotComplet;
	//private JCheckBox caseIncremental;
	//private JCheckBox caseExpressionsRegulieres;
	
	public PanneauOptions() {
		
		// met box layout pour que les panneaux imbriques soient les uns par
		// dessus les autres
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new GridLayout(2,1));
		setPreferredSize(new Dimension(300, 1));
				
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
		
		caseSensibleCasse = nouvelleCase(caseSensibleCasse);
		//caseRechercheGeneralisee = nouvelleCase(caseRechercheGeneralisee);
		//caseMotComplet = nouvelleCase(caseMotComplet);
		//caseIncremental = nouvelleCase(caseIncremental);
		//caseExpressionsRegulieres = nouvelleCase(caseExpressionsRegulieres);
	}
	
	private JCheckBox nouvelleCase(JCheckBox caseACocher) {
		
		caseACocher = new JCheckBox();
		
		return caseACocher;
	}
	
	private void initialiserComposants() {
		
		titre.setText("Options");
		
		caseSensibleCasse.setText("Sensible à la casse");
		
		//caseRechercheGeneralisee.setText("Recherche généralisée");
		
		//caseMotComplet.setText("Mot complet");
		
		//caseIncremental.setText("Incrémental");
		
		//caseExpressionsRegulieres.setText("Expressions régulières");
	}
	
	private void ajouterComposants() {
		
		add(titre);
		add(caseSensibleCasse);
		//add(caseRechercheGeneralisee);
		//add(caseMotComplet);
		//add(caseIncremental);
		//add(caseExpressionsRegulieres);
	}
}
