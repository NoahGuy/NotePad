package Recherche;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanneauFermer extends JPanel{
	
	private JButton btnFermer;
	
	public PanneauFermer() {
				
		initComposants();
		
	}
	
	private void initComposants() {
	
		creerBtn();
		
		initialiserComposants();
		
		ajouterComposants();
	}
	
	private void creerBtn() {
		
		btnFermer = nouveauBtn(btnFermer);
	}
	
	private JButton nouveauBtn(JButton btn) {
		
		btn = new JButton();
		
		return btn;
	}
	
	private void initialiserComposants() {
		
		btnFermer.setText("Fermer");
	}
	
	private void ajouterComposants() {
		
		add(btnFermer);
	}
}
