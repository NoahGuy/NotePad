package Recherche;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;

public class PanneauDirection extends JPanel{
	
	private JLabel titre;
	
	private JCheckBox caseAvant;
	private JCheckBox caseArriere;
	
	public PanneauDirection() {
		
		// met box layout pour que les panneaux imbriques soient les uns par
		// dessus les autres
		//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		//setLayout(new FlowLayout());
		setLayout(new GridLayout(3,1));
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
		//titre.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	private JLabel nouvelleEtiq(JLabel etiq) {
		
		etiq = new JLabel();
		
		return etiq;
	}
	
	private void creerCase() {
		
		caseAvant = nouvelleCase(caseAvant);
		caseArriere = nouvelleCase(caseArriere);

		//caseArriere.setAlignmentX(Component.LEFT_ALIGNMENT);
		//caseAvant.setAlignmentX(Component.LEFT_ALIGNMENT);
	}
	
	private JCheckBox nouvelleCase(JCheckBox caseACocher) {
		
		caseACocher = new JCheckBox();
		
		return caseACocher;
	}
	
	private void initialiserComposants() {
		
		titre.setText("Direction");
		
		caseAvant.setText("Avant");
		
		caseArriere.setText("Arrière");
	}
	
	private void ajouterComposants() {
		
		add(titre);
		add(caseAvant);
		add(caseArriere);
	}
}
