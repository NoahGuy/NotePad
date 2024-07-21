package Notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        creerBarMenu();
        creerFichierMenu();

    }

    private void creerBarMenu() {
        ajouterMenu();
    }

    private void creerFichierMenu() {
        initcomposant();
    }

    private void ajouterMenu() {
        menu = new JMenu("Fichier");
        add(menu);

        menuModifier = new JMenu("Modifier");
        add(menuModifier);

        menuAfficher = new JMenu("Affichage");
        add(menuAfficher);
    }

    private void initcomposant() {
        itemNouveau = new JMenuItem("Nouveau");
        itemNouveau.addActionListener(this);
        itemNouveau.setActionCommand("Nouveau");
        menu.add(itemNouveau);

        itemOuvrir = new JMenuItem("Ouvrir");
        itemOuvrir.addActionListener(this);
        itemOuvrir.setActionCommand("Ouvrir");
        menu.add(itemOuvrir);

        itemSauvegarder = new JMenuItem("Sauvegarder");
        itemSauvegarder.addActionListener(this);
        itemSauvegarder.setActionCommand("Sauvegarder");
        menu.add(itemSauvegarder);

        itemSaveAs = new JMenuItem("Sauvegarder en tant que");
        itemSaveAs.addActionListener(this);
        itemSaveAs.setActionCommand("Sauvegarder en tant que"); // Correspond à la vérification dans actionPerformed
        menu.add(itemSaveAs);

        itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(this);
        itemQuitter.setActionCommand("Quitter");
        menu.add(itemQuitter);

        itemBarreEtat = new JMenuItem("Barre état");
        itemBarreEtat.addActionListener(this);
        itemBarreEtat.setActionCommand("Barre état");
        menuAfficher.add(itemBarreEtat);

        itemZoomIn = new JMenuItem("Zoom in");
        itemZoomIn.addActionListener(this);
        itemZoomIn.setActionCommand("Zoom in");
        menuAfficher.add(itemZoomIn);

        itemZoomOut = new JMenuItem("Zoom out");
        itemZoomOut.addActionListener(this);
        itemZoomOut.setActionCommand("Zoom out");
        menuAfficher.add(itemZoomOut);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Nouveau")) {
            cadre.getFichier().nouveauFichier();
        } else if (command.equals("Ouvrir")) {
            cadre.getOuvrir().ouvrirFichier();
        } else if (command.equals("Sauvegarder")) {
            cadre.getSave().save();
        } else if (command.equals("Sauvegarder en tant que")) { // Correspond à l'action command
            cadre.getSaveAS().saveAs();
        } else if (command.equals("Quitter")) {
            System.exit(0);
        } else if (command.equals("Zoom in")) {
            cadre.getZoomIn().zoomIn();
        } else if (command.equals("Zoom out")) {
            cadre.getZoomOut().zoomOut();
        }
    }
}
