package Notepad;

import javax.swing.JFrame;

public class CadreGUI extends JFrame {
    private PanneauPrincipal panneauPrincipal = new PanneauPrincipal();
    private MonJMenuBar monMenuBar = new MonJMenuBar(this);
    private Fonctions fonctions = new Fonctions(this);
    private Shortcuts keyListener = new Shortcuts(this);

    public CadreGUI() {
        this.setTitle(this.fonctions.nomFichier);
        this.setSize(800, 600);
        this.setDefaultCloseOperation(3);
        this.setJMenuBar(this.monMenuBar);
        this.add(this.panneauPrincipal, "Center");
        this.setFocusable(true);
        this.setVisible(true);
    }

    public PanneauPrincipal getPanneauPrincipal() {
        return this.panneauPrincipal;
    }

    public Fonctions getFonctions() {
        return this.fonctions;
    }
}
