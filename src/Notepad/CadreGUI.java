package Notepad;

import javax.swing.*;

public class CadreGUI extends JFrame {
    private PanneauPrincipal panneauPrincipal;
    private Fonctions fonctions;
    private MonJMenuBar monMenuBar;
    private Shortcuts keyListener;

    public CadreGUI() {

        initComposants();

        setTitle(fonctions.nomFichier);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(monMenuBar);
        add(panneauPrincipal, "Center");
        setFocusable(true);
        setVisible(true);
    }

    public void initComposants() {

        panneauPrincipal = new PanneauPrincipal();


        fonctions = new Fonctions(this, panneauPrincipal.getTextPane(), panneauPrincipal.getBarreEtat());
        monMenuBar = new MonJMenuBar(fonctions);
        keyListener = new Shortcuts(fonctions, panneauPrincipal.getTextPane());
    }

}
