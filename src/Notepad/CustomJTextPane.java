package Notepad;

import javax.swing.*;
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
 * <p>Classe	: CustomJTextPane </p>
 *
 * <p>Desc		: JTextPane modifié afin d'implementer le zoom avec ctrl = , ctrl - et ctrl scroll
 *  * ainsi que sauvegarder avec ctrl S </p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 07/08/24
 */
public class CustomJTextPane extends JTextPane {

    protected Fonctions fonctions; //fonctions pour effectuer zoom in/out et sauvegarder

    /**
     * Constructeur pour CustomJTextPane
     *
     * @param cadre  cadre qu'on passe au constructeur de fonctions
     * @param panneauPrincipal  panneau dans lequel on ajoute le CustomJTextPane
     */
    public CustomJTextPane(CadreGUI cadre, PanneauPrincipal panneauPrincipal) {

        super();

        //on instancie la barre etat ici, car elle a besoin du JTextPane pour le nombre de caractères et parce que
        //fonctions a besoin de la barre etat afin d'ajouter/enlever la barre etat
        BarreEtat barreEtat = new BarreEtat(this);
        panneauPrincipal.add(barreEtat, "South");

        //on instancie les fonctions dans cette classe car fonctions a besoin du text pane et le text pane
        //a besoin des fonctions
        fonctions = new Fonctions(cadre, this, barreEtat);


        bindCtrlS();
        bindZoom();
    }

    /**
     * Permet d'executer la fonction save quand on appuie ctrl S.
     */
    private void bindCtrlS() {

        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_S, CTRL_DOWN_MASK), "Save");

        getActionMap().put("Save", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                fonctions.save();
            }
        });
    }

    /**
     * appele les methodes qui permettent d'ajouter les touches pour les fonctions zoomIn et zoomOut.
     */
    private void bindZoom() {

        bindCtrlEqual();
        bindCtrlMinus();
        bindCtrlScroll();
    }

    /**
     * permet de zoomIn et zoomOut quand on appuie sur ctrl et qu'on roule la roue de la souris.
     * permet aussi de scroll dans le textpane avec la roue de la souris quand on appuie pas sur ctrl
     */
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



    /**
     * permet de zoomOut quand on appuie sur ctrl -.
     */
    private void bindCtrlMinus() {

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_MINUS, CTRL_DOWN_MASK), "zoomout");
        this.getActionMap().put("zoomout", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                fonctions.zoomOut();
            }
        });
    }

    /**
     * permet de zoomIn quand on appuie sur ctrl =.
     */
    private void bindCtrlEqual() {
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_EQUALS, CTRL_DOWN_MASK), "zoomin");
        this.getActionMap().put("zoomin", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                fonctions.zoomIn();
            }
        });
    }
}

