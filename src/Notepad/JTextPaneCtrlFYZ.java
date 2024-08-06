package Notepad;

import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.KeyEvent.*;

/**
 * herite de CustomJTextPane et ajoute des methodes pour bind les fonctions de recherche à ctrl F, ainsi que
 * undo et redo à ctrl z et y
 */
public class JTextPaneCtrlFYZ extends CustomJTextPane {

    private UndoManager undoManager;
    private Document doc;


    public JTextPaneCtrlFYZ(CadreGUI cadreGUI, PanneauPrincipal panneauPrincipal) {

        super(cadreGUI, panneauPrincipal);
        initComposants();
        bindUndoRedo();
        bindCtrlF();
    }

    private void initComposants() {


        this.undoManager = new UndoManager();
        this.doc = this.getDocument();
    }

    private void bindCtrlF() {
        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_F, CTRL_DOWN_MASK), "Search");

        this.getActionMap().put("Search", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                fonctions.ouvrirPanneauRecherche(fonctions);
            }
        });
    }

    private void bindUndoRedo() {

        doc.addUndoableEditListener(new UndoableEditListener() {

            public void undoableEditHappened(UndoableEditEvent e) {

                undoManager.addEdit(e.getEdit());
            }
        });

        bindCtrlZ();
        bindCtrlY();
    }


    private void bindCtrlY() {

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_Y, CTRL_DOWN_MASK), "Redo");
        this.getActionMap().put("Redo", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                if (undoManager.canRedo()) {

                    undoManager.redo();
                }

            }
        });
    }

    private void bindCtrlZ() {

        this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_Z, CTRL_DOWN_MASK), "Undo");
        this.getActionMap().put("Undo", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                if (undoManager.canUndo()) {

                    undoManager.undo();
                }

            }
        });
    }


}
