package Notepad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.KeyEvent.*;

/**
 * JTextPane modifié afin d'implementer le zoom avec ctrl = , ctrl - et ctrl scroll
 * a fonctions comme attribut pour effectuer les fonctions de zoom, doit etre instancie dans cette classe
 * car fonctions a besoin du textPane et textPane a besoin des fonctions
 *
 * barreEtat est aussi instanciee dans cette classe car fonctions a besoin de la barre etat
 */
public class CustomJTextPane extends JTextPane {

    protected Fonctions fonctions;


    public CustomJTextPane(CadreGUI cadre, PanneauPrincipal panneauPrincipal) {

        BarreEtat barreEtat = new BarreEtat(this);
        panneauPrincipal.add(barreEtat, "South");

        fonctions = new Fonctions(cadre, this, barreEtat);
        initComposants(fonctions);
        bindZoom();
    }

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

