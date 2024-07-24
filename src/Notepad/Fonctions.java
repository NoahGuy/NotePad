package Notepad;

import Recherche.Cadre;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Fonctions {

    private CadreGUI cadre;
    protected String nomFichier;
    private String adresseFichier;
    private PanneauPrincipal panneauPrincipal;

    public Fonctions(CadreGUI cadre) {
        this.cadre = cadre;
        this.panneauPrincipal = cadre.getPanneauPrincipal();
    }

    public void nouveauFichier() {
        cadre.getPanneauPrincipal().getTextArea().setText("");
        cadre.setTitle("Nouvelle page");
        nomFichier = null;
        adresseFichier = null;
    }




    public void ouvrirFichier() {
        FileDialog fd = new FileDialog(cadre, "Ouvrir", FileDialog.LOAD);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            nomFichier = fd.getFile();
            adresseFichier = fd.getDirectory();
            cadre.setTitle(nomFichier);
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(adresseFichier+nomFichier));
            cadre.getPanneauPrincipal().setText("");

            String ligne;

            while ((ligne = br.readLine()) != null) {

                cadre.getPanneauPrincipal().append(ligne+"\n");
            }
            br.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void save(){
        if(nomFichier == null){
            saveAs();
        }else{
            try {
                FileWriter fw = new FileWriter(adresseFichier + nomFichier);
                fw.write(cadre.getPanneauPrincipal().getTextArea().getText()); // Correction ici
                cadre.setTitle(nomFichier);
                fw.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    public void saveAs() {
        FileDialog fd = new FileDialog(cadre, "Sauvegarder en tant que", FileDialog.SAVE);
        fd.setVisible(true);

        if (fd.getFile() != null) {
            nomFichier = fd.getFile();
            adresseFichier = fd.getDirectory();
            cadre.setTitle(nomFichier);
        }
        try {
            FileWriter fw = new FileWriter(adresseFichier + nomFichier);
            fw.write(cadre.getPanneauPrincipal().getTextArea().getText()); // Correction ici
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //TODO: mettre sous fonction pour le pourcentage zoom
    public void zoomIn() {
        Font currentFont = cadre.getPanneauPrincipal().getTextArea().getFont();
        if (currentFont.getSize() < 60) {
            float newSize = currentFont.getSize() + 2.0f;
            int pourcentageAugmentation = (int) (100 * (newSize / 12)); //12 est la grandeur defaut
            cadre.getPanneauPrincipal().getTextArea().setFont(currentFont.deriveFont(newSize));

            cadre.getPanneauPrincipal().getBarreEtat().getZoom().setText(String.valueOf(pourcentageAugmentation) + "% ");
        }
    }

    public void zoomOut() {
        Font currentFont = cadre.getPanneauPrincipal().getTextArea().getFont();
        if (currentFont.getSize() > 2) {

            float newSize = currentFont.getSize() - 2.0f; // Limite minimale à 8 pour éviter une taille de police trop petite

            int pourcentageDiminution = (int) (100 * (newSize / 12)); //12 est la grandeur defaut
            cadre.getPanneauPrincipal().getTextArea().setFont(currentFont.deriveFont(newSize));
            cadre.getPanneauPrincipal().getBarreEtat().getZoom().setText(String.valueOf(pourcentageDiminution) + "% ");
        }
    }

    public void rechercher() {

        Cadre cadre = new Cadre("Rechercher/Remplacer");

        // On passe la référence pour démarrage de l'application.
        SwingUtilities.invokeLater(cadre);
    }

    public void enleverRemettreBarreEtat() {

        if (cadre.getPanneauPrincipal().getBarreEtat().isVisible()) {

            cadre.getPanneauPrincipal().getBarreEtat().setVisible(false);
        }
        else {

            cadre.getPanneauPrincipal().getBarreEtat().setVisible(true);
        }
    }
}