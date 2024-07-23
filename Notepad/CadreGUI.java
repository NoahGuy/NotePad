package Notepad;

import javax.swing.*;
import java.awt.*;

public class CadreGUI extends JFrame {
    private PanneauPrincipal panneauPrincipal;
    private MonJMenuBar monMenuBar;
    private Fonctions fonctions;
    private Shortcuts keyListener;


    public CadreGUI() {
        fonctions = new Fonctions(this);
        monMenuBar = new MonJMenuBar(this);
        panneauPrincipal = new PanneauPrincipal();
        keyListener = new Shortcuts(this);

        setTitle(fonctions.nomFichier);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(monMenuBar);
        add(panneauPrincipal, BorderLayout.CENTER);



        setFocusable(true);
        setVisible(true);
    }

    public PanneauPrincipal getPanneauPrincipal() {
        return panneauPrincipal;
    }

    public Fonctions getFonctions() {
        return fonctions;
    }
}
