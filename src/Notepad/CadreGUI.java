package Notepad;

import javax.swing.*;
import java.awt.*;

public class CadreGUI extends JFrame {
    private PanneauPrincipal panneauPrincipal;
    private MonJMenuBar monMenuBar;
    private Fonctions fichier;
    private Shortcuts keyListener;
    private JLabel statusLabel;

    public CadreGUI() {
        fichier = new Fonctions(this);
        monMenuBar = new MonJMenuBar(this);
        panneauPrincipal = new PanneauPrincipal();
        keyListener = new Shortcuts(this);

        setTitle(fichier.nomFichier);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(monMenuBar);
        add(panneauPrincipal, BorderLayout.CENTER);

        statusLabel = new JLabel(" ");
        add(statusLabel, BorderLayout.SOUTH);

        //addKeyListener(keyListener);
        //panneauPrincipal.getText().addKeyListener(keyListener); // Ajout du KeyListener au JTextArea
        //panneauPrincipal.getText().addMouseWheelListener(keyListener); // Ajout du MouseWheelListener au JTextArea
        setFocusable(true);
        setVisible(true);
    }

    public PanneauPrincipal getPanneauPrincipal() {
        return panneauPrincipal;
    }

    public Fonctions getFichier() {
        return fichier;
    }

    public Fonctions getOuvrir(){
        return fichier;
    }
    public Fonctions getSave(){
        return fichier;
    }
    public Fonctions getSaveAS(){
        return fichier;
    }
    public Fonctions getZoomIn(){
        return fichier;
    }
    public Fonctions getZoomOut(){
        return fichier;
    }


    public void setStatus(String status) {
        statusLabel.setText(status);
    }
}
