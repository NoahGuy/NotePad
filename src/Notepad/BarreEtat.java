

package Notepad;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

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

public class BarreEtat extends JPanel {

    private JLabel zoom;
    private JLabel nbCaracteres;
    private JLabel ligneColonne;
    private CustomJTextPane textPane;

    public BarreEtat(CustomJTextPane textPane) {

        setLayout(new BorderLayout());

        this.textPane = textPane;

        zoom = new JLabel("100% ");
        nbCaracteres = new JLabel(String.valueOf(" " + textPane.getText().length()) + " caractères.");
        ligneColonne = new JLabel(" Ligne: 1, Colonne: 1");

        add(nbCaracteres, "West");
        add(zoom, "East");
        add(ligneColonne, "Center");

        textPane.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {

                updateNbCaracteres();
            }

            public void removeUpdate(DocumentEvent e) {

                updateNbCaracteres();
            }

            public void changedUpdate(DocumentEvent e) {

                // pas utilisé
            }
        });


        textPane.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                updateLigneColonne();
            }
        });
    }

    private void updateLigneColonne() {

        int caretPos = textPane.getCaretPosition();
        int line = 1;
        int column = 1;

        try {

            int offset = caretPos;
            Element root = textPane.getDocument().getDefaultRootElement();
            line = root.getElementIndex(offset) + 1;

            int start = root.getElement(line - 1).getStartOffset();
            column = caretPos - start + 1;
        }
        catch (Exception e) {

            e.printStackTrace();
        }

        ligneColonne.setText(" Ligne: " + line + ", Colonne: " + column);
    }



    private void updateNbCaracteres() {

        int length = textPane.getDocument().getLength();
        nbCaracteres.setText(" " + length + " caractères");
    }

    public JLabel getZoom() {

        return zoom;
    }
}
