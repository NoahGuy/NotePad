package Notepad;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  class de la barre en haut ou on a Fichier, Modifier et Afficher (à optimiser)
 *
 */
public class MonJMenuBar extends JMenuBar implements ActionListener {
    private JMenu menu;
    private JMenu menuModifier;
    private JMenu menuAfficher;
    private JMenuItem itemNouveau;
    private JMenuItem itemSauvegarder;
    private JMenuItem itemSaveAs;
    private JMenuItem itemQuitter;
    private CadreGUI  cadre;


    /**
     * constructeur et appel des methodes pour creer la barre et la barre d'outil
     * @param cadre
     */
    public MonJMenuBar(CadreGUI cadre) {
        this.cadre = cadre;
        creerBarMenu();
        creerFichierMenu();
    }

    /**
     * appel de la methode ajouterMenu qui va ajouter dans la barre Fichier,Modifier,Afficher
     */
    private void creerBarMenu() {
       ajouterMenu();
    }

    /**
     *
     */
    private void creerFichierMenu() {
       initcomposant();
    }

    private void ajouterMenu(){
        menu = new JMenu("Fichier");
        add(menu);

        menuModifier = new JMenu("Modifier");
        add(menuModifier);

        menuAfficher = new JMenu("Affichage");
        add(menuAfficher);

    }

    /**
     * Initialisation des composant et des action listener pour les composants de la barre d'outil
     */
    private void initcomposant(){
        itemNouveau = new JMenuItem("Nouveau");
        itemNouveau.addActionListener(this);
        itemNouveau.setActionCommand("Nouveau");
        menu.add(itemNouveau);

        itemSauvegarder = new JMenuItem("Sauvegarder");
        itemSauvegarder.addActionListener(this);
        itemSauvegarder.setActionCommand("Sauvegarder");
        menu.add(itemSauvegarder);

        itemSaveAs = new JMenuItem("Sauvegarder en tant que");
        itemSaveAs.addActionListener(this);
        itemSaveAs.setActionCommand("SaveAs");
        menu.add(itemSaveAs);

        itemQuitter = new JMenuItem("Quitter");
        itemQuitter.addActionListener(this);
        itemQuitter.setActionCommand("Quitter");
        menu.add(itemQuitter);
    }


    /**
     * Quand on clique sur Nouveau,Ouvrir,Save,SaveAS on a des actions
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("Nouveau")) {
            cadre.getFichier().nouveauFichier();
        }
        // Ajoutez d'autres actions
    }
}
