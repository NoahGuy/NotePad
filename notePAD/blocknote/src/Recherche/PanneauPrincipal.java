package Recherche;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.*;

public class PanneauPrincipal extends JPanel{
	
	private JPanel panneauMots;
	
	private JPanel panneauDirection;
	
	private JPanel panneauOptions;
	
	private JPanel panneauBtn;
	
	private JPanel panneauFermer;
	
	public PanneauPrincipal() {
		
		// met box layout pour que les panneaux imbriques soient les uns par
		// dessus les autres
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		panneauMots = new PanneauMots();
		panneauDirection = new PanneauDirection();
		panneauOptions = new PanneauOptions();
		panneauBtn = new PanneauBtn();
		panneauFermer = new PanneauFermer();

		
		add(panneauMots);
		add(panneauDirection);
		add(panneauOptions);
		add(panneauBtn);
		add(panneauFermer);
	}
}
