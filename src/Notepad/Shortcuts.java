package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

public class Shortcuts {
    private CadreGUI gui;
    private JTextPane textArea;
    private UndoManager undoManager;
    private Document doc;
    private Fonctions fonctions;

    public Shortcuts(CadreGUI gui) {
        this.initComposants(gui);
        this.bindZoom();
        this.bindUndoRedo();
        this.bindCtrlF();
    }

    private void initComposants(CadreGUI gui) {
        this.gui = gui;
        this.textArea = gui.getPanneauPrincipal().getTextArea();
        this.undoManager = new UndoManager();
        this.doc = this.textArea.getDocument();
    }

    private void bindCtrlF() {
        this.textArea.getInputMap(2).put(KeyStroke.getKeyStroke(70, 128), "Search");
        this.textArea.getActionMap().put("Search", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Shortcuts.this.gui.getFonctions().ouvrirPanneauRecherche(gui.getFonctions());
            }
        });
    }

    private void bindUndoRedo() {
        this.doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                Shortcuts.this.undoManager.addEdit(e.getEdit());
            }
        });
        this.bindCtrlZ();
        this.bindCtrlY();
    }

    private void bindZoom() {
        this.bindCtrlEqual();
        this.bindCtrlMinus();
        this.bindCtrlScroll();
    }

    private void bindCtrlScroll() {
        this.textArea.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.isControlDown()) {
                    if (e.getWheelRotation() < 0) {
                        Shortcuts.this.gui.getFonctions().zoomIn();
                    } else {
                        Shortcuts.this.gui.getFonctions().zoomOut();
                    }
                }

            }
        });
    }

    private void bindCtrlY() {
        this.textArea.getInputMap(2).put(KeyStroke.getKeyStroke(89, 128), "Redo");
        this.textArea.getActionMap().put("Redo", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (Shortcuts.this.undoManager.canRedo()) {
                    Shortcuts.this.undoManager.redo();
                }

            }
        });
    }

    private void bindCtrlZ() {
        this.textArea.getInputMap(2).put(KeyStroke.getKeyStroke(90, 128), "Undo");
        this.textArea.getActionMap().put("Undo", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (Shortcuts.this.undoManager.canUndo()) {
                    Shortcuts.this.undoManager.undo();
                }

            }
        });
    }

    private void bindCtrlMinus() {
        this.textArea.getInputMap(2).put(KeyStroke.getKeyStroke(45, 128), "zoomout");
        this.textArea.getActionMap().put("zoomout", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Shortcuts.this.gui.getFonctions().zoomOut();
            }
        });
    }

    private void bindCtrlEqual() {
        this.textArea.getInputMap(2).put(KeyStroke.getKeyStroke(61, 128), "zoomin");
        this.textArea.getActionMap().put("zoomin", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                Shortcuts.this.gui.getFonctions().zoomIn();
            }
        });
    }
}
