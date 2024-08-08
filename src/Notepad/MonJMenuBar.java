package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Travail Pratique 2 INF111
 *
 * <p>L'objectif de ce travail pratique est de créer un éditeur de texte simple avec les 
 *    options ‘défaire’ (undo), via Ctrl+z, et ‘refaire’ (redo), via ‘Ctrl+y’. L'éditeur
 *    a aussi une fonctionnalité de recherche et de remplacement de mots qu'on accède via
 *    la commande ‘Ctrl+f’.</p>
 *
 * <p>Classe	: MonJMenuBar</p>
 *
 * <p>Desc		: Cette classe crée la barre de menu pour l'éditeur de texte avec des options
 *                de fichier et d'affichage, et gère les actions associées aux éléments de menu.</p>
 *
 * @author Josue Jesus Aliaga Guillen, Noah Boivin, Simon Dion, Souhayl Farsane
 *
 * @version 07/08/24
 */
public class MonJMenuBar extends JMenuBar implements ActionListener {

    //Les options qui seront dans le menu
    private JMenu menuFichier;
    private JMenu menuAfficher;

    //Les items presents dans les options
    private JMenuItem itemNouveau;
    private JMenuItem itemOuvrir;
    private JMenuItem itemSauvegarder;
    private JMenuItem itemSaveAs;
    private JMenuItem itemQuitter;
    private JMenuItem itemBarreEtat;
    private JMenuItem itemZoomIn;
    private JMenuItem itemZoomOut;


    private Fonctions fonctions;

    // Constructeur qui initialise la barre de menu avec les fonctions données
    public MonJMenuBar(Fonctions fonctions) {
        this.fonctions = fonctions;
        this.creerBarMenu();
        this.creerFichierMenu();
    }

    // Crée la barre de menu
    private void creerBarMenu() {
        this.ajouterMenu();
    }

    // Crée les éléments de menu de fichier
    private void creerFichierMenu() {
        this.initcomposant();
    }

    // Ajoute les menus Fichier et Affichage à la barre de menu
    private void ajouterMenu() {
        menuFichier = new JMenu("Fichier");
        add(this.menuFichier);

        menuAfficher = new JMenu("Affichage");
        add(this.menuAfficher);
    }

    // Initialise les composants de menu et les ajoute aux menus appropriés
    private void initcomposant() {

        itemNouveau =   nouveauMenuItem("Nouveau", menuFichier);
        itemOuvrir  =   nouveauMenuItem("Ouvrir", menuFichier);

        itemSauvegarder = nouveauMenuItem("Sauvegarder",       menuFichier);
        itemSaveAs      = nouveauMenuItem("Sauvegarder sous",  menuFichier);
        itemQuitter     = nouveauMenuItem("Quitter",           menuFichier);

        itemBarreEtat = nouveauMenuItem("Barre état", menuAfficher);
        itemZoomIn    = nouveauMenuItem("Zoom in",    menuAfficher);
        itemZoomOut   = nouveauMenuItem("Zoom out",   menuAfficher);
    }

    // Crée un nouvel élément de menu avec le nom donné et l'ajoute au menu spécifié
    public JMenuItem nouveauMenuItem(String nom, JMenu menu) {

        JMenuItem item = new JMenuItem(nom);

        item.addActionListener(this);
        item.setActionCommand(nom);

        menu.add(item);
        return item;
    }

    // Gère les actions effectuées sur les éléments de menu
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Nouveau":
                fonctions.nouveauFichier();
                break;
            case "Ouvrir":
                fonctions.ouvrirFichier();
                break;
            case "Sauvegarder":
                fonctions.save();
                break;
            case "Sauvegarder sous":
                fonctions.saveAs();
                break;
            case "Quitter":
                fonctions.quitter();
                break;
            case "Zoom in":
                fonctions.zoomIn();
                this.menuAfficher.doClick();
                break;
            case "Zoom out":
                fonctions.zoomOut();
                this.menuAfficher.doClick();
                break;
            case "Barre état":
                fonctions.enleverRemettreBarreEtat();
                break;
        }
    }
}
