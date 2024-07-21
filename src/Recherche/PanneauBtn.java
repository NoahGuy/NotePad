package Recherche;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanneauBtn extends JPanel{
	
	private JButton btnTrouver;
	private JButton btnSelectionnerTout;
	private JButton btnRemplacerTrouver;
	private JButton btnRemplacer;
	private JButton btnRemplacerTout;
	
	public PanneauBtn() {
		
		// met box layout pour que les panneaux imbriques soient les uns par
		// dessus les autres
		setLayout(new FlowLayout());
				
		initComposants();
		
	}
	
	private void initComposants() {
	
		creerBtn();
		
		initialiserComposants();
		
		ajouterComposants();
	}
	
	private void creerBtn() {
		
		btnTrouver = nouveauBtn(btnTrouver);
		btnSelectionnerTout = nouveauBtn(btnSelectionnerTout);
		btnRemplacerTrouver = nouveauBtn(btnRemplacerTrouver);
		btnRemplacer = nouveauBtn(btnRemplacer);
		btnRemplacerTout = nouveauBtn(btnRemplacerTout);
	}
	
	private JButton nouveauBtn(JButton btn) {
		
		btn = new JButton();

		//btn.setPreferredSize(new Dimension(120, 20));
		
		return btn;
	}
	
	private void initialiserComposants() {
		
		btnTrouver.setText("Trouver");
		
		btnSelectionnerTout.setText("Sélectionner tout");
		
		btnRemplacerTrouver.setText("Remplacer/Trouver");
		
		btnRemplacer.setText("Remplacer");
		
		btnRemplacerTout.setText("Remplacer tout");
	}
	
	private void ajouterComposants() {
		
		add(btnTrouver);
		add(btnSelectionnerTout);
		add(btnRemplacerTrouver);
		add(btnRemplacer);
		add(btnRemplacerTout);
	}
}
