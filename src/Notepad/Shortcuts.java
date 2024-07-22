package Notepad;

import Recherche.Cadre;

import javax.swing.*;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;

import static Notepad.Fonctions.*;

public class Shortcuts  {
    private CadreGUI gui;
    private JTextArea textArea;
    private UndoManager undoManager;
    private Document doc;
    private Fonctions fonctions;


    public Shortcuts(CadreGUI gui) {

        initComposants(gui);

        bindZoom();
        bindUndoRedo();
        bindCtrlF();
    }


    private void initComposants(CadreGUI gui) {

        this.gui = gui;
        textArea = gui.getPanneauPrincipal().getText();
        undoManager = new UndoManager();
        doc = textArea.getDocument();
    }


    private void bindCtrlF() {

        textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK), "Search");
        textArea.getActionMap().put("Search", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gui.getFonctions().rechercher();


            }
        });
    }



    private void bindUndoRedo() {

        doc.addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });
        //undo et redo
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
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (e.isControlDown()) {
                    if (e.getWheelRotation() < 0) {

                        gui.getFonctions().zoomIn();
                    } else {

                        gui.getFonctions().zoomOut();
                    }
                }
            }
        });
    }

    private void bindCtrlY() {

        textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK), "Redo");
        textArea.getActionMap().put("Redo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            }
        });
    }

    private void bindCtrlZ() {

        textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK), "Undo");
        textArea.getActionMap().put("Undo", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            }
        });
    }

    private void bindCtrlMinus() {

        textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK),
                "zoomout");
        textArea.getActionMap().put("zoomout", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gui.getFonctions().zoomOut();
            }
        });
    }

    private void bindCtrlEqual() {

        textArea.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS, KeyEvent.CTRL_DOWN_MASK),
                "zoomin");
        textArea.getActionMap().put("zoomin", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gui.getFonctions().zoomIn();
            }
        });
    }

}
