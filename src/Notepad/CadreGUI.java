package Notepad;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer un éditeur de texte simple avec les 
 *    options ‘défaire’ (undo), via Ctrl+z, et ‘refaire’ (redo), via ‘Ctrl+y’. L'éditeur
 *    a aussi une fonctionnalité de recherche et de remplacement de mots qu'on accède via
 *    la commande ‘Ctrl+f’.</p>
 *
 * <p>Classe	: CadreGUI</p>
 *
 * <p>Desc		: Cette classe représente la fenêtre principale de l'application
 *                d'édition de texte.</p>
 *
 * @version 07/08/24
 */

public class CadreGUI extends JFrame {

    private PanneauPrincipal panneauPrincipal;
    private Fonctions fonctions;
    private MonJMenuBar monMenuBar;
    private JTextPaneCtrlFYZ textPane;
    private JScrollPane scroll;

    // Constructeur de la fenêtre principale
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

    // Méthode pour initialiser les composants de la fenêtre
    public void initComposants() {

        panneauPrincipal = new PanneauPrincipal(this);

        textPane = new JTextPaneCtrlFYZ(this, panneauPrincipal);

        fonctions = textPane.fonctions;

        monMenuBar = new MonJMenuBar(fonctions);

        panneauPrincipal.add(textPane);

        scroll = new JScrollPane(textPane, VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        panneauPrincipal.add(scroll, "Center");
    }
}
