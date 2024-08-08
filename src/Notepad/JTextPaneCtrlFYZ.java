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
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer un éditeur de texte simple avec les 
 *    options ‘défaire’ (undo), via Ctrl+z, et ‘refaire’ (redo), via ‘Ctrl+y’. L'éditeur
 *    a aussi une fonctionnalité de recherche et de remplacement de mots qu'on accède via
 *    la commande ‘Ctrl+f’.</p>
 *
 * <p>Classe	: JTextPaneCtrlFYZ </p>
 *
 * <p>Desc		: hérite de CustomJTextPane et ajoute des methodes pour ajouter les fonctions de recherche à ctrl F,
 *  *              ainsi que undo et redo à ctrl z et y</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 07/08/24
 */
public class JTextPaneCtrlFYZ extends CustomJTextPane {

    private UndoManager undoManager; //gère les modifications au document qui peuvent être undo/redo
    private Document doc; //document du textpane auquel les modifications sont effectuées


    /**
     * Constructeur pour JTextPaneCtrlFYZ
     *
     * @param cadreGUI  cadre qu'on passe au constructeur de CustomJTextPane
     * @param panneauPrincipal  panneau qu'on passe au constructeur de CustomJTextPane
     */
    public JTextPaneCtrlFYZ(CadreGUI cadreGUI, PanneauPrincipal panneauPrincipal) {

        super(cadreGUI, panneauPrincipal);

        initComposants();

        bindUndoRedo();
        bindCtrlF();
    }

    /**
     * initialise les attributs de la classe
     */
    private void initComposants() {


        this.undoManager = new UndoManager();
        this.doc = this.getDocument();
    }

    /**
     * Permet d'executer la ouvrirPanneauRecherche quand on appuie sur ctrl F.
     */
    private void bindCtrlF() {
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(VK_F, CTRL_DOWN_MASK), "Search");

        getActionMap().put("Search", new AbstractAction() {

            public void actionPerformed(ActionEvent e) {
                fonctions.ouvrirPanneauRecherche(fonctions);
            }
        });
    }

    /**
     * Ajoute un ecouteur  de modifications sur le document et appele les methodes qui permettent d'undo/redo
     * ces modifications quand on appuie sur ctrl z et ctrl y
     */
    private void bindUndoRedo() {

        doc.addUndoableEditListener(new UndoableEditListener() {

            public void undoableEditHappened(UndoableEditEvent e) {

                undoManager.addEdit(e.getEdit());
            }
        });

        bindCtrlZ();
        bindCtrlY();
    }


    /**
     * Permet de redo une modification quand on appuie sur ctrl Y.
     */
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

    /**
     * Permet d'undo une modification quand on appuie sur ctrl Z.
     */
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
