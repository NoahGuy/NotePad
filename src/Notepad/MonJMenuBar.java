package Notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MonJMenuBar extends JMenuBar implements ActionListener {
    private JMenu menu;
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
    private CadreGUI cadre;
    private JLabel statusBar;

    public MonJMenuBar(CadreGUI cadre) {
        this.cadre = cadre;
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
        this.menu = new JMenu("Fichier");
        this.add(this.menu);
        this.menuModifier = new JMenu("Modifier");
        this.add(this.menuModifier);
        this.menuAfficher = new JMenu("Affichage");
        this.add(this.menuAfficher);
    }

    private void initcomposant() {
        this.itemNouveau = new JMenuItem("Nouveau");
        this.itemNouveau.addActionListener(this);
        this.itemNouveau.setActionCommand("Nouveau");
        this.menu.add(this.itemNouveau);
        this.itemOuvrir = new JMenuItem("Ouvrir");
        this.itemOuvrir.addActionListener(this);
        this.itemOuvrir.setActionCommand("Ouvrir");
        this.menu.add(this.itemOuvrir);
        this.itemSauvegarder = new JMenuItem("Sauvegarder");
        this.itemSauvegarder.addActionListener(this);
        this.itemSauvegarder.setActionCommand("Sauvegarder");
        this.menu.add(this.itemSauvegarder);
        this.itemSaveAs = new JMenuItem("Sauvegarder en tant que");
        this.itemSaveAs.addActionListener(this);
        this.itemSaveAs.setActionCommand("Sauvegarder en tant que");
        this.menu.add(this.itemSaveAs);
        this.itemQuitter = new JMenuItem("Quitter");
        this.itemQuitter.addActionListener(this);
        this.itemQuitter.setActionCommand("Quitter");
        this.menu.add(this.itemQuitter);
        this.itemBarreEtat = new JMenuItem("Barre état");
        this.itemBarreEtat.addActionListener(this);
        this.itemBarreEtat.setActionCommand("Barre état");
        this.menuAfficher.add(this.itemBarreEtat);
        this.itemZoomIn = new JMenuItem("Zoom in");
        this.itemZoomIn.addActionListener(this);
        this.itemZoomIn.setActionCommand("Zoom in");
        this.menuAfficher.add(this.itemZoomIn);
        this.itemZoomOut = new JMenuItem("Zoom out");
        this.itemZoomOut.addActionListener(this);
        this.itemZoomOut.setActionCommand("Zoom out");
        this.menuAfficher.add(this.itemZoomOut);
    }

    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Nouveau":
                this.cadre.getFonctions().nouveauFichier();
                break;
            case "Ouvrir":
                this.cadre.getFonctions().ouvrirFichier();
                break;
            case "Sauvegarder":
                this.cadre.getFonctions().save();
                break;
            case "Sauvegarder en tant que":
                this.cadre.getFonctions().saveAs();
                break;
            case "Quitter":
                cadre.getFonctions().quitter();
                break;
            case "Zoom in":
                this.cadre.getFonctions().zoomIn();
                this.menuAfficher.doClick();
                break;
            case "Zoom out":
                this.cadre.getFonctions().zoomOut();
                this.menuAfficher.doClick();
                break;
            case "Barre état":
                this.cadre.getFonctions().enleverRemettreBarreEtat();
        }

    }
}
