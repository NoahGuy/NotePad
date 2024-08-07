package Notepad;

import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.KeyEvent.*;

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

/**
 * JTextPane modifié afin d'implementer le zoom avec ctrl = , ctrl - et ctrl scroll
 * a fonctions comme attribut pour effectuer les fonctions de zoom, doit etre instancie dans cette classe
 * car fonctions a besoin du textPane et textPane a besoin des fonctions
 *
 * barreEtat est aussi instanciee dans cette classe car fonctions a besoin de la barre etat
 */
public class CustomJTextPane extends JTextPane {

    protected Fonctions fonctions;
    protected StyledDocument doc;

    public CustomJTextPane(CadreGUI cadre, PanneauPrincipal panneauPrincipal) {

        super();

        BarreEtat barreEtat = new BarreEtat(this);
        panneauPrincipal.add(barreEtat, "South");

        fonctions = new Fonctions(cadre, this, barreEtat);
        initComposants(fonctions);



//        JScrollPane scrollPane = new JScrollPane(this);
//        panneauPrincipal.add(scrollPane, BorderLayout.CENTER);
//
//        // Enable mouse wheel scrolling in the JScrollPane
//        scrollPane.setWheelScrollingEnabled(true);

        bindZoom();
    }

    /**
     * initialize la police du JTextPane afin que chaque lettre prenne le même nombre de pixels
     * afin d'avoir ligne et colonne dans la barre état.
     */


    private void initComposants(Fonctions fonctions) {

        this.fonctions = fonctions;
    }



    private void bindZoom() {

        bindCtrlEqual();
        bindCtrlMinus();
        bindCtrlScroll();
    }

    private void bindCtrlScroll() {

        this.addMouseWheelListener(new MouseWheelListener() {

            public void mouseWheelMoved(MouseWheelEvent e) {

                if (e.isControlDown()) {

                    if (e.getWheelRotation() < 0) {

                        fonctions.zoomIn();
                    }

                    else {

                        fonctions.zoomOut();
                    }
                }
                else {

                    // scroll normalement quand ctrl n'est pas appuye
                    JScrollPane scrollPane = (JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class,
                                            CustomJTextPane.this);

                    if (scrollPane != null) {

                        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
                        JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();

                        int unitsToScroll = e.getUnitsToScroll();
                        int direction = e.getWheelRotation();

                        //scroll horizontalement si shift est appuye
                        if (e.isShiftDown()) {

                            horizontalScrollBar.setValue(horizontalScrollBar.getValue() +
                                    (unitsToScroll * horizontalScrollBar.getUnitIncrement(direction)));
                        }
                        //scroll verticalement sinon
                        else {

                            verticalScrollBar.setValue(verticalScrollBar.getValue() +
                                    (unitsToScroll * verticalScrollBar.getUnitIncrement(direction)));
                        }
                    }
                }

            }
        });
    }



    private void bindCtrlMinus() {

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_MINUS, CTRL_DOWN_MASK), "zoomout");
        this.getActionMap().put("zoomout", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                fonctions.zoomOut();
            }
        });
    }

    private void bindCtrlEqual() {
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_EQUALS, CTRL_DOWN_MASK), "zoomin");
        this.getActionMap().put("zoomin", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                fonctions.zoomIn();
            }
        });
    }
}

