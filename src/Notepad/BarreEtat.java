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
 * <p>Classe	:BarreEtat </p>
 *
 * <p>Desc		:Cette classe représente une barre d'état pour l'éditeur de texte.
 *               Elle affiche le zoom, le nombre de caractères dans le document,
 *               ainsi que la ligne et la colonne actuelles du curseur. </p>
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

    /**
     * Constructeur de la classe BarreEtat
     * Initialise les composants et ajoute les écouteurs d'événements
     *
     * @param textPane le composant JTextPane personnalisé
     */

    public BarreEtat(CustomJTextPane textPane) {
        setLayout(new BorderLayout());

        this.textPane = textPane;

        // Initialisation des labels
        zoom = new JLabel("100% ");
        nbCaracteres = new JLabel(String.valueOf(" " + textPane.getText().length()) + " caractères.");
        ligneColonne = new JLabel(" Ligne: 1, Colonne: 1");

        // Ajout des labels au panel
        add(nbCaracteres, "West");
        add(zoom, "East");
        add(ligneColonne, "Center");

        // Ajout d'un écouteur pour les changements dans le document
        textPane.getDocument().addDocumentListener(new DocumentListener() {

            // Méthode appelée lors de l'insertion de texte
            public void insertUpdate(DocumentEvent e) {
                updateNbCaracteres();
            }

            // Méthode appelée lors de la suppression de texte
            public void removeUpdate(DocumentEvent e) {
                updateNbCaracteres();
            }

            // Méthode appelée lors des changements d'attributs de texte (non utilisée ici)
            public void changedUpdate(DocumentEvent e) {
                // pas utilisé
            }
        });

        // Ajout d'un écouteur pour les mouvements du curseur
        textPane.addCaretListener(new CaretListener() {
            // Méthode appelée lors du déplacement du curseur
            public void caretUpdate(CaretEvent e) {
                updateLigneColonne();
            }
        });
    }

    /**
     * Met à jour l'affichage de la ligne et de la colonne en fonction de la position du curseur
     */

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        ligneColonne.setText(" Ligne: " + line + ", Colonne: " + column);
    }

    /**
     * Met à jour l'affichage du nombre de caractères dans le document
     */

    private void updateNbCaracteres() {
        int length = textPane.getDocument().getLength();
        nbCaracteres.setText(" " + length + " caractères");
    }

    /**
     * Retourne le label zoom
     *
     * @return le label zoom
     */
    
    public JLabel getZoom() {
        return zoom;
    }
}
