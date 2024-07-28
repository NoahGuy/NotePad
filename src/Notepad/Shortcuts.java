package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.KeyEvent.*;
import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class Shortcuts {

    private JTextPane textArea;
    private UndoManager undoManager;
    private Document doc;
    private Fonctions fonctions;

    public Shortcuts(Fonctions fonctions, JTextPane textPane) {

        initComposants(fonctions, textPane);
        bindZoom();
        bindUndoRedo();
        bindCtrlF();
        //TODO: bind ctrls and ctrlshifts
    }

    private void initComposants(Fonctions fonctions, JTextPane textPane) {

        this.fonctions = fonctions;
        this.textArea = textPane;
        this.undoManager = new UndoManager();
        this.doc = this.textArea.getDocument();
    }

    private void bindCtrlF() {
        textArea.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_F, CTRL_DOWN_MASK), "Search");

        textArea.getActionMap().put("Search", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                fonctions.ouvrirPanneauRecherche(fonctions);
            }
        });
    }

    private void bindUndoRedo() {

        doc.addUndoableEditListener(new UndoableEditListener() {

            public void undoableEditHappened(UndoableEditEvent e) {

                Shortcuts.this.undoManager.addEdit(e.getEdit());
            }
        });

        bindCtrlZ();
        bindCtrlY();
    }

    private void bindZoom() {

        bindCtrlEqual();
        bindCtrlMinus();
        bindCtrlScroll();
    }

    private void bindCtrlScroll() {

        textArea.addMouseWheelListener(new MouseWheelListener() {

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

    private void bindCtrlY() {

        textArea.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_Y, CTRL_DOWN_MASK), "Redo");
        textArea.getActionMap().put("Redo", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                if (undoManager.canRedo()) {

                   undoManager.redo();
                }

            }
        });
    }

    private void bindCtrlZ() {

        textArea.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_Z, CTRL_DOWN_MASK), "Undo");
        textArea.getActionMap().put("Undo", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                if (undoManager.canUndo()) {

                    undoManager.undo();
                }

            }
        });
    }

    private void bindCtrlMinus() {

        textArea.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_MINUS, CTRL_DOWN_MASK), "zoomout");
        textArea.getActionMap().put("zoomout", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                fonctions.zoomOut();
            }
        });
    }

    private void bindCtrlEqual() {
        this.textArea.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_EQUALS, CTRL_DOWN_MASK), "zoomin");
        this.textArea.getActionMap().put("zoomin", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {

                fonctions.zoomIn();
            }
        });
    }
}
