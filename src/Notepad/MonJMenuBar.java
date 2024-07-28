package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MonJMenuBar extends JMenuBar implements ActionListener {
    private JMenu menuFichier;
    private JMenu menuModifier;
    private JMenu menuAfficher;
    private JMenuItem itemNouveau;
    private JMenuItem itemOuvrir;
    private JMenuItem itemSauvegarder;
    private JMenuItem itemSaveAs;
    private JMenuItem itemQuitter;
    private JMenuItem itemBarreEtat;
    private JMenuItem itemZoomIn;
    private JMenuItem itemZoomOut;

    private Fonctions fonctions;

    public MonJMenuBar(Fonctions fonctions) {

        this.fonctions = fonctions;
        this.creerBarMenu();
        this.creerFichierMenu();
    }

    private void creerBarMenu() {
        this.ajouterMenu();
    }

    private void creerFichierMenu() {
        this.initcomposant();
    }

    private void ajouterMenu() {

        menuFichier = new JMenu("Fichier");
        add(this.menuFichier);
        menuModifier = new JMenu("Modifier");
        add(this.menuModifier);
        menuAfficher = new JMenu("Affichage");
        add(this.menuAfficher);
    }

    private void initcomposant() {

        itemNouveau = nouveauMenuItem("Nouveau", menuFichier);

        itemOuvrir = nouveauMenuItem("Ouvrir", menuFichier);

        itemSauvegarder = nouveauMenuItem("Sauvegarder", menuFichier);

        itemSaveAs = nouveauMenuItem("Sauvegarder sous", menuFichier);

        itemQuitter = nouveauMenuItem("Quitter", menuFichier);

        itemBarreEtat = nouveauMenuItem("Barre état", menuAfficher);

        itemZoomIn = nouveauMenuItem("Zoom in", menuAfficher);

        itemZoomOut = nouveauMenuItem("Zoom out", menuAfficher);
    }

    public JMenuItem nouveauMenuItem(String nom, JMenu menu) {

        JMenuItem item = new JMenuItem(nom);
        item.addActionListener(this);
        item.setActionCommand(nom);
        menu.add(item);
        return item;
    }

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


            case "Sauvegarder en tant que":

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
        }

    }
}
