package Notepad;

import Recherche.Cadre;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Fonctions {

    private static CadreGUI cadre;
    protected String nomFichier;
    private String adresseFichier;

    public Fonctions(CadreGUI cadre) {
        this.cadre = cadre;
    }

    public void nouveauFichier() {
        cadre.getPanneauPrincipal().getText().setText("");
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
                fw.write(cadre.getPanneauPrincipal().getText().getText()); // Correction ici
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
            fw.write(cadre.getPanneauPrincipal().getText().getText()); // Correction ici
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void zoomIn() {
        Font currentFont = cadre.getPanneauPrincipal().getText().getFont();
        float newSize = currentFont.getSize() + 2.0f;
        cadre.getPanneauPrincipal().getText().setFont(currentFont.deriveFont(newSize));
    }

    public static void zoomOut() {
        Font currentFont = cadre.getPanneauPrincipal().getText().getFont();
        float newSize = Math.max(currentFont.getSize() - 2.0f, 8.0f); // Limite minimale à 8 pour éviter une taille de police trop petite
        cadre.getPanneauPrincipal().getText().setFont(currentFont.deriveFont(newSize));
    }

    public static void rechercher() {

        Cadre cadre = new Cadre("Rechercher/Remplacer");

        // On passe la référence pour démarrage de l'application.
        SwingUtilities.invokeLater(cadre);
    }
}