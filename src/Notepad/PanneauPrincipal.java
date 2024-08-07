package Notepad;

import java.awt.BorderLayout;
import javax.swing.*;

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
 * <p>Classe	: </p>
 *
 * <p>Desc		: </p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 07/08/24
 */

public class PanneauPrincipal extends JPanel {
    private JTextPaneCtrlFYZ textPane;
    private JScrollPane scroll;


    public PanneauPrincipal(CadreGUI cadreGUI) {

        initComposants();
    }

    private void initComposants() {

        setLayout(new BorderLayout());



       // SimpleAttributeSet.SetFontStyle(..., "monospaced");
    }

}
