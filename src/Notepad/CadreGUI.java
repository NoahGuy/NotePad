package Notepad;

import javax.swing.*;
import java.awt.*;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;

public class CadreGUI extends JFrame {
    private PanneauPrincipal panneauPrincipal;
    private Fonctions fonctions;
    private MonJMenuBar monMenuBar;
    private JTextPaneCtrlFYZ textPane;
    private JScrollPane scroll;

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
